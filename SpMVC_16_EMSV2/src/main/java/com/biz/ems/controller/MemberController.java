package com.biz.ems.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.ems.service.NaverLoginService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping(value="/member")
@Controller
public class MemberController {

	private final NaverLoginService nLoginService;
	
	@RequestMapping(value="/naver", method=RequestMethod.GET)
	public String naver_login() {
		
		String apiURL = nLoginService.oAuthLoginGet();
		return "redirect:" + apiURL;
		
	}
	
}
