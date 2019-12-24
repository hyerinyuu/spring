package com.biz.student.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// controller에 있는 requestMapping에는 method는 못붙임(value만 가능)
@RequestMapping(value="/member")
@Controller
public class MemberController {

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(Model model) {
		
		model.addAttribute("BODY", "LOGIN");
		return "home";
	}

	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join(Model model) {
		
		model.addAttribute("BODY", "JOIN");
		return "home";
	}

}
