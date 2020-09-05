package com.sp.nboard;

import java.util.List;
import java.util.Map;

public interface NboardService {
	
	public int insertBoard(Nboard dto);
	public List<Nboard> listBoard(Map<String, Object> map);
	public Nboard readArticle(int num);
	public int dataCount(Map<String, Object> map);
	public int update(Nboard dto);
	public int delete(int num);
	
	
}
