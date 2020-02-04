package com.biz.bbs.controller;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.biz.bbs.domain.BBsVO;
import com.biz.bbs.service.BBsService;

@SessionAttributes("bbsVO")
@RequestMapping(value="/bbs")
@Controller
public class BBsController {

	private final BBsService bbsService;

	// lombok의 requirearg를 쓰지 않고 original Spring 생성자를 이용해 객체 생성하는 법
	@Autowired
	public BBsController(@Qualifier("bServiceV1")BBsService bbsService) {
		super();
		this.bbsService = bbsService;
	}

	@ModelAttribute("bbsVO")
	public BBsVO makeBBsVO() {
		return new BBsVO();
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String selectAll(Model model) {
		
		List<BBsVO> bbsList = bbsService.selectAll();
		model.addAttribute("BBSLIST", bbsList);
		// 홈에서 바로 받기 위해서
		model.addAttribute("BODY", "BBS_LIST");
		return "home";
	}
	
	@RequestMapping(value="/input", method=RequestMethod.GET)
	public String input(Model model) {
		
		// 작성날짜, 시간 세팅
		// java 1.8이상에서 등장한 새로운 날짜/시간 class
		LocalDateTime ld = LocalDateTime.now();
		LocalTime lt = LocalTime.now(); 
		DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm:ss");
		DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		
		BBsVO bbsVO = BBsVO.builder().bbs_date(ld.format(date)).bbs_time(lt.format(time)).build();
		
		model.addAttribute("BBSVO", bbsVO);
		model.addAttribute("BODY", "BBS_INPUT");
		
		return "home";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String save(@ModelAttribute("bbsVO")BBsVO bbsVO) {
		
		bbsService.save(bbsVO);
		return "redirect:/bbs/list";
	}
	
	@RequestMapping(value="/view/{bbs_id}", method=RequestMethod.GET)
	public String view(@ModelAttribute("bbsVO") BBsVO bbsVO, Model model, SessionStatus status, @PathVariable("bbs_id") String bbs_id) {
		
		bbsVO = bbsService.findById(bbsVO.getBbs_id());
		
		model.addAttribute("BODY", "BBS_VIEW");
		model.addAttribute("bbsVO", bbsVO);
		
		return "home";
	}
	
	@RequestMapping(value="/reply", method=RequestMethod.POST)
	public String reply(@ModelAttribute("bbsVO") BBsVO bbsVO, Model model){

		bbsVO = bbsService.reply(bbsVO);
		return "redirect:/bbs/list";
	}
	
	
	
	
}
