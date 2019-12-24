package com.biz.memo.controller;

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

import com.biz.memo.domain.MemoDTO;
import com.biz.memo.domain.UserDTO;
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
 * 
 *  SessionAttributes()에 설정하는 문자열(이름)은
 *  클래스의 표준 객체생성 패턴에 의해서 만들어진 이름이다.
 *  예) MemoDTO memoDTO와 같이 클래스를 객체로 선언할때 소문자로 시작하는 것과 같음
 *  
 *   이름을 표준패턴이 아닌 임의로 변수명을 설정하고 싶을때는 괄호안에 객체명을 항상 적어줘야함
 *   (표준패턴이면 안적어도 됨~~~ 단 5.0 이상에서만)
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
	 * 
	 *  만약 Attributes의 이름을 표준 패턴이 아닌 임의의 이름으로 사용할 경우
	 *  @ModelAttribute("객체이름") 을 필수로 지정해주어야한다.
	 */
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String insert(@ModelAttribute("memoDTO") MemoDTO memoDTO, Model model, HttpSession httpSession) {
		
	//	memoDTO = MemoDTO.builder().m_seq(99999).m_auth("hyerinyu").m_date("2019-12-02").m_time("15:26:00").build();
		
		UserDTO userDTO = (UserDTO)httpSession.getAttribute("userDTO");
		if(userDTO == null) {
			model.addAttribute("LOGIN_MSG", "TRY");
			return "redirect:/member/login";
		}
		
		Date date = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat st = new SimpleDateFormat("hh:mm:ss");
		
		memoDTO.setM_date(sd.format(date));
		memoDTO.setM_time(st.format(date));
		
		memoDTO.setM_auth(userDTO.getU_id());

		model.addAttribute("CATS", mService.getCats());
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
	public String insert(@ModelAttribute("memoDTO") MemoDTO memoDTO, String search, Model model, SessionStatus sStatus) {
		
		log.debug("시퀀스 : " + memoDTO.getM_seq());
		log.debug("날짜 : " + memoDTO.getM_date());
		log.debug("TEXT : " + memoDTO.getM_text());
		int ret = mService.insert(memoDTO);
		
		// SessionAttributes를 사용할 때 insert, update가 완료되고
		// view로 보내기 전 반드시 setComplete();를 실행해서
		// Session에 담긴 값을 clear 해주어야함
		sStatus.setComplete();
		return "redirect:/memo/list";
	}
	
	// requestParam : 웹에서 사용하는 변수와 내가 사용하는 변수를 다르게 설정하고 싶을때
	// web에서 쓰는 변수를 괄호안에, 내가 사용하고 싶은 변수 명을 그 다음에 설정해주면 됨(String id)
	@RequestMapping(value="/view", method=RequestMethod.GET)
	public String view(@RequestParam("id") String str_seq, @ModelAttribute MemoDTO memoDTO, Model model, HttpSession hSession) {
		
		
		// 분리하는 이유 : try안에서 오류가 발생했는데 try안에변수를 선언하면
		// 오류를 잡아내기 힘드니까
		long m_seq = 0;
		try {
			m_seq = Long.valueOf(str_seq);
		} catch (Exception e) {
		}
		memoDTO = mService.getMemo(m_seq);
		
		UserDTO userDTO = (UserDTO)hSession.getAttribute("userDTO");
		
		// 로그인한 id와 메모의 m_auth 값을 비교하여
		// 작성자가 아니면 로그인 창으로 건너 뛰기
		if(userDTO != null && userDTO.getU_id().equals(memoDTO.getM_auth())) {
			model.addAttribute("memoDTO", memoDTO);
			return "view";
		}else {

			model.addAttribute("LOGIN_MSG", "NO_AUTH");
			return "redirect:/member/login";
		}
		
	}
	
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String update(String id, @ModelAttribute MemoDTO memoDTO, Model model) {
		
		long m_seq = 0;
		try {
			m_seq = Long.valueOf(id);
		} catch (Exception e) {
		}
		memoDTO = mService.getMemo(m_seq);
		
		model.addAttribute("CATS", mService.getCats());
		model.addAttribute("memoDTO", memoDTO);
		// insert.jsp
		return "insert";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(@ModelAttribute MemoDTO memoDTO, Model model, SessionStatus sStatus) {
		
		int ret = mService.update(memoDTO);
		sStatus.setComplete();
		return "redirect:/memo/list";
	}
	
	/*
	 * 브라우저에서 delete를 호출할 때
	 * m_seq 변수에 값을 포함하지 않고 request를 하면, 서버에서는 400오류가 발생함
	 * 그 이유는, spring은 web에서 넘겨져온 데이터를 
	 * long형으로 형변환을 수행하여 값을 받으려고 시도를 한다.
	 * 그 과정에서 값이 없으면(web은 값을 넘길때 항상 String으로 넘김) 
	 * null을 long형으로 변환하는 코드가 실행되어 서버 내부에서
	 * exception이 발생하고 400오류를 web에게 알려주게 된다.
	 * 
	 * 이것을 방지하기 위해 String으로 일단 받고
	 * 별도로 Long.valueof()를 실행하는 코드가 안전하다.
	 * 
	 * 하지만 delete를 호출할 때
	 * #####절대###### m_seq이 없이는 호출되지 않는다는 가정하에 바로 Long형의 변수로 수신할 수 있다.
	 * 
	 */
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String delete(@ModelAttribute MemoDTO memoDTO) {
		// == public String delete(long m_seq)
		
		// memoDTO의 m_seq만 setting이 되서 삭제 수행
		int ret = mService.delete(memoDTO.getM_seq());
		return "redirect:/memo/list"; 
	}
	
	
}
