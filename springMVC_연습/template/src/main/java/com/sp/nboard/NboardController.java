package com.sp.nboard;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sp.common.MyUtil;

@Controller("nBoard")
public class NboardController {

	@Autowired
	NboardService nboardService;
	@Autowired
	MyUtil myUtil;
	
	@RequestMapping(value="/nBoard/list", method=RequestMethod.GET)
	public String list(
				Model model,
				@RequestParam(defaultValue="all") String condition,
				@RequestParam(defaultValue="") String value,
				@RequestParam(value="page", defaultValue="1") int current_page,
				HttpServletRequest request
			) throws UnsupportedEncodingException {
		
		String cp = request.getContextPath();
		value = URLDecoder.decode(value, "UTF-8");
		
		Map<String, Object> listMap = new HashMap<>();
		
		listMap.put("condition", condition);
		listMap.put("value", value);
		
		int dataCount = 0;
		dataCount = nboardService.dataCount(listMap);
		
		int rows=5;
		int total_page=0;
		
		total_page = myUtil.pageCount(rows, dataCount);
		
		if(current_page>total_page) {
			current_page = total_page;
		}

		int start = (current_page-1)*rows+1;
		int end = current_page*rows;
		
		listMap.put("start", start);
		listMap.put("end", end);
		
		List<Nboard> boardList = nboardService.listBoard(listMap);
		
		if(boardList==null || boardList.isEmpty()) {
			return "redirect:/";
		}
		
		int listNum=0;
		int n =0;
		for(Nboard dto : boardList) {
			listNum = dataCount - (start+n-1);
			dto.setListNum(listNum);
			n++;
		}
		
		String list_url = cp+"/nBoard/list";
		String article_url= cp+"/nBoard/article?page="+current_page;
		if(value.length()!=0) {
			String query = "condition="+condition+"&value="+URLEncoder.encode(value, "UTF-8");
			list_url += "?"+query;
			article_url += "&"+query;
		}
		
		String paging = myUtil.paging(current_page, total_page, list_url);
		
		
		model.addAttribute("list_url", list_url);
		model.addAttribute("article_url", article_url);
		model.addAttribute("page", current_page);
		model.addAttribute("total_page", total_page);
		model.addAttribute("dataCount", dataCount);
		model.addAttribute("paging", paging);
		model.addAttribute("boardList", boardList);
		
		return ".nBoard.list";
	}
	
	@RequestMapping(value="/nBoard/created", method=RequestMethod.GET)
	public String created(Model model) {
		
		model.addAttribute("mode", "created");
		
		return ".nBoard.created";
	}
	
	@RequestMapping(value="/nBoard/created", method=RequestMethod.POST)
	public String createdSubmit(
			Model model, Nboard dto,
			HttpServletRequest request
			) {
		
		dto.setIpAddr(request.getRemoteAddr());
		
		int result = nboardService.insertBoard(dto);
		
		if(result !=1) {
			model.addAttribute("msg", "글등록에 실패했습니다.");
			return ".nBoard.list";
		}
		
		model.addAttribute("msg", "게시글 등록에 성공했습니다.");
		return "redirect:/nBoard/list";
		
	}
	
	@RequestMapping(value="/nBoard/article", method=RequestMethod.GET)
	public String article(
			@RequestParam int num,
			@RequestParam(defaultValue="1") int page,
			@RequestParam(defaultValue="all") String condition,
			@RequestParam(defaultValue="") String value,
			Model model
			) {
		
		Nboard dto = nboardService.readArticle(num);
		if(dto==null) {
			model.addAttribute("msg", "접근 불가 게시글입니다.");
			return "redirect:/nBoard/list";
		}
		
		String query = "page="+page+"&condition="+condition+"&value="+value;
		
		
		model.addAttribute("dto", dto);
		model.addAttribute("query", query);
		
		return ".nBoard.article";
	}
	
	@RequestMapping(value="/nBoard/updateForm", method=RequestMethod.POST)
	public String updateForm(Model model, Nboard dto) {
		
		model.addAttribute("mode", "update");
		model.addAttribute("dto", dto);
		
		return ".nBoard.created";
	}
	
	@RequestMapping(value="/nBoard/update", method=RequestMethod.POST)
	public String update(
			Nboard dto,
			HttpServletRequest request,
			Model model
			) {
		
		dto.setIpAddr(request.getRemoteAddr());
		
		int result = nboardService.update(dto);
		if(result!=1) {
			model.addAttribute("msg", "글 수정에 실패했습니다.");
			return "redirect:/nBoard/list";
		}
		
		model.addAttribute("msg", "게시글이 수정되었습니다.");
		return "redirect:/nBoard/list";
	}
	
	@RequestMapping(value="/nBoard/delete", method=RequestMethod.GET)
	public String delete(
			@RequestParam int num
			) {
		int result = 0;
		
		result = nboardService.delete(num);
		if(result!=1) {
			return "redirect:/nBoard/list";
		}
		
		return "redirect:/nBoard/list";
	}
	
}
