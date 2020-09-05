package com.atoverflow.svt.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("product.productController")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value= "/list", method= RequestMethod.GET)
	public String list(
			Model model
			) {
		
		List<Map<String, Object>> listMap = new ArrayList<>();
		listMap = productService.list();
		
		if(listMap==null) {
			model.addAttribute("listMap",listMap);
			return "product/list";
		}
		
		model.addAttribute("listMap",listMap);
		return "product/list";
	}
	
	
	@RequestMapping(value="/order", method = RequestMethod.POST)
	public String order(
			Model model,
			@RequestParam(value="idList[]") List<String> id_list
			) {
		
		
		List<Map<String, Object>> listMap = new ArrayList<>();
		listMap = productService.orderList(id_list);
		
		if(listMap==null) {
			model.addAttribute("listMap",listMap);
			return "product/list";
		}
		
		int total = 0;
		String ITEM_PRICE = null;
		
		Iterator<Map<String, Object>> it = listMap.iterator();
		while(it.hasNext()) {
			Map<String, Object> map = new HashMap<>();
			map = it.next();
			int item_price = Integer.valueOf(String.valueOf(map.get("ITEM_PRICE")));
			total += item_price; // 총계 구하기
			
			ITEM_PRICE = String.format("%,d", item_price); // 출력을위한 원화 ,붙히기 처리
			map.put("ITEM_PRICE", ITEM_PRICE);
		}
		
		String TOTAL = String.format("%,d", total); // 출력을위한 원화 ,붙히기 처리
		
		model.addAttribute("TOTAL", TOTAL);
		model.addAttribute("listMap",listMap);
		return "product/order";
	}
	
	
	
	
	
	
	
	
	
}
