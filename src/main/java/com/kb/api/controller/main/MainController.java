package com.kb.api.controller.main;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kb.api.service.main.NaverService;
import com.kb.api.service.service.CommonService;
import com.kb.api.service.service.MasterService;

@Controller
public class MainController {  
	
	@Inject NaverService mainService;
	@Inject MasterService masterService;
	@Inject CommonService commmonService;
	
	@RequestMapping(value = "/")
	public String main(Model model, HttpServletResponse response, HttpServletRequest request, HttpSession session) throws Exception {
		return "layout/main/index";
	}
//	
//	@RequestMapping(value = "/naver")
//	public String index(Model model, HttpServletResponse response, HttpServletRequest request, HttpSession session) throws Exception {
//		model.addAttribute("target","꼬마옴므");
//		model.addAttribute("pageSize","30");
//		return "naver";
//	}
//	
//	@RequestMapping(value = "/smartstore")
//	public String smartstore(Model model, HttpServletResponse response, HttpServletRequest request, HttpSession session) throws Exception {
//	
//				
//		return "smartstore";
//	}
//	
//	@RequestMapping(value = "/naver/sort")
//	public String test(Model model, HttpServletResponse response, HttpServletRequest request, HttpSession session) throws Exception {
//		model.addAttribute("keyword","아이패드에어3");
//		model.addAttribute("pageSize","2");
//		model.addAttribute("sort","price_asc");
//		
//		return "naverSort";
//	}
	@RequestMapping(value = "/{type}")
	public String main(Model model, @PathVariable String type) throws Exception {
		if(type.equals("naverSort_ui")) {
			commmonService.commonSortList(model);
		}
		return "layout/page/"+type;
	}
	@RequestMapping(value = "/list/{type_seq}")
	public String mainList(Model model, @PathVariable String type_seq) throws Exception {
//		if(type.equals("naverSort_ui")) {
//			commmonService.dataList(model);
//		}
		if(type_seq.equals("2")||type_seq.equals("1")) {
		commmonService.dataList(model,type_seq);
		}
		if(type_seq.equals("3")) {
			commmonService.dataStoreList(model);
		}
		if(type_seq.equals("4")) {
			commmonService.dataBrandList(model);
			return "layout/page/brandlist";
		}
		return "layout/page/list";
	}
	//스마트 스토어 
	@RequestMapping(value = "/store/{seq}")
	public String storeproductDetail(Model model, @PathVariable String seq) throws Exception {
		
		model.addAttribute("detail", masterService.dataRead("mapper.admin.StoreProductMapper", "detail", seq));
		return "layout/page/detail";
	}

	//브랜드 스토어 
	@RequestMapping(value = "/brand/{seq}")
	public String  brandproductDetail(Model model, @PathVariable String seq) throws Exception {
		
		model.addAttribute("detail", masterService.dataRead("mapper.admin.BrandProductMapper", "detail", seq));
		return "layout/page/detail";
	}
	// 네이버 쇼핑 
	@RequestMapping(value = "/product/{seq}")
	public String productDetail(Model model, @PathVariable String seq) throws Exception {
		
		model.addAttribute("detail", masterService.dataRead("mapper.admin.ProductMapper", "detail", seq));
		return "layout/page/detail";
	}
}
