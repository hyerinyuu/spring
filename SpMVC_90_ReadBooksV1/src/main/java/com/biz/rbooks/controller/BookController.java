package com.biz.rbooks.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import com.biz.rbooks.domain.BookDTO;
import com.biz.rbooks.domain.ReadBookDTO;
import com.biz.rbooks.service.BookService;

// @SessionAttributes("bDTO")
@Controller
public class BookController {

	BookService bService;
	
	@Autowired
	public BookController(BookService bService) {
		this.bService = bService;
	}
	
	@RequestMapping(value="books/list", method=RequestMethod.GET)
	public String list(Model model) {
	
		// List<ReadBookDTO> rbList = rbService.selectAll();
		List<BookDTO> bList = bService.selectAll();
		model.addAttribute("BLIST", bList);
		
		return "home";
	}
	
	@RequestMapping(value="books/viewdetail", method=RequestMethod.GET)
	public String viewdetail(@RequestParam("bcode") String b_code, @ModelAttribute("bDTO") BookDTO bDTO, Model model) {
		
		bDTO = bService.selectById(b_code);
		model.addAttribute("bDTO", bDTO);
		
		return null;
	}
	
	@RequestMapping(value="books/insert", method=RequestMethod.GET)
	public String insert(@ModelAttribute("bDTO") ReadBookDTO rbDTO, Model model){

		Date date = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		
		// 독서날짜 setting
		rbDTO.setRb_date(sd.format(date));
		
		// userID setting
		// mDTO.setM_id(m_id);
		
		model.addAttribute("rbDTO", rbDTO);
		return "books/insert";
	}
	
	@RequestMapping(value="books/insert", method=RequestMethod.POST)
	public String insert(@ModelAttribute("bDTO") BookDTO bDTO, Model model) {
		
		int ret = bService.insert(bDTO);
		
		return "redirect:/";
	}
	
	
	
}
