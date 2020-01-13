package com.biz.rbooks.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

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
import com.biz.rbooks.domain.MemberDTO;
import com.biz.rbooks.domain.ReadBookDTO;
import com.biz.rbooks.service.BookService;
import com.biz.rbooks.service.MemberService;
import com.biz.rbooks.service.ReadBookService;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/rbooks")
@Slf4j
@SessionAttributes("rbDTO")
@Controller
public class ReadBookController {

	ReadBookService rbService;
	BookService bService;
	MemberService mService;
	
	@Autowired
	public ReadBookController(ReadBookService rbService,BookService bService, MemberService mService) {

		this.rbService = rbService;
		this.bService = bService;
		this.mService = mService;
	}

	@ModelAttribute("rbDTO")
	public ReadBookDTO newRbDTO() {
		
		return new ReadBookDTO();
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(Model model) {
		
		List<ReadBookDTO> rbList = rbService.selectAll();
		model.addAttribute("RBLIST", rbList);
		return null;
	}
	
	@RequestMapping(value="/viewdetail", method=RequestMethod.GET)
	public String viewdetail(@RequestParam("id") String rb_seq, @ModelAttribute("rbDTO") ReadBookDTO rbDTO, Model model) {
		
		rbDTO = rbService.findBySeq(rb_seq);
		model.addAttribute("rbDTO", rbDTO);
		
		return null;
	}
	
	// 사용자 아이디(httpSession)/독서록 작성 날짜/도서명 세팅
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String insert(@ModelAttribute("rbDTO") ReadBookDTO rbDTO, Model model, @ModelAttribute("bDTO") BookDTO bDTO, SessionStatus status, HttpSession httpSession) {
//		
//		MemberDTO memberDTO = (MemberDTO) httpSession.getAttribute("memberDTO");
//		
//		// 로그인 정보가 없으면
//		if(memberDTO == null) {
//			return "redirect:/member/login";
//		}
		
		// 날짜 세팅
		Date date = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		rbDTO.setRb_date(sd.format(date));
		
		// 오늘 날짜를 입력하지 않으면 오늘 날짜로 세팅해서 보내기
		if(rbDTO.getRb_date().isEmpty()) {
			rbDTO.setRb_date(sd.format(date));
		}
		
		log.debug("##rbDTO" + rbDTO.toString());
		
		status.setComplete();
		
		return "rbooks/insert";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(@ModelAttribute("rbDTO") ReadBookDTO rbDTO, SessionStatus status) {
		
		log.debug("###rbDTO" + rbDTO.toString());
		rbService.insert(rbDTO);
		status.setComplete();
		return "redirect:/rbooks/list";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String update(@ModelAttribute("rbDTO") ReadBookDTO rbDTO, Model model, @RequestParam("id") String rb_seq) {
		
		rbDTO = rbService.findBySeq(rb_seq);
		model.addAttribute("rbDTO", rbDTO);
		return "rbooks/insert";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(@ModelAttribute("rbDTO") ReadBookDTO rbDTO, SessionStatus status) {
		
		rbService.update(rbDTO);
		status.setComplete();
		return "redirect:/rbooks/list";
	}
	
	
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String delete(@ModelAttribute("rbDTO") ReadBookDTO rbDTO) {
		
		rbService.delete(rbDTO.getRb_seq());
		return "redirect:/rbooks/list";
	}
	
}
