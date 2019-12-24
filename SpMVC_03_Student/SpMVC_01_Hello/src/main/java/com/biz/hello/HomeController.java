package com.biz.hello;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class HomeController {
	
	@RequestMapping(value = "my", method=RequestMethod.POST)
	public String my(Model model, String st_name, String st_dept) {
		
		System.out.println(st_name);
		System.out.println(st_dept);
		
		return "mypage";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String home(Model model) {
		return "home";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		model.addAttribute("strName","이몽룡이");
		model.addAttribute("strDept","컴퓨터공학과");
		model.addAttribute("strGrade","3");

		return "home";
	}
	
}
