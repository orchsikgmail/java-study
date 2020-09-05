package com.sp.aboard;

import java.io.UnsupportedEncodingException;
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

@Controller("aboard.aBoardController")
public class AboardController {

	@Autowired
	private AboardService aboardService;
	@Autowired
	private MyUtil myutil;
	
	
	@RequestMapping(value="/aboard/list", method = RequestMethod.GET)
	public String list(
			Model model,
			@RequestParam(defaultValue="all") String condition,
			@RequestParam(defaultValue="") String value,
			@RequestParam(value="page", defaultValue="1")int current_page,
			HttpServletRequest request
			) throws UnsupportedEncodingException {
		
		String cp = request.getContextPath();
		
		value = URLEncoder.encode(value, "utf-8");
		
		Map<String, Object> map = new HashMap<>();
		map.put("condition", condition);
		map.put("value", value);
		
		
		int dataCount = 0;
		dataCount = aboardService.dataCount(map);
		
		int rows = 5;
		
		int total_page = myutil.pageCount(rows, dataCount);
		if(current_page>total_page) {
			total_page = current_page;
		}
		
		int start = (rows*(current_page-1))+1;
		int end = rows*current_page;
		map.put("start", start);
		map.put("end", end);
		
		List<Aboard> list = aboardService.list(map);
		
		long listNum =0;
		long n = 0;
		
		for(Aboard dto : list) {
			listNum = dataCount - start - n + 1;
			dto.setListNum(listNum);
			n++;
		}
		
		String article_url = cp+"/aboard/article?page="+current_page;
		String list_url = cp+"/aboard/list";
		String query = null;
		if(value.length()!=0) {
			query = "condition="+condition+"&value="+value;
			article_url += "&"+query;
			list_url += "?"+query;
		}

		String paging = myutil.paging(current_page, total_page, list_url);
		
		model.addAttribute("article_url", article_url);
		model.addAttribute("dataCount", dataCount);
		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("query", query);
		model.addAttribute("total_page", total_page);
		model.addAttribute("page", current_page);
		
		return ".aboard.list";
	}
	
	
	@RequestMapping(value="/aboard/created", method=RequestMethod.GET)
	public String creat(Model model) {
		model.addAttribute("mode", "created");
		return ".aboard.created";
	}
	
	
	@RequestMapping(value="/aboard/created", method=RequestMethod.POST)
	public String creatSubmit(
			Aboard dto,
			Model model,
			HttpServletRequest request
			) {
		
		dto.setIpAddr(request.getRemoteAddr());
		dto.setParent(0);
		dto.setGroupNum(1);
		dto.setOrderNum(1);
		dto.setDepth(1);
		
		int result = 0;
		result = aboardService.insert(dto);
		if(result != 1) {
			model.addAttribute("msg", "게시글 등록에 실패하셨습니다.");
			return "redirect:/aboard/list";
		}
		
		model.addAttribute("msg", "게시글 등록에 성공했습니다.");
		return "redirect:/aboard/list";
	}
	
	
	@RequestMapping(value="/aboard/article", method=RequestMethod.GET)
	public String article(
			@RequestParam(defaultValue="all") String condition,
			@RequestParam(defaultValue="") String value,
			@RequestParam(defaultValue="1") int page,
			@RequestParam(required=true) long num,
			Model model
			) {
		
		Aboard dto = null;
		dto = aboardService.readArticle(num);
		if(dto == null) {
			model.addAttribute("msg", "존재하지 않는 게시글입니다.");
			return "redirect:/aboard/list";
		}
		
		model.addAttribute("dto", dto);
		
		return ".aboard.article";
	}
	
	
	@RequestMapping(value="/aboard/updateForm", method=RequestMethod.POST)
	public String updateForm(
			Model model,
			Aboard dto
			) {
		
		model.addAttribute("dto", dto);
		model.addAttribute("mode", "update");
		
		return ".aboard.created";
	}
	
	@RequestMapping(value="/aboard/update", method=RequestMethod.POST)
	public String update(
			Aboard dto,
			Model model
			) {
		
		
		int result = aboardService.updateArticle(dto);
		if(result!=1) {
			model.addAttribute("msg", "게시글 수정에 실패하셨습니다.");
			return "redirect:/aboard/list";
		}
		
		return "redirect:/aboard/list";
	}
	
	@RequestMapping(value="/aboard/answer", method=RequestMethod.GET)
	public String answer(
			@RequestParam long num,
			Model model
			) {
		
		model.addAttribute("parentNum", num);
		model.addAttribute("mode", "answer");
		
		return ".aboard.created";
	}
	
	@RequestMapping(value="/aboard/answer", method=RequestMethod.POST)
	public String answerSubmit(
			@RequestParam long parentNum,
			Aboard dto,
			Model model,
			HttpServletRequest request
			) {
		
		dto.setIpAddr(request.getRemoteAddr());
		
		Aboard parent = null;
		parent = aboardService.readParent(parentNum);
		
		Map<String, Object> map = new HashMap<>();
		map.put("parentParent", parent.getParent());
		map.put("parentDepth", parent.getDepth());
		map.put("parentNum", parent.getNum());
		
		int minOrderNum = aboardService.readMinOrderNum(map);
		
		dto.setParent(parent.getNum());
		dto.setGroupNum(parent.getGroupNum());
		dto.setOrderNum(minOrderNum);
		dto.setDepth(parent.getDepth()+1);
		
		int result = aboardService.answer(dto);
		if(result!=1) {
			model.addAttribute("msg", "답글 작성이 실패했습니다.");
			return "redirect:/aboard/list";
		}
		
		map.put("orderNum", minOrderNum);
		map.put("groupNum", parent.getGroupNum());
		aboardService.updateOrderNum(map);
		
		return "redirect:/aboard/list";
	}
	
	
	
	
	
	
	
	
	
}
