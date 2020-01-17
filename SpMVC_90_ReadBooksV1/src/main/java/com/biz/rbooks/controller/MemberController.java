package com.biz.rbooks.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biz.rbooks.domain.MemberDTO;
import com.biz.rbooks.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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

	@ResponseBody
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@RequestParam("u_id") String u_id, @RequestParam("u_password") String u_password, Model model, HttpSession httpSession) {
		
		MemberDTO memberDTO = mService.logincheck(u_id, u_password);
		
		if(memberDTO != null) {
			httpSession.setAttribute("MEMBER", memberDTO);
			return "LOGIN_OK";
					
		}else {
			httpSession.removeAttribute("MEMBER");
			return "LOGIN_FAIL";
		}
		// return "redirect:/books/list";
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession httpSession) {
		httpSession.removeAttribute("MEMBER");
		
		return "redirect:/rbooks/list";
		
	}
	
	
	
}
