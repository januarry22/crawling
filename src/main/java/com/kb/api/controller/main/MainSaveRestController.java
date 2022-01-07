package com.kb.api.controller.main;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kb.api.service.main.BrandStoreService;
import com.kb.api.service.main.NaverService;
import com.kb.api.service.main.NaverSortService;
import com.kb.api.service.main.SmartStoreService;
import com.kb.api.service.service.CommonService;
import com.kb.api.service.service.MasterService;
import com.kb.api.util.Common;

@RestController
public class MainSaveRestController {
	
	/**
	 * 네이버쇼핑(스토어기준)
	 */
	@Inject NaverService naverService;
	
	/**
	 * 네이버쇼핑(전체)정렬기준
	 */
	@Inject NaverSortService naverSortService;
	
	/**
	 * 스마트스토어
	 */
	@Inject SmartStoreService smartStoreService;
	

	@Inject CommonService commmonService;
	
	
	@Inject
	com.kb.api.service.main.anoterShopService anoterShopService;

	/**
	 * 브랜드스토어
	 */
	@Inject BrandStoreService brandStoreService;
	
	@Inject MasterService masterService;
	
	@PostMapping("/api/v1/naver/save")  
	public ResponseEntity<Map<String, Object>> main(HttpServletRequest request, @RequestBody HashMap param) throws Exception {
		ResponseEntity<Map<String, Object>> entity = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = naverService.main(request,param);
			HashMap<String, Object> res = new HashMap(map);
			res.put("keyword",param.get("full_link").toString());
			res.put("type_seq", param.get("type_seq").toString());
			res.put("full_link", param.get("full_link").toString());
			res.put("writer_name", param.get("writer_name").toString());
			int record = masterService.dataCreate("mapper.admin.ProductMapper", "create", res);
			

			map.put("success", true);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		Common.returnPrint(Common.GmakeDynamicValueObject(entity));
		return entity; 
	}
	
	 
	
	@GetMapping("/api/v1/smartstore/save")  
	public ResponseEntity<Map<String, Object>> smartstore(HttpServletRequest request) throws Exception {
		ResponseEntity<Map<String, Object>> entity = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			//map.put("link", request.getParameter("link"));
		//	map.put("name", request.getParameter("storeId"));
//			HashMap<String, Object> res = new HashMap();
//			res.put("name", request.getParameter("storeId"));
//			masterService.dataCreate("mapper.admin.StoreProductMapper", "create", res);
			
			map = smartStoreService.main(request);
			HashMap<String, Object> param = new HashMap(map);
			param.put("name", request.getParameter("storeId"));
			param.put("writer_name", request.getParameter("writer_name"));

			commmonService.optionList(param);

			map.put("success", true);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
//		Common.returnPrint(Common.GmakeDynamicValueObject(entity));
		return entity; 
	}
	
	@GetMapping("/api/v1/naver/sort/save")  
	public ResponseEntity<Map<String, Object>> mainSort(HttpServletRequest request) throws Exception {
		ResponseEntity<Map<String, Object>> entity = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = naverSortService.main(request);
			HashMap<String, Object> param = new HashMap(map);
			param.put("keyword", request.getParameter("keyword"));
			param.put("type_seq", request.getParameter("type_seq"));
			param.put("sort_type_seq", request.getParameter("sort_type_seq"));
			//List<HashMap<String,Object>> list = (List<HashMap<String, Object>>) new HashMap(map);
			int record = masterService.dataCreate("mapper.admin.ProductMapper", "create", param);
			

			map.put("success", true);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
//		Common.returnPrint(Common.GmakeDynamicValueObject(entity));
		return entity; 
	}
	
	@GetMapping("/api/v1/brand/save")  
	public ResponseEntity<Map<String, Object>> brand(HttpServletRequest request) throws Exception {
		ResponseEntity<Map<String, Object>> entity = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = brandStoreService.main(request);
			HashMap<String, Object> param = new HashMap(map);
		
			commmonService.brandOptionList(param);
			map.put("success", true);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
//		Common.returnPrint(Common.GmakeDynamicValueObject(entity));
		return entity; 
	}

	@GetMapping("/api/v1/site/save")  
	public ResponseEntity<Map<String, Object>> site(HttpServletRequest request) throws Exception {
		ResponseEntity<Map<String, Object>> entity = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = anoterShopService.main();
			HashMap<String, Object> param = new HashMap(map);
			int record = masterService.dataCreate("mapper.admin.SiteProductMapper", "create", param);
				map.put("success", true);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		Common.returnPrint(Common.GmakeDynamicValueObject(entity));
		return entity; 
	}
	
}
