package com.kb.api.service.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
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
public class BrandStoreService {
    @Inject
    Common common;

    int timeOut = 1000*60*60;

	String resource_server ="https://zoayong-image-server.s3.ap-northeast-2.amazonaws.com/";
	String brandStoreUrl="https://brand.naver.com";

    public Map<String, Object> main(HttpServletRequest request) throws IOException, ParseException, InterruptedException {
        Map<String, Object> map = new HashMap<String, Object>();

        
        String getIDUrl=request.getParameter("brandUrl");
        Document getBrandId = Jsoup.connect(getIDUrl)
		        .ignoreContentType(true) // json 받아오려면 타입무시를 해야하는듯?
		        .timeout(timeOut)
		        .get();

        String storeId = getIDUrl.substring(getIDUrl.lastIndexOf("/")+1,getIDUrl.length());
        
        String channelNo = getBrandId.toString().substring(getBrandId.toString().indexOf("\"channelNo\":"),getBrandId.toString().indexOf(",\"commonConfig\""));
        channelNo = channelNo.replaceAll("\"channelNo\":", "");
        
        
   //     String url = "https://brand.naver.com/n/v1/stores/101135002/categories/ALL/products?categoryId=ALL&categorySearchType=STDCATG&sortType=POPULAR&free=false&page=2&pageSize=40";
        String url = "https://brand.naver.com/n/v1/stores/"+channelNo+"/categories/ALL/products?categoryId=ALL&categorySearchType=STDCATG&sortType=POPULAR&free=false&page="+request.getParameter("page")+"&pageSize="+request.getParameter("pageSize");
           
   //     String referal ="https://brand.naver.com/gapkids/category/ALL?st=POPULAR&free=false&dt=BIG_IMAGE&page=2&size=40";
        
        String referal ="https://brand.naver.com/"+storeId+"/category/ALL?st=POPULAR&free=false&dt=BIG_IMAGE&page=2&size=40";
   
        Document doc = Jsoup.connect(url)
                .header("origin", referal) // same-origin-polycy 로 인한 설정
                .header("referer", referal) // same-origin-polycy 로 인한 설정
                .ignoreContentType(true) // json 받아오려면 타입무시를 해야하는듯?
                .timeout(timeOut)
                .get();

		String json = doc.text();

		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(json); // json을 node로 변환하여 traversing을 편하게.
		
		JSONParser parser = new JSONParser();
		JsonNode products = node.findValue("simpleProducts");
		JsonNode channel = node.findValue("channel");
        
        
		List<Map<String, Object>> rstList = new ArrayList<>();   
		int i = 0;
		for (JsonNode product : products) {
//			 if(i < 2) {
//	       		 i++;
			Map<String, Object> rstMap = new HashMap<String, Object>();
			Object tempObj = parser.parse( product.toString());
			JSONObject obj = (JSONObject) tempObj;


			Object tempObj2 = parser.parse( channel.toString());
			JSONObject obj2 = (JSONObject) tempObj2;

			String id = obj.get("id").toString();
			String mallName = obj2.get("channelName").toString();


			JsonNode discount = product.findValue("benefitsView");
			JsonNode detail = product.findValue("detailContents");
			Object discount1 = parser.parse( discount.toString());
			JSONObject discountTag = (JSONObject) discount1;
			

			Object detail1 = parser.parse( detail.toString());
			JSONObject detailContent = (JSONObject) detail1;
			

			/**
			 * 기본정보 가져오기
			 */

//			System.out.println("=============================================================");
//			System.out.println("스토어명 : " + mallName);
//			System.out.println("아이디 : " + id);
//			System.out.println("상품 번호 : " + obj.get("productNo"));
//			System.out.println("상품명 : " + obj.get("name").toString());
//			System.out.println("판매가 : " + obj.get("salePrice"));
//			System.out.println("할인가 : " + discountTag.get("discountedSalePrice"));
//			System.out.println("모바일 할인가 : " + discountTag.get("mobileDiscountedSalePrice"));
//			System.out.println("할인율(PC) : " + discountTag.get("discountedRatio"));
//			System.out.println("할인율(MOB) : " + discountTag.get("mobileDiscountedRatio"));
//			System.out.println("상세 설명 : " + detailContent.get("detailContentText"));
//			System.out.println("=============================================================");
//			System.out.println("");
//			System.out.println("");
//			System.out.println("");
//			System.out.println("");

			/**
  			 * 옵션 가져오기(상세페이지에 가서 가져온다)
  			 */
        	if(!StringUtils.isEmpty(channelNo) && !StringUtils.isEmpty(id)) {
  				rstMap.put("options", this.getOption(channelNo, id));
  			}
			/**
			 * 상세페이지 접근
			 */
			if(!StringUtils.isEmpty(id) && !StringUtils.isEmpty(obj.get("productNo"))) {
				String productNo = obj.get("productNo").toString();
				String renderContent = this.sub(id, productNo);
			//	String result = renderContent.replaceAll("\\\\", "");
			//	rstMap.put("renderContent", result);
				rstMap.put("renderContent",renderContent);
			}
			

			 rstMap.put("brand_name", mallName);
			 rstMap.put("name", obj.get("name").toString());
			 rstMap.put("price", obj.get("salePrice").toString());
			 rstMap.put("prev_price", discountTag.get("discountedSalePrice"));
			 rstMap.put("discount", discountTag.get("discountedRatio"));
			 rstMap.put("writer_name", request.getParameter("writer_name"));
			
			 rstMap.put("link_url", getIDUrl);
			 
			 rstList.add(rstMap);
			
		}
		//}
		map.put("record",rstList.size());
        map.put("data", rstList);
        return map;
    }
    

	/**
	 * 옵션  가져오기(상세페이지에 가서 가져온다)
	 * @throws IOException 
	 * @throws ParseException 
	 * @throws Exception 
	 */
	public  List<Map<String, Object>>  getOption(String channelNo, String id) throws NullPointerException, IOException, ParseException, InterruptedException{
		 List<Map<String, Object>> optionList = new ArrayList();
	
	    String optionUrl = brandStoreUrl+"/n/v1/stores/"+channelNo+"/products/"+id;
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

		if(!StringUtils.isEmpty(options)) {
			
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
     * 상세페이지
     * @throws InterruptedException
     */
    public String sub(String id,String productNo) throws IOException, ParseException, InterruptedException {
    //    String id = null;
        String connect_url ="https://brand.naver.com/n/v1/products/"+id+"/contents/"+productNo+"/PC";
  //     System.out.print("connect_url:::"+connect_url);
       // Request URL: https://brand.naver.com/n/v1/products/6037638060/contents/6009292715/PC

		URL url = new URL(connect_url);
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
		String subRenderContent = renderContent.replaceAll("\\\\", "").replaceAll("[+]", "00");
	    
		Pattern removePattern = Pattern.compile("<img *src=[\"']?([^>\"']+)[\"']?[^>]*>"); //img 태그 src 추출 정규표현식
		Matcher removeMacher = removePattern.matcher(subRenderContent);

		String removeParse = null;
		if(removeMacher.find()){	
			// img 토큰 경로 추출 
			removeParse = removeMacher.group(1);
		}
		
		
		Pattern pattern = Pattern.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>"); //img 태그 src 추출 정규표현식
		Matcher matcher = pattern.matcher(subRenderContent);
		while(matcher.find()){
        	String fullPath = matcher.group(1);
        	
        	String fileName = id+"_"+fullPath.substring(fullPath.lastIndexOf("/")+1, fullPath.indexOf("?") == -1 ? fullPath.length() : fullPath.lastIndexOf("?"));
        	Integer lastIndex = fileName.length()-1;
        	
        	if(fileName.lastIndexOf("=")==lastIndex){
        		fileName = fileName+".jpg";
        	}
        	
        	common.download(fullPath, fileName, timeOut);
		    String currentUrl = resource_server+fileName;
        	fullPath = fullPath.replaceAll("[?]", "00");
		    subRenderContent = subRenderContent.replaceAll("[?]", "00").replaceAll(fullPath, currentUrl);
		    
		    // img src 토큰데이터 치환 *필수*
		    subRenderContent = subRenderContent.replaceAll("src='"+removeParse+"'", "");
		    // data-src 를 src 로 변환 
		    subRenderContent = subRenderContent.replaceAll("data-", "");
		}
		
       return subRenderContent;
    }
}
