package com.biz.rbooks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.biz.rbooks.domain.BookDTO;
import com.biz.rbooks.domain.ReadBookDTO;
import com.biz.rbooks.service.BookService;
import com.biz.rbooks.service.ReadBookService;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/books")
@Slf4j
@SessionAttributes("bDTO")
@Controller
public class BookController {

	BookService bService;
	ReadBookService rbService;
	
	@Autowired
	public BookController(BookService bService, ReadBookService rbService) {
		this.bService = bService;
		this.rbService = rbService;
	}

	@ModelAttribute("bDTO")
	public BookDTO newBookDTO() {
		
		return new BookDTO();
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(Model model) {
	
		// List<ReadBookDTO> rbList = rbService.selectAll();
		List<BookDTO> bList = bService.selectAll();
		model.addAttribute("BLIST", bList);
		
		return "home";
	}
	
	@RequestMapping(value="/viewdetail", method=RequestMethod.GET)
	public String viewdetail(@RequestParam("bcode") String b_code, @ModelAttribute("bDTO") BookDTO bDTO, ReadBookDTO rbDTO, Model model) {
		
		bDTO = bService.findById(b_code);
		
		log.debug("rbDTO" + rbDTO);
		model.addAttribute("bDTO", bDTO);
		return null;
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String insert(@ModelAttribute("bDTO") BookDTO bDTO, Model model, SessionStatus status){

		// ISBN 자동생성해서 넣는 코드 추가하기
		// 도서코드가 일치하는 도서에 insert를 수행하면 insert막아주는 코드 (ajax로 Stirng Yes No 식으로 보내기)
		// code check 따로 만들어야함

		model.addAttribute("bDTO", bDTO);
		status.setComplete();
		return "books/insert";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(@ModelAttribute("bDTO") BookDTO bDTO, SessionStatus status) {
		
		bService.insert(bDTO);
		
		log.debug("###insert 값" + bDTO.toString());
		
		status.setComplete();
		return "redirect:/books/list";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String update(@ModelAttribute("bDTO") BookDTO bDTO, Model model, @RequestParam("id") String b_code) {
		
		bDTO = bService.findById(b_code);
		model.addAttribute("bDTO", bDTO);
		
		return "books/insert";
	}

	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(@ModelAttribute("bDTO")BookDTO bDTO, SessionStatus status) {
		
		bService.update(bDTO);
		status.setComplete();
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String delete(@ModelAttribute("bDTO") BookDTO bDTO) {

		bService.delete(bDTO.getB_code());
		
		return "redirect:/books/list";
	}
	
	
}
