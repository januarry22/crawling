package com.kb.api.service.main;


import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.kb.api.util.Common;

@Service
public class anoterShopService {
    @Inject
    Common common;

    int timeOut = 1000*60*60;

    String resource_server ="https://perfect-img.s3.ap-northeast-2.amazonaws.com/";

    public Map<String, Object> main() throws IOException, ParseException, InterruptedException {
        Map<String, Object> map = new HashMap<String, Object>();

//        String domain="https://dorosiwa.co.kr";
//        String url ="https://dorosiwa.co.kr/product/list.html?cate_no=250&page=2#Product_ListMenu";
        String domain="https://freeboo.co.kr";
       	
        String url ="https://freeboo.co.kr/category/sexy/153/?page=2";
        Document doc = Jsoup.connect(url)
//                .header("origin", referal) // same-origin-polycy 로 인한 설정
//                .header("referer", referal) // same-origin-polycy 로 인한 설정
                .ignoreContentType(true) // json 받아오려면 타입무시를 해야하는듯?
                .timeout(timeOut)
                .get();
        
      //  Elements items = doc.select(".xans-product-listnormal");
        //Elements items = doc.select(".df-prl-desc");
        Elements items = doc.select(".desc");
        List<Map<String, Object>> rstList = new ArrayList<>();
        int i = 0;
        for( Element item : items ) {
        	 Map<String, Object> rstMap = new HashMap<String, Object>();
    		 
//       	 if(i < 3) {
//       		 i++;
        	//	Elements linkTag = item.select(".df-prl-name a"); //id
         		Elements linkTag = item.select(".name a"); //id
        		String link= linkTag.attr("href");
        		//.replace("..", ""); //id
        	//	System.out.print("DDDDD::::::::::"+link);
                
        		
           		Elements titleTag= item.select(".name span"); //id
           		String title= titleTag.text().replaceAll("상품명 : 상품명 ", "");
           	 // System.out.print("DDDDD::::::::::"+title);
           	
           		
           		Elements info = item.select(".xans-product-listitem span"); //id
           	//	Elements info = item.select(".price"); //id
           		String price= info.text();

                String content = this.sub(domain, link);
                
//                System.out.print("============================");
//                System.out.print("\n");
//                System.out.print("link::::::"+link+"\n");
//                System.out.print("title::::::"+title+"\n");
//                System.out.print("price::::::"+price+"\n");
//                System.out.print("\n");
//                System.out.print("\n");
//             //   link= link.substring(2,link.length());
//	            System.out.print("productContent::::::"+content+"\n");
//	            System.out.print("\n");
//	            System.out.print("============================");
            

                rstMap.put("shop_name","프리부");
                rstMap.put("info",price);
                rstMap.put("title",title);
                rstMap.put("link_url",domain+link);
                rstMap.put("content",content);

			rstList.add(rstMap);
        }
    		map.put("record", rstList.size());
        	map.put("data", rstList);
     //   }

        return map;
    }


    /**
     * 상세페이지
     * @throws InterruptedException
     */
    public String sub(String domain,String link) throws IOException, ParseException, InterruptedException {
        String id = null;
        String connect_url =domain+link;
        String productContent = null;
        
        Document doc = Jsoup.connect(connect_url)
//                .header("origin", referal) // same-origin-polycy 로 인한 설정
//                .header("referer", referal) // same-origin-polycy 로 인한 설정
                .ignoreContentType(true) // json 받아오려면 타입무시를 해야하는듯?
                .timeout(timeOut)
                .get();
        
        Elements conItems = doc.select(".xans-detail-description");
        productContent = conItems.html();

        List<Map<String, Object>> rstList = new ArrayList<>();

        Pattern pattern = Pattern.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>"); //img 태그 src 추출 정규표현식
        Matcher matcher = pattern.matcher(conItems.toString());

        String prel =null;
        while(matcher.find()){
        	String fullPath = matcher.group(1);
//        	fullPath =URLDecoder.decode(fullPath, "UTF-8");
        	
            UUID one = UUID.randomUUID();
            String fileName = one.toString().replaceAll("-", "")+"_"+fullPath.substring(fullPath.lastIndexOf("/")+1, fullPath.indexOf("?") == -1 ? fullPath.length() : fullPath.lastIndexOf("?"));
            
//            if(fullPath.contains("balimall.cafe24.com")) {
//                common.download("https:"+fullPath, fileName, timeOut);
//            }
            if(fullPath.contains("vividjoy.cafe24.com")) {
                common.download(fullPath, fileName, timeOut);
            }
//            if(fullPath.contains("https://www.qmomo.co.kr")) {
//              common.download(fullPath, fileName, timeOut);
//            }
            if(fullPath.contains("simplo.cafe24.com")) {
                common.download(fullPath, fileName, timeOut);
            }
            if(fullPath.contains("//freeboo.co.kr")) {
              common.download("https:"+fullPath, fileName, timeOut);
            }

            common.download(domain+fullPath, fileName, timeOut);
            
           // common.download( fullPath, fileName, timeOut);
            productContent= productContent.replaceAll(fullPath, resource_server+fileName);
        }
        productContent= productContent.replaceAll("ec-data-","");
        return productContent;
    }



}
