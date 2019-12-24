package com.biz.memo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.biz.memo.domain.UserDTO;

/*
 * Controller에서 객체(리스트)를 view로 보내는 방법
 * model(ModelAndView).addAttribute()
 * SessionAttributes() ModelAndAttribbute() 설정 후 Model에 담기
 * HttpSession.setAttribute()에 담기
 * 
 * Model : 일회용데이터, Controller >> view 보내기만을 위한 데이터(일방통행)
 * 		  Model에 담긴 데이터를 다시 서버로 보내려면
 * 		  input tag에 값을 담아서 다시 POST로 전송해야함
 * 
 * SessionAttributes + Model : 일회용데이터이면서, Session에 유지되는 데이터
 * 		  input view에서 Spring form tag에 값을 담으면
 * 		  이후에 자유롭게 서버로 전송을 할 수 있다.
 * 		  input, update 코딩을 간편하게 사용하는 용도.
 * 		  Spring에서는 SessionAttribute일 경우, Garbage Collection이 적용됨.(사용안하면 삭제)	
 * 
 * HttpSession : 주로 login과 관련된 데이터를 유지하기위한 용도
 * 		  특별히 시간설정을 하거나, 값을 remove하거나 server가 멈출때까지 그 값을 유지하고
 * 		  모든 controller, 모든 view에서 값을 참조할 수 있다.
 * 		  그러나, httpSession은 GarbageCollection을 적용할 수 없다.
 * 		  (표준 HTTP 프로토콜에 정의된 규격을 사용) 
 * 		  httpSession은 server의 메모리 공간을 많이 차지해 server 성능에 문제를 일으키기도 하므로
 * 		  login을 할때만 사용해야하기. 
 * 
 * 
 */
@RequestMapping(value="/member")
@Controller
public class MemberController {
	
	/*
	 * 19-12-04 할일
	 * 1. DB에 사용자 저장하기
	 * 2. password 부분을 암호화하기
	 * 3. spring form tag를 활용한 유효성 검사
	 */
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join() {
		
		return null;
	}

	// required=false 값을 주면 받고 안주면 말아라
	@RequestMapping(value="login", method=RequestMethod.GET )
	public String login(@RequestParam(value="LOGIN_MSG", required=false, defaultValue = "0") String msg, Model model) {
		
		model.addAttribute("LOGIN_MSG", msg);
		return null;
	}

	@RequestMapping(value="login", method=RequestMethod.POST )
	public String login(UserDTO userDTO, Model model, HttpSession httpSession) {
		
		UserDTO getUserDTO = UserDTO.builder().u_id("hyerinyu").u_password("12345").u_name("홍길동").u_tel("010-1111-2222").build();
		
		// 로그인한 사용자 ID와 Password를 DB정보와 일치하는지 검사
		if(userDTO.getU_id().equalsIgnoreCase(getUserDTO.getU_id()) 
				&& userDTO.getU_password().equals(getUserDTO.getU_password())) {
			
			// 이 세션은 앞으로 10분 동안만 유지하라 60초 *10
			httpSession.setMaxInactiveInterval(10*60);
			// 일치하면 사용자 정보 저장(모든 jsp에서 참조 가능)
			httpSession.setAttribute("userDTO", getUserDTO);
		}else {
			
			// 로그인에 실패하면 remove
			httpSession.removeAttribute("userDTO");
		}
		
		return "redirect:/memo/list";
	}

	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession httpSession) {
		
		// session은 유지하되 내용만 null로함
		httpSession.setAttribute("userDTO", null);
		
		// session 자체를 지워버림
		httpSession.removeAttribute("userDTO");
		
		return "redirect:/memo/list";
	}
	
	
}
