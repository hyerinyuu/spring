package com.biz.rbooks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.rbooks.domain.ReadBookVO;

@RequestMapping("/rbook")
@Controller
public class ReadBookController {

	// 독서록 추가 버튼을 입력했을때 rbooks/write로 넘어가서 입력form을 보여주기 위한 method(GET)
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String insert(Model model) {
		
		ReadBookVO rBookVO = new ReadBookVO();
		model.addAttribute("rBookVO", rBookVO);
		
		return "rbooks/input";
	}
	
}
