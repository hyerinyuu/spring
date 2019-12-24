package com.biz.memo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.biz.memo.domain.MemoDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
// session을 여러개를 사용하고 싶으면 배열로 사용해주면 됨
// ex) @SessionAttribute({"memoDTO", "mDTO"})
@SessionAttributes({"memoDTO", "mDTO"})
@RequestMapping(value="/session")
@Controller
public class SessionController {
	
	/*
	 * 보통의 자바 코드에서는 아래와같이 생성자를 만들 수 있으나 Spring에서는 불가해서
	 * 생성자method를 modelAttribute로 생성해줘야함
	MemoDTO memoDTO;
	public SessionController() {
		memoDTO = new MemoDTO();
	}
	*/
	
	// return type을 클래스로 해주고 modelAttribute Annotaion 붙여주기
	// == memoDTO = new MemoDTO()의 역할을 수행할 초기화 코드임
	@ModelAttribute("memoDTO")
	public MemoDTO newMemoDTO(){
		
		return new MemoDTO();
	}
	
	@ModelAttribute("mDTO")
	public MemoDTO newMDTO() {
		
		return new MemoDTO();
		
	}

	// @ModelAttribute에서 생성된 인스턴스를 찾아서 MemoDTO memoDTO에 담아줌.
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String insert(@RequestParam("id") String str_seq, @ModelAttribute("memoDTO") MemoDTO memoDTO, Model model) {
		
		memoDTO.setM_seq(8888);
		memoDTO.setM_auth("유혜린");
		memoDTO.setM_date("2019-12-03");
		
		model.addAttribute("memoDTO", memoDTO);
		
		return "insert";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(@ModelAttribute("memoDTO") MemoDTO memoDTO, Model model) {
		
		log.debug("시퀀스 : " + memoDTO.getM_seq());
		return "redirect:/memo/list";
	}
	

}
