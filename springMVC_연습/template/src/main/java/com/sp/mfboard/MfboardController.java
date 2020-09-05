package com.sp.mfboard;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sp.common.FileManager;
import com.sp.common.MyUtil;

@Controller("mfboard.mfboardController")
public class MfboardController {

	@Autowired
	private MfboardService boardservice;
	@Autowired
	private MyUtil myutil;
	@Autowired
	private FileManager filemanager;
	
	
	@RequestMapping(value="/mfboard/list")
	public String list(
			@RequestParam(value="page", defaultValue="1") int current_page,
			@RequestParam(defaultValue="all") String condition,
			@RequestParam(defaultValue="") String keyword,
			Model model,
			HttpServletRequest req
			) throws UnsupportedEncodingException {
		
		String cp = req.getContextPath();

		if(req.getMethod().equalsIgnoreCase("GET")) {
			keyword = URLDecoder.decode(keyword, "utf-8");
		}
		
		Map<String , Object> map = new HashMap<>();
		map.put("condition", condition);
		map.put("keyword", keyword);
		
		// 페이징 처리 요소 구하기
		int rows = 5;
		int total_page = 0;
		int dataCount = 0;
		
		dataCount = boardservice.dataCount(map);
		if(dataCount != 0) {
			total_page = myutil.pageCount(rows, dataCount);
		}
		
		if(total_page<current_page) {
			current_page = total_page;
		}
		
		// 특정 페이지에 출력할 리스트의 범위
		int start = (current_page-1)*rows+1;
		int end = current_page*rows;
		map.put("start", start);
		map.put("end", end);
		
		// 쿼리문
		String query = "";
		String list_url = cp + "/mfboard/list";
		String article_url = cp + "/mfboard/article?page="+current_page;
		
		if(keyword.length()!=0) {
			query = "condition="+condition+"&keyword="+URLEncoder.encode(keyword, "utf-8");
			list_url = cp+"/mfboard/list?"+query;
			article_url = cp+"/mfboard/article?page="+current_page+"&"+query;
		}
		
		// paging
		String paging = myutil.paging(current_page, total_page, list_url);
		
		// 글 리스트
		List<Mfboard> list = boardservice.listBoard(map);
		
		// 리스트 번호
		int listNum = 0;
		int n = 0;
		for(Mfboard dto : list) {
			listNum = dataCount - start - n + 1;
			dto.setListNum(listNum);
			n++;
		}
				
		// 1페이지인 경우 상단 공지리스트 가져오기
		List<Mfboard> noticeList = null;
		if(current_page == 1) {
			noticeList = boardservice.listBoardTop();
		}
		
		model.addAttribute("noticeList", noticeList);
		model.addAttribute("list", list);
		model.addAttribute("article_url", article_url);
		model.addAttribute("page", current_page);
		model.addAttribute("total_page", total_page);
		model.addAttribute("dataCount", dataCount);
		model.addAttribute("paging", paging);
		model.addAttribute("condition", condition);
		model.addAttribute("keyword", keyword);
		
		return ".mfboard.list";
	}
	
	@RequestMapping(value="/mfboard/created", method=RequestMethod.GET)
	public String createdForm(Model model) {
		model.addAttribute("mode", "created");
		return ".mfboard.created";
	}
	
	@RequestMapping(value="/mfboard/created", method=RequestMethod.POST)
	public String createdSubmit(Mfboard dto, HttpSession session) {
		
		String root = session.getServletContext().getRealPath("/");
		String pathname = root + File.separator + "uploads" + File.separator +"mfboard";
		
		boardservice.insertBoard(dto, pathname, "created");
		
		return "redirect:/mfboard/list";
	}
	
	@RequestMapping(value="/mfboard/article", method=RequestMethod.GET)
	public String readArticle(
			@RequestParam int num,
			@RequestParam String page,
			@RequestParam(defaultValue="all") String condition,
			@RequestParam(defaultValue="") String keyword,
			Model model
			) throws UnsupportedEncodingException {
	
		// 검색한 경우 검색쿼리 생성
		keyword = URLDecoder.decode(keyword, "utf-8");
		String query = "page="+page;
		
		if(keyword.length()!=0) {
			query += "&condition="+condition+"&keyword="+URLEncoder.encode(keyword, "utf-8");
		}
		
		// 게시글 읽기
		Mfboard dto = boardservice.readBoard(num);
		if(dto==null) {
			return "redirect:/mfboard/list?"+query;
		}
		
		// 파일 처리
		List<Mfboard> listFile = boardservice.listFile(num);
				
		/* html에 view될 수 있도록 특수문자 등 처리
			input태크, textarea태크의 값으로 주는 경우에는 필요없다.
			dto.setContent(dto.getContent().replaceAll("\n", "<br>"));
			dto.setContent(myutil.htmlSymbols(dto.getContent()));
		 */
		
		// 조회수 증가
		boardservice.updateHitCount(num);
		
		// 이전글,다음글
		Map<String, Object> map = new HashMap<>();
		map.put("condition", condition);
		map.put("keyword", keyword);
		map.put("groupNum", dto.getGroupNum());
		map.put("orderNum", dto.getOrderNum());
		
		Mfboard preReadDto = boardservice.preReadBoard(map);
		Mfboard nextReadDto = boardservice.nextReadBoard(map);
		
		model.addAttribute("dto", dto);
		model.addAttribute("preReadDto", preReadDto);
		model.addAttribute("nextReadDto", nextReadDto);
		model.addAttribute("listFile", listFile);
		model.addAttribute("page", page);
		model.addAttribute("query", query);
		
		return ".mfboard.article";
	}
	
	
	@RequestMapping(value="/mfboard/download", method=RequestMethod.GET)
	public void download(
			@RequestParam int fileNum,
			HttpServletResponse response,
			HttpSession session
			) {
		
		String root = session.getServletContext().getRealPath("/");
		String pathname = root + File.separator + "uploads" + File.separator + "mfboard";
		
		boolean isDownloaded = false;
		
		Mfboard dto = boardservice.readFile(fileNum);
		if(dto!=null) {
			String saveFilename = dto.getSaveFilename();
			String originalFilename = dto.getOriginalFilename();
			
			isDownloaded = filemanager.doFileDownload(pathname, saveFilename, originalFilename, response);
		}
		
		if(! isDownloaded) {
			try {
				response.setContentType("text/html; charset=utf-8"); 
				PrintWriter writer  = response.getWriter();
				writer.println("<script>alert('파일 다운로드가 불가능합니다.');history.back();</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@RequestMapping(value="/mfboard/update", method=RequestMethod.GET)
	public String updateForm(
			@RequestParam int num,
			@RequestParam String page,
			Model model
			) {
		
		Mfboard dto = boardservice.readBoard(num);
		if(dto==null) {
			return "redirect:/mfboard/list?page="+page;
		}
		
		List<Mfboard> listFile = boardservice.listFile(num);
		
		model.addAttribute("mode", "update");
		model.addAttribute("page", page);
		model.addAttribute("dto", dto);
		model.addAttribute("listFile", listFile);
		
		return ".mfboard.created";
	}
	
	@RequestMapping(value="/mfboard/update", method=RequestMethod.POST)
	public String updateSubmit(
			Mfboard dto,
			@RequestParam String page,
			HttpSession session
			) {

		String root = session.getServletContext().getRealPath("/");
		String pathname = root + File.separator + "uploads" + File.separator +"mfboard";
		
		boardservice.updateBoard(dto, pathname);
		
		return "redirect:/mfboard/list?page="+page;
	}
	
	@RequestMapping(value="mfboard/deleteFile", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteFile(
			@RequestParam int fileNum,
			HttpServletResponse res,
			HttpSession session
			){
		
		String root = session.getServletContext().getRealPath("/");
		String pathname = root + File.separator + "uploads" + File.separator + "mfboard";
		
		Mfboard dto = boardservice.readFile(fileNum);
		if(dto!=null) {
			filemanager.doFileDelete(dto.getSaveFilename(), pathname);
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("field", "fileNum");
		map.put("num", fileNum);
		boardservice.deleteFile(map);
		
		// 작업 결과를 json으로 전송
		Map<String, Object> model = new HashMap<>();
		model.put("stae", "true");
		
		return model;
	}
	
	@RequestMapping(value="/mfboard/reply", method = RequestMethod.GET)
	public String replyForm(
			@RequestParam int num,
			@RequestParam String page,
			Model model
			) {
		
		Mfboard dto = boardservice.readBoard(num);
		if(dto==null) {
			return "redirect:/mfboard/list?page="+page;
		}
		
		dto.setNotice(0);
		dto.setName("");
		dto.setSubject("답] "+dto.getSubject());
		
		String replyComment = "["+dto.getSubject()+"]에 대한 답변입니다.\n";
		dto.setContent(replyComment);
		
		model.addAttribute("mode", "reply");
		model.addAttribute("dto", dto);
		model.addAttribute("page", page);
		
		return ".mfboard.created";
	}
	
	@RequestMapping(value="/mfboard/reply", method = RequestMethod.POST)
	public String replySubmit(
			Mfboard dto,
			String page,
			HttpServletRequest req,
			HttpSession session
			) {
		
		String root = session.getServletContext().getRealPath("/");
		String pathname = root + File.separator + "uploads" + File.separator + "mfboard";
		
		boardservice.insertBoard(dto, pathname, "reply");
		
		return "redirect:/mfboard/list?page="+page;
	}
	
	@RequestMapping(value="/mfboard/delete", method=RequestMethod.GET)
	public String delete(
			@RequestParam int num,
			@RequestParam String page,
			@RequestParam(defaultValue="all") String condition,
			@RequestParam(defaultValue="") String keyword,
			HttpSession session
			) throws UnsupportedEncodingException {
		
		String query = "page"+page;

		keyword = URLDecoder.decode(keyword, "utf-8");
		if(keyword.length()!=0) {
			query += "&condition="+condition+"&keyword="+URLEncoder.encode(keyword, "utf-8");
		}
		
		String root = session.getServletContext().getRealPath("/");
		String pathname = root + File.separator + "uploads" + File.separator +"mfboard";
		
		boardservice.deleteBoard(num, pathname);
		
		return "redirect:/mfboard/list?"+query;
	}
	
	
	
	
	
}
