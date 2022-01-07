package com.kb.api.service.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kb.api.util.Common;

@Service
public class SmartStoreService {
	//String resource_server ="https://perfect-img.s3.ap-northeast-2.amazonaws.com/";
	String resource_server ="https://zoayong-image-server.s3.ap-northeast-2.amazonaws.com/";
	
	String httpSmartStoreUrl = "https://smartstore.naver.com";
	@Inject Common common;
	int timeOut = 1000*60*60;
	
	public Map<String, Object> main(HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		String storeId = request.getParameter("storeId");
		String categoryId = request.getParameter("categoryId");
		String pageSize = request.getParameter("pageSize");

		String page = request.getParameter("page");
		
		if(StringUtils.isEmpty(storeId)) {
			map.put("success", false);
			map.put("errorMsg", "storeId를 입력해주세요.");
			return map;
		}
		if(StringUtils.isEmpty(categoryId)) {
			map.put("success", false);
			map.put("errorMsg", "categoryId를 입력해주세요.");
			return map;
		}
		if(!StringUtils.isEmpty(request.getParameter("pageSize"))) {
			pageSize = request.getParameter("pageSize");
		}
		
		if(!StringUtils.isEmpty(request.getParameter("pageSize"))) {
			pageSize = request.getParameter("pageSize");
			
			if(Integer.parseInt(pageSize) < 20) {
				map.put("success", false);
				map.put("errorMsg", "pageSize는 최소 20이상부터 가능합니다.");
				return map;
			}
		}
		
		String url = httpSmartStoreUrl+"/"+storeId+"/category/"+categoryId+"?st=POPULAR&free=false&dt=IMAGE&page="+page+"&size="+pageSize;
		String referal = httpSmartStoreUrl+"/"+storeId+"/category/"+categoryId+"?cp=1";
		
         
		Document doc = Jsoup.connect(url)
		        .header("origin", referal) // same-origin-polycy 로 인한 설정
		        .header("referer", referal) // same-origin-polycy 로 인한 설정
		        .ignoreContentType(true) // json 받아오려면 타입무시를 해야하는듯?
		        .timeout(timeOut)
		        .get();
         
         
         Elements items = doc.select(".-qHwcFXhj0");
         List<Map<String, Object>> rstList = new ArrayList<>();
         int i = 0;
         for( Element item : items ) {
//        	 if(i < 2) {
//	       		 i++;
        		 Map<String, Object> rstMap = new HashMap<String, Object>();
        		 
        		 Elements idTag = item.select("._3BkKgDHq3l"); //id
            	 Elements nameTag = item.select(".QNNliuiAk3"); //상품명
            	 Elements priceTag = item.select("._23DThs7PLJ strong .nIAdxeTzhx"); //금액
            	 Elements bepriceTag = item.select("._45HSXeff1y .nIAdxeTzhx"); //할인전 금액
            	 Elements discountTag = item.select(".pT4bw14aV2"); //할인율
            	 
            	 
            	 String href = idTag.attr("href");
            	 String id = href.substring(href.lastIndexOf("/")+1, href.length());
            	 
            	 String name = nameTag.text();
            	 String price = priceTag.text();
            	 String beprice = bepriceTag.text();
            	 String discount = discountTag.text();
            	 
            	 
            	 /**
     			 * 기본정보 가져오기
     			 */

//            	 System.out.println("=============================================================");
//            	 System.out.println("상세페이지 URL : " + href);
//            	 System.out.println("ID : " + id);
//            	 System.out.println("상품명 : " + name);
//            	 System.out.println("금액 : " + price);
//            	 System.out.println("할인전 금액 : " + beprice);
//            	 System.out.println("할인율 : " + discount);
//            	 System.out.println("=============================================================");
//            	 System.out.println("");
//            	 System.out.println("");
//            	 System.out.println("");
//            	 System.out.println("");

            	 
            	 
//            	/**
//     			 * 이미지 가져오기(상세페이지에 가서 가져온다)
//     			 */
//            	List<Map<String, Object>> imgList = new ArrayList<>();
//     			if(!StringUtils.isEmpty(href) && !StringUtils.isEmpty(id)) {
//     				imgList = this.getImg(href, id);
//
//      			    System.out.print("imgList"+imgList);
//     			}
            	 
            	/**
      			 * 옵션 가져오기(상세페이지에 가서 가져온다)
      			 */
            //	Map<String, Object> optionList = new HashMap<>();
      			if(!StringUtils.isEmpty(href) && !StringUtils.isEmpty(id)) {
      				Document html = Jsoup.connect(httpSmartStoreUrl+href)
      				        .get();
      				// channelNo 필요 
      			  	String channelNo = html.toString().substring(html.toString().indexOf("\"channelNo\":"), html.toString().indexOf(",\"displayConfig\""));
      				channelNo = channelNo.replaceAll("\"channelNo\":", "");
      				
      			  //  optionList = this.getOption(channelNo, id);
      				rstMap.put("options", this.getOption(channelNo, id));
      			}
     		
     			
     			/**
     			 * 상세페이지 접근
     			 */
     			if(!StringUtils.isEmpty(href) && !StringUtils.isEmpty(id)) {
     				
     				
     				Connection conn = Jsoup.connect(httpSmartStoreUrl + href).timeout(timeOut);
     		        Document html = conn.get(); // conn.post();
     		        
     		        String productNo = html.toString().substring(html.toString().indexOf("\"productNo\":\"")+1, html.toString().indexOf("\",\"salePrice")+1);
     		        productNo = productNo.replaceAll("\"productNo\":\"", "");

     				if(!StringUtils.isEmpty(productNo)) {
        				String renderContent = this.sub(id, productNo);
        				rstMap.put("renderContent", renderContent);

        				// 이미지 태그만 뽑아서 출력 
//        				String subRenderContent1 = renderContent.replaceAll("\\\\", "");
//        				Pattern pattern1 = Pattern.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>"); //img 태그 src 추출 정규표현식
//        		        Matcher matcher1 = pattern1.matcher(subRenderContent1);
//        			
//        		        while(matcher1.find()){
//        		        	String fullPath1 = matcher1.group(1);
//        		        	System.out.print("fullPath1"+fullPath1+"\n");
//        		        	
//        		        }
     				}
     			}
        		 
     			
     			/**
    			 * 리턴 데이터 셋팅
    			 */
     			 rstMap.put("name", name);
				 rstMap.put("price", price);
				 rstMap.put("prev_price", beprice);
				 rstMap.put("discount", discount);
				 rstMap.put("writer_name", request.getParameter("writer_name"));
			//	 rstMap.put("options", optionList);
    			//rstMap.put("imgs", imgList);
    			
    			
    			rstList.add(rstMap);
        //	}
        	 
         }
 		 map.put("record",rstList.size());
 		 map.put("link",referal);
         map.put("data", rstList);
 		return map;
	}
	

	/**
	 * 옵션  가져오기(상세페이지에 가서 가져온다)
	 * @throws IOException 
	 * @throws ParseException 
	 * @throws InterruptedException 
	 * @throws Exception 
	 */
	public  List<Map<String, Object>>  getOption(String channelNo, String id) throws NullPointerException, IOException, ParseException, InterruptedException{
		 List<Map<String, Object>> optionList = new ArrayList();
	
	    String optionUrl = httpSmartStoreUrl+"/i/v1/stores/"+channelNo+"/products/"+id;
	    Document doc = Jsoup.connect(optionUrl)
		        .ignoreContentType(true) 
		        .timeout(timeOut)
		        .get();
		String json = doc.text();

		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(json); 

		List<String> rstList1 = new ArrayList<>();
		List<String> rstList2 = new ArrayList<>();
		List<String> rstList3 = new ArrayList<>();
		JSONParser parser = new JSONParser();
		
		JsonNode options = node.findValue("options");	

		Object tempObj1 = parser.parse( options.toString());
		JSONArray optionParse1 = (JSONArray) tempObj1;
		Map<String, Object> optionPRa = new HashMap<String, Object>();
		for(int i = 0 ; i < optionParse1.size() ; i++) {
			JSONObject opt = (JSONObject) optionParse1.get(i);
			optionPRa.put("groupName"+(i+1), opt.get("groupName"));
			
		}
		
		JsonNode optionCombinations = node.findValue("optionCombinations");		//옵션 조합
		Object tempObj = parser.parse( optionCombinations.toString());
		JSONArray optionParse = (JSONArray) tempObj;

		Map<String, Object> returnOpt = new HashMap<String, Object>();

		List<Map<String, Object>> optItems = new ArrayList();
		JSONObject opt = null;
		for(int i = 0 ; i < optionParse.size() ; i++) {
			opt = (JSONObject) optionParse.get(i);

			if(!StringUtils.isEmpty(opt.get("optionName1"))) {
				rstList1.add( opt.get("optionName1").toString());
			}
			if(!StringUtils.isEmpty(opt.get("optionName2"))) {
			rstList2.add( opt.get("optionName2").toString());
		//	returnOpt.put("option",this.optionListtoMap(rstList2,"option2",optionPRa.get("groupName2").toString()));
			}

			if(!StringUtils.isEmpty(opt.get("optionName3"))) {
			rstList3.add( opt.get("optionName3").toString());
		//	returnOpt.put("option",this.optionListtoMap(rstList3,"option3",optionPRa.get("groupName3").toString()));
			}
		
		}

		if(!StringUtils.isEmpty(opt.get("optionName1"))) {
		optionList.addAll(this.optionListtoMap(rstList1,optionPRa.get("groupName1").toString(),"1"));
		}
		if(!StringUtils.isEmpty(opt.get("optionName2"))) {
		optionList.addAll(this.optionListtoMap(rstList2,optionPRa.get("groupName2").toString(),"2"));
		}
		if(!StringUtils.isEmpty(opt.get("optionName3"))) {
		optionList.addAll(this.optionListtoMap(rstList3,optionPRa.get("groupName3").toString(),"3"));
		}
	    return optionList;
	}
	
	
	public List<Map<String, Object>> optionListtoMap(List<String> optionList,String mapName,String grade) throws ParseException{
	
		optionList =  optionList.stream().distinct().collect(Collectors.toList());
	
		List<Map<String, Object>> returnOption = new ArrayList();
		for(int i = 0; i<optionList.size(); i++) {
			LinkedHashMap<String, Object> option = new LinkedHashMap<String, Object>();
			option.put("option_name", optionList.get(i));
			option.put("option_group",mapName);
			option.put("option_grade",grade);
			returnOption.add(option);
		}
	
		return returnOption;
	}

	/**
	 * 이미지 가져오기(상세페이지에 가서 가져온다)
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public List<Map<String, Object>> getImg(String uri, String id) throws IOException, InterruptedException{
		List<Map<String, Object>> imgList = new ArrayList<>();
		
	
		Connection conn = Jsoup.connect(httpSmartStoreUrl + uri).timeout(timeOut);
	    Document html = conn.get(); // conn.post();
	    
	    
	    Elements imgItems = html.select("._1F-riHNwNb img");
	    for( Element imgItem : imgItems ) {
	    	Map<String, Object> imgMap = new HashMap<String, Object>();
	    	 
	    	
	    	String fullPath = imgItem.attr("src");
	    	fullPath = fullPath.substring(0, fullPath.lastIndexOf("?")+1)+"type=m510";
	    	String fileName = id+"_"+fullPath.substring(fullPath.lastIndexOf("/")+1, fullPath.lastIndexOf("?"));
	    	common.download(fullPath, fileName, timeOut);
			
			imgMap.put("url", resource_server+fileName);
			imgList.add(imgMap);
	    }
	    return imgList;
	}


	/**
	 * 상세페이지 
	 * @throws InterruptedException 
	 */
	
	public String sub(String id, String productNo) throws IOException, ParseException, InterruptedException {
		
		String fb_url = "https://shopping.naver.com/v1/products/"+id+"/contents/"+productNo+"/PC";

		URL url = new URL(fb_url);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setDoOutput(true);
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		con.setConnectTimeout(timeOut);
		con.setReadTimeout(timeOut);

		int responseCode = con.getResponseCode();
		BufferedReader br;
		if (responseCode == 200) {
			br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
		} else {
			br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "utf-8"));
		}
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = br.readLine()) != null) {
			response.append(inputLine);
		}
		br.close();
		String json = response.toString();
		
		/**
		 * HTML 데이터 파싱
		 */
		String renderContent = json.substring(json.indexOf("\"renderContent\":\""), json.length());
		renderContent = renderContent.replaceAll("\"renderContent\":\"", "");
		renderContent = renderContent.substring(0, renderContent.lastIndexOf("}")-1);
		
		
		/**
		 * HTML에서 img src 추출후 이미지 로컬에 업로드
		 */
		String subRenderContent = renderContent.replaceAll("\\\\", "");
		Pattern pattern = Pattern.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>"); //img 태그 src 추출 정규표현식

        Matcher matcher = pattern.matcher(subRenderContent);
        
        while(matcher.find()){
        	String fullPath = matcher.group(1);
        	//String fileName = id+"_"+fullPath.substring(fullPath.lastIndexOf("/")+1, fullPath.length());
        	// UUID one = UUID.randomUUID();
            // String fileName = one.toString().replaceAll("-", "")+"_"+id+"_"+fullPath.substring(fullPath.lastIndexOf("/")+1, fullPath.indexOf("?") == -1 ? fullPath.length() : fullPath.lastIndexOf("?"));
        	String fileName = id+"_"+fullPath.substring(fullPath.lastIndexOf("/")+1, fullPath.indexOf("?") == -1 ? fullPath.length() : fullPath.lastIndexOf("?"));
            fileName=fileName.replaceAll("\\(","").replaceAll("\\)","");
			common.download(fullPath, fileName, timeOut);
        }

		/**
		 * 정규표현식 () 한번더 replace 
		 */
		String formatContent = renderContent.replaceAll("\\\\", "").replaceAll("\\(","").replaceAll("\\)","");
		Pattern pattern1 = Pattern.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>"); 
		Matcher matcher1 = pattern1.matcher(formatContent);
        while(matcher1.find()){
        	String fullPath = matcher1.group(1);
        	//UUID one = UUID.randomUUID();
           // String fileName = one.toString().replaceAll("-", "")+"_"+id+"_"+fullPath.substring(fullPath.lastIndexOf("/")+1, fullPath.indexOf("?") == -1 ? fullPath.length() : fullPath.lastIndexOf("?"));
            String fileName = id+"_"+fullPath.substring(fullPath.lastIndexOf("/")+1, fullPath.indexOf("?") == -1 ? fullPath.length() : fullPath.lastIndexOf("?"));
           
			String currentUrl = resource_server+fileName;
			formatContent= formatContent.replaceAll(fullPath, currentUrl);
        }
        
        
		return formatContent;
	}
	
	
}
