package com.sp.mfboard;

import java.util.List;
import java.util.Map;

public interface MfboardService {

	// 게시판 처리
	// insert mode: 등록, 답변 구분하여 처리
	public int insertBoard(Mfboard dto, String pathname, String mode);
	public int dataCount(Map<String, Object> map);
	public List<Mfboard> listBoard(Map<String, Object> map);
	public List<Mfboard> listBoardTop();
	public int updateHitCount(int num);
	public Mfboard readBoard(int num);
	public Mfboard preReadBoard(Map<String, Object> map);
	public Mfboard nextReadBoard(Map<String, Object> map);
	public int updateBoard(Mfboard dto, String pathname);
	public int deleteBoard(int num, String pathname);
	
	// 파일 처리
	public int insertFile(Mfboard dto);
	public List<Mfboard> listFile(int num);
	public Mfboard readFile(int fileNum);
	public int deleteFile(Map<String, Object> map);
	
	
}
