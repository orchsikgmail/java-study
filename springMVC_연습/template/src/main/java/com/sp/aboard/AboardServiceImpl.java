package com.sp.aboard;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp.common.dao.CommonDAO;

@Service("aboard.aboardService")
public class AboardServiceImpl implements AboardService {

	@Autowired
	private CommonDAO dao;
	
	@Override
	public List<Aboard> list(Map<String, Object> map) {
		List<Aboard> list = null;
		
		try {
			list = dao.selectList("aboard.list", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public int dataCount(Map<String, Object> map) {
		int result = 0;
		
		try {
			result = dao.selectOne("aboard.dataCount", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public int insert(Aboard dto) {
		int result = 0;
		
		try {
			dto.setNum(dao.selectOne("aboard.seq_nextval"));
			dto.setGroupNum(dto.getNum());
			result = dao.insertData("aboard.insert", dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@Override
	public int answer(Aboard dto) {
		int result = 0;
		
		try {
			dto.setNum(dao.selectOne("aboard.seq_nextval"));
			result = dao.insertData("aboard.insert", dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public Aboard readArticle(long num) {
		Aboard dto = null;
		
		try {
			dto = dao.selectOne("aboard.readArticle", num);
			dao.updateData("aboard.updateHitCount", num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;
	}

	
	@Override
	public int updateArticle(Aboard dto) {
		int result =0;
		
		try {
			result = dao.updateData("aboard.update", dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public Aboard readParent(long num) {
		Aboard dto  = null;
		
		try {
			dto = dao.selectOne("aboard.readParent", num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;
	}

	@Override
	public int readMinOrderNum(Map<String, Object> map) {
		int result = 0;
		
		try {
			result = dao.selectOne("aboard.readMinOrderNum", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public int updateOrderNum(Map<String, Object> map) {
		int result = 0;
		
		try {
			result = dao.updateData("aboard.updateOrderNum", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	
	

	
	
	
	
	
	
	
}
