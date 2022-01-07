package com.kb.api.service.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class CommonService {
	
	@Inject MasterService masterService;
	
	public void commonSortList(Model model) {

		model.addAttribute("sort",(List<HashMap<String,Object>>) masterService.dataList("mapper.admin.CommonMapper", "sort", null));
		
	}
	public void dataList(Model model,String type_seq) {
		
		model.addAttribute("list",(List<HashMap<String,Object>>) masterService.dataList("mapper.admin.ProductMapper", "list", type_seq));
		
	}
	
	public void dataStoreList(Model model) {
		
		model.addAttribute("list",(List<HashMap<String,Object>>) masterService.dataList("mapper.admin.StoreProductMapper", "list", null));
		
	}
	public void dataBrandList(Model model) {
		
		model.addAttribute("list",(List<HashMap<String,Object>>) masterService.dataList("mapper.admin.BrandProductMapper", "list", null));
		
	}

	public void optionList(Map<String,Object> options) {
		int record = 0;
        List<Map<String, Object>> rstList = new ArrayList<>();
        rstList = (List<Map<String, Object>>) options.get("data");

    	record += masterService.dataCreate("mapper.admin.StoreProductMapper", "store_create", options);
        for(int i =0; i<rstList.size(); i++) {
        	Map<String, Object> res = new HashMap();
        	res = rstList.get(i);
        	res.put("store_seq", options.get("store_seq"));
        	record += masterService.dataCreate("mapper.admin.StoreProductMapper", "create", res);

        	List<Map<String, Object>> retunr = new ArrayList<>();
        	retunr = (List<Map<String, Object>>) res.get("options");
            for(int j =0; j<retunr.size(); j++) {
            	Map<String, Object> param = new HashMap();
            	param = retunr.get(j);
            	param.put("product_seq", rstList.get(i).get("product_seq"));
    		    record += masterService.dataCreate("mapper.admin.StoreProductMapper", "option_create",param);
           }
        }
	}
	
	public void brandOptionList(Map<String,Object> options) {
		int record = 0;
        List<Map<String, Object>> rstList = new ArrayList<>();
        rstList = (List<Map<String, Object>>) options.get("data");
        for(int i =0; i<rstList.size(); i++) {
        	Map<String, Object> res = new HashMap();
        	res = rstList.get(i);
        	record += masterService.dataCreate("mapper.admin.BrandProductMapper", "create", res);

        	List<Map<String, Object>> retunr = new ArrayList<>();
        	retunr = (List<Map<String, Object>>) res.get("options");
            for(int j =0; j<retunr.size(); j++) {
            	Map<String, Object> param = new HashMap();
            	param = retunr.get(j);
            	param.put("product_seq", rstList.get(i).get("product_seq"));
    		    record += masterService.dataCreate("mapper.admin.BrandProductMapper", "option_create",param);
           }
        }
	}
}
