package com.biz.memo.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biz.memo.domain.UserDTO;
import com.biz.memo.service.UserService;

@RequestMapping(value="/user")
@Controller
public class UserController {
	
	@Autowired
	UserService uService; // == UserService uService = new UserServiceImp();

	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join(Model model) {
		
		// insert.jsp에서 spring form tag를 사용하면서 modelAttribute를 userDTO로 설정해두었는데
		// 해당 attribute를 controller에서 전달해 주지 않으면 form을 열때 오류가 발생한다.
		// 때문에 userDTO를 생성해주고 model에 실어서 보내야 랜더링이 된다.
		UserDTO userDTO = new UserDTO();
		model.addAttribute("userDTO", userDTO);
		model.addAttribute("BODY", "JOIN");
		return "user/insert";
	}
	
	// BindingResult는 반드시 DTO다음에 와야한다.(전에오면 오류)
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(@ModelAttribute("userDTO") @Valid UserDTO userDTO, BindingResult bResult, Model model) {
		
		if(bResult.hasErrors()) {
			
			return "user/insert";
		}else {
			int ret = uService.userJoin(userDTO);
			return "redirect:/memo/list";
		}
	}
	
	@RequestMapping(value="/mypage", method=RequestMethod.GET)
	public String mypage(Model model, HttpSession httpSession) {
		
		UserDTO userDTO = (UserDTO)httpSession.getAttribute("userDTO");
		model.addAttribute("userDTO", userDTO);
		model.addAttribute("TITLE", "회원정보수정");
		
		return "user/insert";
	}
	
	@RequestMapping(value="/idcheck", method=RequestMethod.GET)
	public String userIdCheck(String u_id, Model model) {
		
		/*
		 * idYes : true: 등록된 회원 id가 있다
		 * 		 : false = 등록된 회원 id가 없다
		 */
		boolean idYes = uService.userIdCheck(u_id);
		model.addAttribute("ID_YES", idYes);
		model.addAttribute("U_ID", u_id);
		
		return "user/idcheck";
	}
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	/*
	 * @ResponseBody
	 * method가 return 하는 문자열을
	 * ResolverView에게 보내서 *.jsp 파일과 렌더링 하는 일을 하지 마라
	 * 문자열을 있는 그대로 브라우저로 보내라
	 * 
	 * @ResponseBody 가 없으면
	 * 	통상 /views/문자열.jsp 파일을 렌더링 하여 브라우저로 전송하라는 의미
	 */
	@ResponseBody
	
	@RequestMapping(value="/pass", method=RequestMethod.GET)
	
	/*
	 * 매개변수 이름과 상관없이 QUERY로 전달되는 strText변수를 사용하겠다.
	 * ss = strText
	 * @RequestParam(value="strText",
	 * 
	 * 사용자가 strText에 값을 전달하지 않아도 400오류를 내지 마라
	 * ※절대 DTO VO에는 설정 금지!!!!
	 *  	required = false,
	 *  
	 * 사용자가 strText값을 전달하지 않으면 
	 *  	defaultValue = "KOREA" 라는 기본 문자열을 변수에 받아라
	 *  	defaultValue = "KOREA") String ss
	 */
	public String passwordTest(@RequestParam(value="strText", required = false, defaultValue="KOREA") String strText) {
		
		
		String cryptTest = passwordEncoder.encode(strText);
		long textLeng = cryptTest.length();
		
		return cryptTest + " : " + textLeng;
	}
	
}
