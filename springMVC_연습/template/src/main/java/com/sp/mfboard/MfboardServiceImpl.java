package com.sp.mfboard;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sp.common.FileManager;
import com.sp.common.dao.CommonDAO;

@Service("mfboard.mfboardService")
public class MfboardServiceImpl implements MfboardService {

	@Autowired
	private CommonDAO dao;
	@Autowired
	private FileManager filemanager;
	
	
	@Override
	public int insertBoard(Mfboard dto, String pathname, String mode) {
		int result = 0;
		
		try {
			int maxNum = dao.selectOne("mfboard.maxNum");
			dto.setNum(maxNum+1);

			if(mode.equals("created")) {
				
				dto.setParent(0);
				dto.setGroupNum(dto.getNum());
				dto.setDepth(0);
				dto.setOrderNum(0);
			} else if(mode.equals("reply")) {
				// orderNum 변경
				Map<String, Object> map = new HashMap<>();
				map.put("groupNum", dto.getGroupNum());
				map.put("orderNum", dto.getOrderNum());
				dao.updateData("mfboard.updateOrderNum", map);
				
				dto.setDepth(dto.getDepth()+1);
				dto.setOrderNum(dto.getOrderNum()+1);
				// groupNum, parent는 created.jsp 변경해서 넘겨줬다.
			}
			
			result = dao.insertData("mfboard.insertBoard", dto);
			
			// 파일 업로드
			if(! dto.getUpload().isEmpty()) {
				for(MultipartFile mf : dto.getUpload()) {
					if(mf.isEmpty()) {
						continue;
					}
					
					String saveFilename = filemanager.doFileUpload(mf, pathname);
					
					if(saveFilename!=null) {
						String originalFilename = mf.getOriginalFilename();
						long fileSize = mf.getSize();
						
						dto.setOriginalFilename(originalFilename);
						dto.setSaveFilename(saveFilename);
						dto.setFileSize(fileSize);
						
						insertFile(dto);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public int insertFile(Mfboard dto) {
		int result = 0;
		
		try {
			result = dao.insertData("mfboard.insertFile", dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	
	@Override
	public int dataCount(Map<String, Object> map) {
		int result = 0;
		
		try {
			result = dao.selectOne("mfboard.dataCount", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@Override
	public List<Mfboard> listBoard(Map<String, Object> map) {
		List<Mfboard> list = null;
		
		try {
			list = dao.selectList("mfboard.listBoard", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public List<Mfboard> listBoardTop() {
		List<Mfboard> list = null;
		
		try {
			list = dao.selectList("mfboard.listBoardTop");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	@Override
	public Mfboard readBoard(int num) {
		Mfboard dto = null;
		
		try {
			dto = dao.selectOne("mfboard.readMfboard", num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;
	}
	
	@Override
	public List<Mfboard> listFile(int num) {
		List<Mfboard> listFile = null;
		
		try {
			listFile = dao.selectList("mfboard.listFile", num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listFile;
	}
	
	@Override
	public int updateHitCount(int num) {
		int result = 0;
		
		try {
			result = dao.updateData("mfboard.updateHitCount", num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public Mfboard preReadBoard(Map<String, Object> map) {
		Mfboard dto = null;
		
		try {
			dto = dao.selectOne("mfboard.preReadBoard", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;
	}

	@Override
	public Mfboard nextReadBoard(Map<String, Object> map) {
		Mfboard dto = null;
		
		try {
			dto = dao.selectOne("mfboard.nextReadBoard", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;
	}

	@Override
	public Mfboard readFile(int fileNum) {
		Mfboard dto = null;
		
		try {
			dto = dao.selectOne("mfboard.readFile", fileNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;
	}
	
	@Override
	public int updateBoard(Mfboard dto, String pathname) {
		int result = 0;
		
		try {
			result = dao.updateData("mfboard.updateBoard", dto);
			
			if(! dto.getUpload().isEmpty()) {
				for(MultipartFile mf : dto.getUpload()) {
					if(mf.isEmpty()) {
						continue;
					}
					
					String saveFilename = filemanager.doFileUpload(mf, pathname);
					if(saveFilename!=null) {
						String originalFilename = mf.getOriginalFilename();
						long fileSize = mf.getSize();
						
						dto.setOriginalFilename(originalFilename);
						dto.setSaveFilename(saveFilename);
						dto.setFileSize(fileSize);
						
						insertFile(dto);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@Override
	public int deleteFile(Map<String, Object> map) {
		int result = 0;
		
		try {
			result = dao.deleteData("mfboard.deleteFile", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@Override
	public int deleteBoard(int num, String pathname) {
		int result = 0;
		
		try {
			//파일 지우기
			List<Mfboard> listFile = listFile(num);
			if(listFile!=null) {
				Iterator<Mfboard> boardIterator = listFile.iterator();
				
				while(boardIterator.hasNext()) {
					Mfboard dto = boardIterator.next();
					filemanager.doFileDelete(dto.getSaveFilename(), pathname);
				}
			}
			
			// 파일테이블 내용 지우기
			Map<String, Object> map = new HashMap<>();
			map.put("field", "num");
			map.put("num", num);
			
			// 파일테이블 삭제
			deleteFile(map);
			// 게시판테이블 삭제
			result = dao.deleteData("mfboard.deleteBoard", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	
}
