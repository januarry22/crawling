package com.kb.api.service.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class NaverSortService {
	//String resource_server ="https://perfect-img.s3.ap-northeast-2.amazonaws.com/";
	String resource_server ="https://zoayong-image-server.s3.ap-northeast-2.amazonaws.com/";


	String httpSearchNaverShoppingUrl = "https://search.shopping.naver.com";
	@Inject Common common;
	int timeOut = 1000*60*60;
	
	
	public Map<String, Object> main(HttpServletRequest request) throws IOException, ParseException, InterruptedException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		String pageSize = request.getParameter("pageSize");

		String keyword = request.getParameter("keyword");
		String sort = request.getParameter("sort");

//		String keyword = (String) param.get("keyword");
//		String sort =  (String) param.get("sort");
		if(StringUtils.isEmpty(keyword)) {
			map.put("success", false);
			map.put("errorMsg", "keyword를 입력해주세요.");
			return map;
		}
		if(StringUtils.isEmpty(sort)) {
			map.put("success", false);
			map.put("errorMsg", "sort를 입력해주세요.");
			return map;
		}
		if(!StringUtils.isEmpty(request.getParameter("pageSize"))) {
			pageSize = request.getParameter("pageSize");
		}
		
		keyword = URLEncoder.encode(keyword, "UTF-8");
		
		String url = httpSearchNaverShoppingUrl+"/search/all?pagingIndex=1&pagingSize="+pageSize+"&productSet=total&query="+keyword+"&sort="+sort+"&timestamp=&viewType=list";
		String referal = httpSearchNaverShoppingUrl;
		
		
		Document doc = Jsoup.connect(url)
		        .header("origin", referal) 
		        .header("referer", referal) 
		        .ignoreContentType(true) 
		        .timeout(timeOut)
		        .get();
		
		String json = doc.html();
		Pattern pattern = Pattern.compile("<script id=\"__NEXT_DATA__\" type=\"application/json\">(?:.*?)</script>");
		Matcher matcher = pattern.matcher(json);

		String jsonData = "";
		while (matcher.find()) {
			jsonData = matcher.group(0);
		}
		
		if(!StringUtils.isEmpty(jsonData)) {
			jsonData = jsonData.replaceAll("<script id=\"__NEXT_DATA__\" type=\"application/json\">", "").replaceAll("</script>", "");
		}
		//System.out.println("전체JSON : " + jsonData);
		
		JSONParser parser = new JSONParser();
		
		Object renderContent = parser.parse(jsonData);
		JSONObject renderObj = (JSONObject) renderContent;
		
		String propsContent = renderObj.get("props").toString();
		Object propsObj = parser.parse(propsContent);
		JSONObject props = (JSONObject) propsObj;
		
		String pagePropsContent = props.get("pageProps").toString();
		Object pagePropsObj = parser.parse(pagePropsContent);
		JSONObject pageProps = (JSONObject) pagePropsObj;
		
		
		String initialStateContent = pageProps.get("initialState").toString();
		Object initialStateObj = parser.parse(initialStateContent);
		JSONObject initialState = (JSONObject) initialStateObj;
		
		String productsContent = initialState.get("products").toString();
		Object productsObj = parser.parse(productsContent);
		JSONObject products = (JSONObject) productsObj;
		
		String listContent = products.get("list").toString();
		Object listObj = parser.parse(listContent);
		JSONArray items = (JSONArray) listObj;
		
		List<Map<String, Object>> rstList = new ArrayList<>();
		for(int i = 0 ; i < items.size() ; i++) {
			JSONObject itemTemp = (JSONObject) items.get(i); 
			
			
			String itemText = itemTemp.get("item").toString();
			Object itemObj = parser.parse(itemText);
			JSONObject item = (JSONObject) itemObj;
			
			String id = item.get("id").toString();
			String productName = item.get("productName").toString();
			String price = item.get("price").toString();
			String imageUrl = item.get("imageUrl").toString();
			

			String mallName = "";
			String bizplBaseAddr = "";
			if(!StringUtils.isEmpty(item.get("mallInfoCache"))) {
				String mallInfoCacheContent = item.get("mallInfoCache").toString();
				Object mallInfoCacheObj = parser.parse(mallInfoCacheContent);
				JSONObject mallInfoCache = (JSONObject) mallInfoCacheObj;
				
				mallName = StringUtils.isEmpty(mallInfoCache.get("name")) ? "" : mallInfoCache.get("name").toString();
				bizplBaseAddr = StringUtils.isEmpty(mallInfoCache.get("bizplBaseAddr")) ? "" : mallInfoCache.get("bizplBaseAddr").toString();
			}
			
			
			/**
			 * 기본정보 가져오기
			 */
			/*
			System.out.println("=============================================================");
			System.out.println("전체 : " + item);
			System.out.println("아이디 : " + id);
			System.out.println("상품명 : " + productName);
			System.out.println("금액 : " + price);
			System.out.println("이미지URL : " + imageUrl);
			System.out.println("=============================================================");
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("");
			*/
			
			/**
			 * 이미지 셋팅
			 */
			List<Map<String, Object>> imgList = new ArrayList<>();
			Map<String, Object> imgMap = new HashMap<String, Object>();
			
			String fullPath = imageUrl;
			String fileName = id+"_"+fullPath.substring(fullPath.lastIndexOf("/")+1, fullPath.length());
			System.out.println("이미지URL : " + fileName);
			common.download(fullPath, fileName, timeOut);
			imgMap.put("url", resource_server+fileName);
			imgList.add(imgMap);
			
			/**
			 * 리턴 데이터 셋팅
			 */
			Map<String, Object> rstMap = new HashMap<String, Object>();
			
			rstMap.put("name", productName);
			rstMap.put("price", price);
			rstMap.put("imgs", imgList);
			rstMap.put("mallName", mallName);
			rstMap.put("bizplBaseAddr", bizplBaseAddr);
			rstList.add(rstMap);
		}

		map.put("record",rstList.size());
		map.put("data", rstList);
		return map;
		
	}
	
	
	
	
	
	
}
