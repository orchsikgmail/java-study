package com.atoverflow.svt.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atoverflow.svt.commo.dao.CommonDAO;

@Service("product.productService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	private CommonDAO dao;


	@Override
	public List<Map<String, Object>> list() {
		List list = new ArrayList<>();

		try {
			list = dao.selectList("product.productList");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}


	@Override
	public List<Map<String, Object>> orderList(List<String> list) {
		List orderList = new ArrayList<>();

		try {
			orderList = dao.selectList("product.orderList", list);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return orderList;
	}






}
