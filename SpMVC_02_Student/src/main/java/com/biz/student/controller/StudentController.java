package com.biz.student.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.student.domain.StudentDTO;
import com.biz.student.service.AnnService;
import com.biz.student.service.HomeService;
import com.biz.student.service.StudentService;

/*
 * @Controller
 * Tomcat, Spring container에 이 클래스의 List를 추가하고
 * request 요청에 대비하라
 */
@Controller
public class StudentController {
	
	// @Autowired
	@Inject
	StudentService sService;
	
	@Autowired
	HomeService hService;
	
	@Autowired
	AnnService aService;
	
	public StudentController() {
		
		sService = new StudentService();
	
	}
	
	@RequestMapping(value="input", method=RequestMethod.GET)
	public String input() {
		
		// WEB-INF/view/student/input.jsp파일을 열어서
		// browser로 전송하라는 의미
		return "student/input";
	}
	
	// input form에 데이터를 입력한 후 전송을 하면 데이터를 수신할 method
	
	// 매개변수로 설정된 StudentDTO stDTO는 form의 input box에 설정된 name과 같은 변수명을 필드로 갖는 DTO
	
	// 별다른 setter조치 없이 Spring은 자동으로 form의 input box에 설정된 변수명(name)값과 일치하는 정보를
	// 검사하여 자동으로 DTO에 전달받은 값을 setting하여 stDTO에 담아준다.
	
	// from에서 전달받은 모든 데이터의 기본 Type은 String임.
	// 이때 st_grade는 int형으로 필드변수에 선언이 되어있는데,
	// 문자열로 전달받은 데이터를 int형으로 Integer.valueof() method를 이용해서 내부적으로 숫자Type으로 변환을 시도함(Spring framework에서)
	// 만약 st_grade input box에 값을 입력하지 않고 전송을 누르면 
	// 해당 변수에 담긴 값은 ""이거나 null일것이다.
	// == Integer.valueof("") or Integer.valueof(null) ==> NumberformatException 발생
	// 이런 상황이 발생하면 톰캣은 400오류창을 띄움(ex 학생정보입력창에서 아무것도 입력하지 않고 저장을 누르면 400발생)
	@RequestMapping(value="input", method=RequestMethod.POST)
	public String input(StudentDTO stDTO, Model model) {
		
		System.out.println(stDTO.toString());
		model.addAttribute("stDTO", stDTO);
		return "student/view";
	}
	
	@RequestMapping(value="search", method=RequestMethod.GET)
	public String search() {
		
		sService.viewStudent();
		hService.viewHome();
		aService.viewAnn();
		return "student/search";
	}
	
	@RequestMapping(value="view", method=RequestMethod.GET)
	public String view(){
		
		
		return "student/view";
	}
	
	@RequestMapping(value="list", method=RequestMethod.GET)
	public String view(Model model){
		
		List<StudentDTO> stdList = sService.stdList();
		model.addAttribute("stdList", stdList);
		
		return "student/viewList";
	}
	
}
