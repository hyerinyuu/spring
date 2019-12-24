package com.biz.memo.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	
}
