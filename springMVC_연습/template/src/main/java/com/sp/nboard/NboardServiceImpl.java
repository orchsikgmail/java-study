package com.sp.nboard;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp.common.dao.CommonDAO;

@Service("nboardService")
public class NboardServiceImpl implements NboardService{

	@Autowired
	private CommonDAO dao;
	
	@Override
	public int insertBoard(Nboard dto) {
		int result=0;
		
		try {
			result = dao.insertData("nBoard.insertNboard", dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public List<Nboard> listBoard(Map<String, Object> map) {
		List<Nboard> listBoard = null;
		
		try {
			listBoard = dao.selectList("nBoard.listNboard", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listBoard;
	}

	@Override
	public Nboard readArticle(int num) {
		Nboard dto = null;
		
		try {
			dto = dao.selectOne("nBoard.readArticle", num);
			dao.updateData("nBoard.plusHitCount", num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;
	}

	@Override
	public int dataCount(Map<String, Object> map) {
		int result = 0;
		
		try {
			result = dao.selectOne("nBoard.dataCount", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public int update(Nboard dto) {
		int result =0 ;
		
		try {
			result = dao.updateData("nBoard.updateBoard", dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public int delete(int num) {
		int result =0;
		
		try {
			result = dao.deleteData("nBoard.deleteBoard", num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	
	
	
	
	
}
