package com.sp.aboard;

import java.util.List;
import java.util.Map;

public interface AboardService {
	
	public List<Aboard> list(Map<String, Object> map);
	public int dataCount(Map<String, Object> map);
	public int insert(Aboard dto);
	public Aboard readArticle(long num);
	public int updateArticle(Aboard dto);
	public Aboard readParent(long num);
	public int readMinOrderNum(Map<String, Object> map);
	public int updateOrderNum(Map<String, Object> map);
	public int answer(Aboard dto);
	
}
