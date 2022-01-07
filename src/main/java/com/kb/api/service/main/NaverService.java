package com.kb.api.service.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Connection;
import org.jsoup.Connection.Response;
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
public class NaverService {

	//String resource_server ="https://perfect-img.s3.ap-northeast-2.amazonaws.com/";
	String resource_server ="https://zoayong-image-server.s3.ap-northeast-2.amazonaws.com/";

	String httpNaverShoppingUrl = "https://shopping.naver.com";
	@Inject Common common;
	int timeOut = 1000*60*60;
	
	
	public Map<String, Object> main(HttpServletRequest request,HashMap<String,Object> param) throws IOException, ParseException, InterruptedException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		String storeId = param.get("link").toString();
		String referalId = param.get("refer").toString();
	
		//	String pageSize = "10";
		
//		String subVertical ="";
//		if(StringUtils.isEmpty(request.getParameter("target")) || (!request.getParameter("target").equals("꼬마옴므") && !request.getParameter("target").equals("마이주니어"))) {
//			map.put("success", false);
//			map.put("errorMsg", "target이 잘못된 값입니다.");
//			return map;
//		}
//		if(!StringUtils.isEmpty(request.getParameter("target")) && request.getParameter("target").equals("꼬마옴므")) {
//			storeId = "1623942000000";
//			referalId = "1000016918";
//		}else if(!StringUtils.isEmpty(request.getParameter("target")) && request.getParameter("target").equals("마이주니어")) {
//			storeId = "1624028400000";
//			referalId = "100002752";
//		}
//		if(!StringUtils.isEmpty(request.getParameter("pageSize"))) {
//			pageSize = request.getParameter("pageSize");
//		}
		
		
//		String url = httpNaverShoppingUrl+"/v1/products?_nc_="+storeId+"&subVertical=KIDS&page=1&pageSize="+pageSize+"&sort=RECENT&filter=ALL&displayType=CATEGORY_HOME&includeZzim=true&includeViewCount=true&includeStoreCardInfo=false&includeStockQuantity=false&includeBrandInfo=false&includeBrandLogoImage=false&includeRepresentativeReview=false&includeListCardAttribute=false&includeRanking=false&includeRankingByMenus=false&includeStoreCategoryName=false&includeIngredient=false&menuId=0&storeId=1000016918&standardSizeKeys[]=&standardColorKeys[]=&optionFilters[]=&attributeValueIds[]=&attributeValueIdsAll[]=&certifications[]=&filterTodayDelivery=false&filterFreeReturnInsurance=false&filterHopeDelivery=false";
//		String referal = httpNaverShoppingUrl+"/kids/stores/"+referalId;

		//String url = httpNaverShoppingUrl +"/v1/products?_nc_=1628521200000&subVertical=HANDMADE&page=1&pageSize=10&sort=RECENT&displayType=CATEGORY_HOME&includeZzim=true&includeViewCount=true&includeStoreCardInfo=true&includeStockQuantity=false&includeBrandInfo=false&includeBrandLogoImage=false&includeRepresentativeReview=false&includeListCardAttribute=false&includeRanking=false&includeRankingByMenus=false&includeStoreCategoryName=false&includeIngredient=false&standardSizeKeys[]=&standardColorKeys[]=&optionFilters[]=&attributeValueIds[]=&attributeValueIdsAll[]=&certifications[]=&filterTodayDelivery=false&filterFreeReturnInsurance=false&filterHopeDelivery=false";
		String url =storeId;
		String referal = httpNaverShoppingUrl+referalId;
		
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
		JsonNode products = node.findValue("products");
		JsonNode channel = node.findValue("channel");

		List<Map<String, Object>> rstList = new ArrayList<>();
		for (JsonNode product : products) {

			Map<String, Object> rstMap = new HashMap<String, Object>();
			Object tempObj = parser.parse( product.toString());
			JSONObject obj = (JSONObject) tempObj;


			Object tempObj2 = parser.parse( channel.toString());
			JSONObject obj2 = (JSONObject) tempObj2;

			String id = obj.get("_id").toString();
			String mallName = obj2.get("name").toString();
			/**
			 * 기본정보 가져오기
			 */

//			System.out.println("=============================================================");
//			System.out.println("아이디 : " + id);
//			System.out.println("스토어명 : " + mallName);
//			System.out.println("상품명 : " + obj.get("name"));
//			System.out.println("상품간략설명 : " + obj.get("contentText"));
//			System.out.println("금액(PC) : " + obj.get("pcDiscountPrice"));
//			System.out.println("할인율(PC) : " + obj.get("pcDiscountRate"));
//			System.out.println("금액(MO) : " + obj.get("mobileDiscountPrice"));
//			System.out.println("할인율(MO) : " + obj.get("mobileDiscountRate"));
//			System.out.println("조회수 : " + obj.get("viewCountFromWindowApi"));
//			System.out.println("=============================================================");
//			System.out.println("");
//			System.out.println("");
//			System.out.println("");
//			System.out.println("");

			
			
			/**
			 * 이미지 가져오기(리스트페이지에서 바로 가져온다)
			 */
			Object tempImages = parser.parse( obj.get("images").toString());
			JSONArray images = (JSONArray) tempImages;
			List<Map<String, Object>> imgList = new ArrayList<>();
			if(!StringUtils.isEmpty(images)) {
				imgList = this.getImg(images, id);
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
			
			
			/**
			 * 리턴 데이터 셋팅
			 */
			rstMap.put("name", obj.get("name"));
			rstMap.put("mallName",mallName);
			rstMap.put("imgs", imgList);
			rstMap.put("price", obj.get("pcDiscountPrice"));
			rstMap.put("writer_name", request.getParameter("writer_name"));
			rstList.add(rstMap);
		}
		map.put("record",rstList.size());
		map.put("data", rstList);
		return map;
	}
	
	/**
	 * 이미지 가져오기
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public List<Map<String, Object>> getImg(JSONArray images, String id) throws IOException, InterruptedException{
		List<Map<String, Object>> imgList = new ArrayList<>();
		
		for(int i = 0 ; i < images.size() ; i++) {
			JSONObject image = (JSONObject) images.get(i);
			
			if(!StringUtils.isEmpty(image.get("imageUrl"))) {
				Map<String, Object> imgMap = new HashMap<String, Object>();
				
				String fullPath = image.get("imageUrl").toString();
				String fileName = id+"_"+fullPath.substring(fullPath.lastIndexOf("/")+1, fullPath.length());
				common.download(fullPath, fileName, timeOut);
				imgMap.put("url", resource_server+fileName);
				imgList.add(imgMap);
			}
		}
        return imgList;
	}
	
	/**
	 * 상세페이지 
	 * @throws InterruptedException 
	 */
	public String sub(String id, String productNo) throws IOException, ParseException, InterruptedException {
		//System.out.print("id"+id+":::::"+productNo+":::\n");
		
		String fb_url = httpNaverShoppingUrl+"/v1/products/"+id+"/contents/"+productNo+"/PC";
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

//        while(matcher.find()){
//        	String fullPath = matcher.group(1);
//			String fileName = id+"_"+fullPath.substring(fullPath.lastIndexOf("/")+1, fullPath.length());
//			common.download(fullPath, fileName, timeOut);
//			String currentUrl = "/resources/images/"+fileName;
//			renderContent = renderContent.replaceAll(fullPath, currentUrl);
//		//	subRenderContent=renderContent.replaceAll("\\\\", "");
//        }
        while(matcher.find()){
        	String fullPath = matcher.group(1);
        	//String fileName = id+"_"+fullPath.substring(fullPath.lastIndexOf("/")+1, fullPath.length());
//        	UUID one = UUID.randomUUID();
//            String fileName = one.toString().replaceAll("-", "")+"_"+id+"_"+fullPath.substring(fullPath.lastIndexOf("/")+1, fullPath.indexOf("?") == -1 ? fullPath.length() : fullPath.lastIndexOf("?"));
          
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
        //	UUID one = UUID.randomUUID();
        //    String fileName = one.toString().replaceAll("-", "")+"_"+id+"_"+fullPath.substring(fullPath.lastIndexOf("/")+1, fullPath.indexOf("?") == -1 ? fullPath.length() : fullPath.lastIndexOf("?"));
        	String fileName = id+"_"+fullPath.substring(fullPath.lastIndexOf("/")+1, fullPath.indexOf("?") == -1 ? fullPath.length() : fullPath.lastIndexOf("?"));
              
			String currentUrl = resource_server+fileName;
			
			formatContent= formatContent.replaceAll(fullPath, currentUrl);
        }
		return formatContent;
	}
	
	
	
	
	
}
