package com.biz.gallery.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biz.gallery.domain.MemberVO;
import com.biz.gallery.service.MemberService;

@RequestMapping(value="/member")
@Controller
public class MemberController {
	
	private final MemberService mService;
	
	@Autowired
	public MemberController(MemberService mService) {
		this.mService = mService;
	}
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join(Model model) {
		
		model.addAttribute("MODAL", "JOIN");
		return "home";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(MemberVO memberVO, Model model) {
		
		mService.insert(memberVO);
		return "redirect:/image/list";

	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		
		return "body/login";
	}
	
	
	// @ResponseBody
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(MemberVO memberVO, Model model, HttpSession httpSession) {

		// 입력받은 id가 DB에 있는 id값 중 일치하는 값이 있나 확인해보기 
		memberVO = mService.loginCheck(memberVO);
		
		// Service에서 검사받은 memberVO가 null이 아니면 == 회원가입되었으면
		if(memberVO != null) {
			httpSession.setAttribute("MEMBER", memberVO);
			// return "LOGIN_OK";
		// 혹시 로그인이 안되었을 경우 session 제거해줘야함
		}else {
			httpSession.removeAttribute("MEMBER");
			// return "LOGIN_FAIL";
		}
			
		return "redirect:/image/list";
	
	}
	
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession httpSession) {
		
		httpSession.removeAttribute("MEMBER");
		return "redirect:/image/list";
	}
	
	
	
}
