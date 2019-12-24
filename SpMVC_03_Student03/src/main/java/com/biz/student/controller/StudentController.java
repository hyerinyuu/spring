package com.biz.student.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class StudentController {

	@RequestMapping(value="/student/list", method=RequestMethod.GET)
	public String list(Model model) {
		
		model.addAttribute("BODY", "STUDENT-LIST");
		
		log.debug("여기는 학생 컨트롤러");
		// return null; // "/student.list.jsp"
		return "home";
	}
	
	
}
