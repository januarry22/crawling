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
public class MainRestController {
	
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
	/**
	 * 브랜드스토어
	 */
	@Inject BrandStoreService brandStoreService;
	
	@Inject
	com.kb.api.service.main.anoterShopService anoterShopService;

	@Inject MasterService masterService;
	
	@PostMapping("/api/v1/naver")  
	public ResponseEntity<Map<String, Object>> main(HttpServletRequest request, @RequestBody HashMap param) throws Exception {
		ResponseEntity<Map<String, Object>> entity = null;
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			map = naverService.main(request,param);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	//	entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		Common.returnPrint(Common.GmakeDynamicValueObject(entity));
		return entity; 
	}

	@GetMapping("/api/v1/test")
	public ResponseEntity<Map<String, Object>> test(HttpServletRequest request) throws Exception {
		ResponseEntity<Map<String, Object>> entity = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = anoterShopService.main();
			//System.out.print("request"+request);
		}catch(Exception e) {
			e.printStackTrace();
		}

		entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
//		Common.returnPrint(Common.GmakeDynamicValueObject(entity));
		return entity;
	}
	
	@GetMapping("/api/v1/smartstore")  
	public ResponseEntity<Map<String, Object>> smartstore(HttpServletRequest request) throws Exception {
		ResponseEntity<Map<String, Object>> entity = null;
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			map = smartStoreService.main(request);
			map.put("success",true);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		Common.returnPrint(Common.GmakeDynamicValueObject(entity));
		return entity; 
	}
	
	@GetMapping("/api/v1/brandstore")  
	public ResponseEntity<Map<String, Object>> brandstore(HttpServletRequest request) throws Exception {
		ResponseEntity<Map<String, Object>> entity = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = brandStoreService.main(request);
		
			map.put("success",true);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		Common.returnPrint(Common.GmakeDynamicValueObject(entity));
		return entity; 
	}
	
	
	@GetMapping("/api/v1/naver/sort")  
	public ResponseEntity<Map<String, Object>> mainSort(HttpServletRequest request) throws Exception {
		ResponseEntity<Map<String, Object>> entity = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = naverSortService.main(request);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
//		Common.returnPrint(Common.GmakeDynamicValueObject(entity));
		return entity; 
	}
	
	@PostMapping("/api/v1/store/remove")  
	public ResponseEntity<Map<String, Object>> remove(HttpServletRequest request, @RequestBody  HashMap param) throws Exception {
		ResponseEntity<Map<String, Object>> entity = null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", false);
		
		int record = masterService.dataDelete("mapper.admin.CommonMapper", "remove", param);
		if(record > 0) map.put("success", true);
		
		try {entity = new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);} 
		catch (Exception e) {entity = new ResponseEntity<Map<String, Object>>(map,HttpStatus.BAD_REQUEST);}
	//	Common.returnPrint(Common.GmakeDynamicValueObject(entity));
		
		return entity;
	}
}
