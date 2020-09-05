package com.atoverflow.svt.product;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service("product.productService")
public interface ProductService {

	public List<Map<String, Object>> list();
	public List<Map<String, Object>> orderList(List<String> id_list);
	
	
	
}
