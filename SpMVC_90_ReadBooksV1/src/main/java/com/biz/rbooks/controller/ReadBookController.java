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
	
	// 모든 독서록 리스트를 보여주는 method
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(Model model) {
		
		List<ReadBookDTO> rbList = rbService.selectAll();
		model.addAttribute("RBLIST", rbList);
		return null;
	}
	
	// 독서록 리스트 한개를 클릭했을때 나오는 detail 화면 method
	@RequestMapping(value="/viewdetail", method=RequestMethod.GET)
	public String viewdetail(@RequestParam("id") String rb_seq, @ModelAttribute("rbDTO") ReadBookDTO rbDTO, Model model) {
		
		rbDTO = rbService.findBySeq(rb_seq);
		model.addAttribute("rbDTO", rbDTO);
		
		return null;
	}

	// 사용자 아이디(httpSession)/독서록 작성 날짜/도서명 세팅
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String insert(@ModelAttribute("rbDTO") ReadBookDTO rbDTO, Model model, SessionStatus status, HttpSession httpSession) {
		
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

	/*
	 * insert POST가 rbDTO를 수신할 때 입력form에서 사용자가 입력한 값들이 있으면
	 * 그 값들을 rbDTO의 필드변수에 setting 하고,
	 * 만약 없으면 메모리 어딘가에 보관중인 SessionAttributes로 설정된 rbDTO변수에서
	 * 값을 가져와서 비어있는 필드변수를 채워서 매개변수에 주입한다.
	 * 
	 * 따라서 form에서 보이지 않아도 되는 값들은
	 * 별도의 코딩을 하지 않아도 자동으로 insert POST로 전송되는 효과를 낸다.
	 * 단, 이 기능을 효율적으로 사용하려면 jsp code에 Spring form tag로 input을 코딩해야한다. 
	 */
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(@ModelAttribute("rbDTO") ReadBookDTO rbDTO, SessionStatus status) {
		
		log.debug("###rbDTO" + rbDTO.toString());
		rbService.insert(rbDTO);
		status.setComplete();
		return "redirect:/rbooks/list";
	}
	
	// update GET method
	/*
	 * @RequestParam : ?변수=값  형식으로 전송하고 변수에서 수신
	 * @PathVariable : path/값   형식으로 전송하고 변수에서 받기
	 * ==> 변수 이름을 노출하지 않아도 됨(일종의 RestController와 비슷)
	 * 근래에는 ?변수=값 보다 이방식을 더 많이 사용
	 */
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String update(@ModelAttribute("rbDTO") ReadBookDTO rbDTO, Model model, @RequestParam("id") String rb_seq, SessionStatus status) {
		
		// update를 위해 pk인 ReadBook table의 seq값을 id로 rbDTO에 넘겨주기
		rbDTO = rbService.findBySeq(rb_seq);
		model.addAttribute("rbDTO", rbDTO);
		status.setComplete();
		return "rbooks/insert";
	}
//	
//	
//	// update POST method
//	@RequestMapping(value="/update", method=RequestMethod.GET)
//	public String update(@ModelAttribute("rbDTO") ReadBookDTO rbDTO, Model model, @RequestParam("id") String rb_seq) {
//		
//		rbDTO = rbService.findByBCode(rb_seq);
//		model.addAttribute("rbDTO", rbDTO);
//		return "rbooks/list";
//	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(@ModelAttribute("rbDTO") ReadBookDTO rbDTO, SessionStatus status) {
		
		rbService.update(rbDTO);
		
		status.setComplete();
		return "redirect:/rbooks/list";
	}
	
	
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String delete(@ModelAttribute("rbDTO") ReadBookDTO rbDTO) {
		
		// seq값을 받아서 삭제 수행
		rbService.delete(rbDTO.getRb_seq());
		return "redirect:/rbooks/list";
	}
	
}
