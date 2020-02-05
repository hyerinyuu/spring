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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.biz.bbs.domain.BBsVO;
import com.biz.bbs.domain.CommentVO;
import com.biz.bbs.service.BBsService;
import com.biz.bbs.service.CommentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SessionAttributes("bbsVO")
@RequestMapping(value="/bbs")
@Controller
public class BBsController {

	private final BBsService bbsService;
	private final CommentService cmmService;

	// lombok의 requirearg를 쓰지 않고 original Spring 생성자를 이용해 객체 생성하는 법
	@Autowired
	public BBsController(@Qualifier("bServiceV1")BBsService bbsService, CommentService cmmService) {
		super();
		this.bbsService = bbsService;
		this.cmmService = cmmService;
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
		
		model.addAttribute("bbsVO", bbsVO);
		model.addAttribute("BODY", "BBS_INPUT");
		
		return "home";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String save(@ModelAttribute("bbsVO")BBsVO bbsVO, SessionStatus status) {
		
		bbsService.save(bbsVO);
		status.setComplete();
		return "redirect:/bbs/list";
	}
	
	/*
	 * 		[view method에서 @ModelAttribute를 사용한 이유]
	 * 게시판 상세페이지(view)에서 답글을 작성할 때,
	 * 본문만 작성하는 TextArea box를 두고 나머지 항목들은
	 * 별도로 저장하거나 하지 않아도 답글을 저장했을 때 원글의 내용이 같이 controller로 전송되도록 
	 * 하기 위한 설정(일종의 트릭)
	 * 
	 * @ModelAttribute로 설정 된 bbsVO는 sessionAttributes에 설정이 되어 있기 때문에
	 * model.addAttribute로 만드는 순간 서버 session 메모리에 데이터가 통채로 저장되어있어서 
	 * 다른 method에서 그 값을 참조할 수 있다. 
	 */
	@RequestMapping(value="/view/{bbs_id}", method=RequestMethod.GET)
	public String view(@ModelAttribute("bbsVO") BBsVO bbsVO, Model model, SessionStatus status, @PathVariable("bbs_id") String bbs_id) {
		
		bbsVO = bbsService.findById(bbsVO.getBbs_id());
		
//		bbsVO.setBbs_writer("");
//		bbsVO.setBbs_content("");
		model.addAttribute("BODY", "BBS_VIEW");
		model.addAttribute("bbsVO", bbsVO);
		
		return "home";
	}
	
	@RequestMapping(value="/reply", method=RequestMethod.POST)
	public String reply(@ModelAttribute("bbsVO") BBsVO bbsVO, Model model, SessionStatus status){
		
		LocalDateTime ldt = LocalDateTime.now();
		DateTimeFormatter ld = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter lt = DateTimeFormatter.ofPattern("HH:mm:ss");
	
		bbsVO.setBbs_time(ldt.format(lt));
		bbsVO.setBbs_date(ldt.format(ld));
		
		bbsVO = bbsService.reply(bbsVO);
		
		status.setComplete();
		return "redirect:/bbs/list";
	}
	
	@RequestMapping(value="/cmt_write", method=RequestMethod.POST)
	public String comment(CommentVO cmmVO, Model model) {
		
		log.debug("커멘트 : " + cmmVO.toString());
		int ret = cmmService.insert(cmmVO);
		
		List<CommentVO> cmmList = cmmService.selectAll(cmmVO.getCmt_p_id());
		
		model.addAttribute("CMT_LIST", cmmList);
		return "include/bbs_comment";
	}
	
	@RequestMapping(value="/cmt_list", method=RequestMethod.POST)
	public String cmm_list(Model model, String cmt_p_id) {
		
		long p_id = Long.valueOf(cmt_p_id);
		List<CommentVO> cmmList = cmmService.selectAll(p_id);
		
		model.addAttribute("CMT_LIST", cmmList);
		return "include/bbs_comment";
	}
	
	
	
	
}
