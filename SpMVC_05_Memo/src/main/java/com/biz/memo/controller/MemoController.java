package com.biz.memo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.biz.memo.domain.MemoDTO;
import com.biz.memo.service.MemoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j

/*
 * 메모리에 복사한 값을 넣어놨다가 post에서 input박스에서 세팅한값을 받을 때
 * input박스에 세팅하지 않은 값이 넘어오면 메모리에 복사해놓은 값을 가져다가 씀
 * (== insert의 input에 해당하는 tag에 값이 없어도 저장해놓은 값을 가져다 씀)
 * 
 * ######################################
 * Session == 상시 연결된 통로
 * 
 * SessionAttributes로 설정된 변수(객체)는 response를 하기 전에 서버의 기억장소 어딘가에 임시로 객체를 보관을 해두었다가
 * web browser와 server간에 한번이라도 연결이 이루어졌으면
 * Session..에 등록된 변수는 서버가 중단될 때까지 그 값이 그대로 유지된다.
 * web은 클라이언트의 request를 서버가 받아서 response를 수행하고 나면 모든 정보가 사라지는 특성을 가지고 있는데,
 * 이러한 특성과는 달리 spring 기반의 web은 일부 데이터들을 메모리에 보관했다가 재사용하는 기법 중 하나가 SessionAttributes 이다. 
 */
@SessionAttributes("memoDTO")
@RequestMapping(value="/memo")
@Controller
public class MemoController {
	
	/*
	 * SessionAttributes를 사용하기위해서는 
	 * 반드시 해당 변수를 생성하는 method가 Controller에 있어야 하고,
	 * 해당 method에는 @ModelAttribute("변수명") Annotation이 있어야 한다.
	 */
	@ModelAttribute("memoDTO") 
	public MemoDTO makeMemoDTO() {
		
		MemoDTO memoDTO = new MemoDTO();
		return memoDTO;
	}
	
	@Autowired
	MemoService mService;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(String search, Model model) {
		
		List<MemoDTO> memoList;
		if(search == null || search.isEmpty()) {
			memoList = mService.getAllList();
		}else {
			memoList = mService.getSearchList(search);
		}
		model.addAttribute("MEMO_LIST", memoList);
		
		return "home";
	}

	/*
	 * SessionAttributes에서 사용하는 변수(객체)에는
	 * @ModelAttribute를 설정해두어야 한다.
	 * 단, spring 5.x 이하에서는 필수, 5.x 이상에서는 선택사항.
	 */
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String insert(@ModelAttribute("memoDTO") MemoDTO memoDTO, Model model) {
		
		memoDTO = MemoDTO.builder().m_seq(99999).m_auth("hyerinyuu").m_date("2019-12-02").m_time("15:26:00").build();
		
		model.addAttribute("memoDTO", memoDTO);
		return "/insert";
		
	}
	
	/*
	 * insert POST가 memoDTO를 수신할 때
	 * 입력form에서 사용자가 입력한 값들이 있으면
	 * 그 값들을 memoDTO의 필드변수에 setting을 하고,
	 * 만약 없으면 메모리 어딘가에 보관중인 SessionAttributes로 설정된 memoDTO변수에서
	 * 값을 가져와서 비어있는 필드변수를 채워서 매개변수에 주입한다.
	 * 
	 * 따라서 form에서 보이지 않아도 되는 값들은
	 * 별도의 코딩을 하지 않아도
	 * 자동으로 insert POST로 전송되는 효과를 낸다.
	 * 단, 이 기능을 효율적으로 사용하려면 
	 * jsp code에 Spring form tag로 input을 코딩해야한다. 
	 */
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(@ModelAttribute("memoDTO") MemoDTO memoDTO, String search, Model model) {
		
		log.debug("시퀀스 : " + memoDTO.getM_seq());
		log.debug("날짜 : " + memoDTO.getM_date());
		log.debug("TEXT : " + memoDTO.getM_text());
		int ret = mService.insert(memoDTO);
		return "redirect:/memo/list";
	}
	
}
