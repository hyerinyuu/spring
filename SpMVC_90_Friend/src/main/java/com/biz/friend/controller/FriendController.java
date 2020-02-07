package com.biz.friend.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.biz.friend.domain.FriendVO;
import com.biz.friend.service.FriendService;

import lombok.RequiredArgsConstructor;

@SessionAttributes("fVO")
@RequestMapping(value="/friend")
@RequiredArgsConstructor
@Controller
public class FriendController {

	private final FriendService fService;
	
	@ModelAttribute("fVO")
	public FriendVO makefVO() {
		return new FriendVO();
	}
	
//	@RequestMapping(value="/list", method=RequestMethod.GET)
//	public String selectAll(Model model) {
//		
//		List<FriendVO> fList = fService.selectAll();
//		
//		model.addAttribute("FLIST", fList);
//		return "home";
//	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(@RequestParam(name="searchtype", required = false) String searchtype, @RequestParam(name="keyword", required=false) String keyword, Model model) {
		
		List<FriendVO> fList;
		if(keyword == null || keyword.isEmpty()) {
			fList = fService.selectAll();
		}else{
			fList = fService.search(searchtype, keyword);
		}

		model.addAttribute("FLIST", fList);
		return "home";
	}
	
	
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String insert(Model model) {
		
		return "friend/insert";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(@ModelAttribute("fVO") FriendVO fVO, Model model, SessionStatus status) {
		
		int ret = fService.insert(fVO);
		model.addAttribute("BODY", "INSERT");
		
		status.setComplete();
		return "redirect:/";
	}
	
	@RequestMapping(value="/view/{f_id}", method=RequestMethod.GET)
	public String view(@ModelAttribute("fVO") FriendVO fVO, @PathVariable("f_id") String f_id, Model model, SessionStatus status) {
		
		fVO = fService.findById(Long.valueOf(f_id));
		
		model.addAttribute("BODY", "VIEW");
		model.addAttribute("fVO", fVO);
		status.setComplete();
		
		return "home";
		
	}
	
	@RequestMapping(value="/update/{f_id}", method=RequestMethod.GET)
	public String update(@ModelAttribute("fVO") FriendVO fVO, @PathVariable("f_id") String f_id, Model model) {
		
		fVO = fService.findById(Long.valueOf(f_id));
		model.addAttribute("BODY", "INSERT");
		model.addAttribute("fVO", fVO);
		
		return "home";
	}
	
	@RequestMapping(value="/update/{f_id}", method=RequestMethod.POST)
	public String update(@ModelAttribute("fVO") FriendVO fVO) {
		
		int ret = fService.update(fVO);
		return "redirect:/friend/list";
	}
	
	@RequestMapping(value="/delete/{f_id}", method=RequestMethod.GET)
	public String delete(@PathVariable("f_id") String f_id) {
		
		int ret = fService.delete(Long.valueOf(f_id));
		return "redirect:/friend/list";
	}
	
}
