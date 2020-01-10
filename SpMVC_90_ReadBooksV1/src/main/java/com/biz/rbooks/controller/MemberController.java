package com.biz.rbooks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.rbooks.domain.MemberDTO;
import com.biz.rbooks.service.MemberService;

@RequestMapping(value="/member")
@Controller
public class MemberController {

	private final MemberService mService;

	@Autowired
	public MemberController(MemberService mService) {
		super();
		this.mService = mService;
	}
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join(Model model) {
		
		model.addAttribute("MODAL", "JOIN");
		return "member/join";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(MemberDTO memberDTO, Model model) {
		
		mService.insert(memberDTO);
		return "redirect:/books/list";
	}
	

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		
		return null;
	}
	
	
	
}
