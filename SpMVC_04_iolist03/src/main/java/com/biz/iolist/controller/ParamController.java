package com.biz.iolist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value="/param")
@Controller
public class ParamController {

	@RequestMapping(value="/view", method=RequestMethod.GET)
	public String view() {
		
		
		return null; // == return "/param/view"
	}
	
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String update(String code) {
		
		return null; // == return "/param/update"
		
	}

	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(String id, String code, Model model) {
		
		/*
		 * 매개변수는 기본값이 String이라서 만약 매개변수로 int를 사용하면
		 * 값이 넘어오면서 형변환이 자동으로 시도되는데 여기서 오류가 발생함
		 * 그래서 int형을 매개변수로 사용하고 싶으면
		 * 매개변수를 String형으로 선언 후 
		 * int 형변수를 따로 선언해주고 Integer로 형변환을 해준 후 try catch로 묶어준다.
		 */
		
		
		System.out.println(code);
		System.out.println(id);
		return null; // == return "/param/update"
		
	}

}
