package com.biz.iolist.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.iolist.domain.IolistDTO;
import com.biz.iolist.domain.IolistVO;
import com.biz.iolist.persistence.IolistDao;
import com.biz.iolist.service.IolistService;

@RequestMapping(value="/iolist")
@Controller
public class IolistController {
	
	@Autowired
	IolistService ioService;

	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String ioHome(Model model) {
		
		/*
		 * 아직 viewAllList() method를 생성하지 않은 상태에서
		 * viewAll List() 호출하여 할일을 먼저 정의한 것 
		 * TDD(Test Driven Developer)
		 * 할일을 먼저 정하고 그것을 공급해줄 것을 나중에 만드는 것
		 */
		List<IolistVO> iolist = ioService.viewAllList();
		
		// [attributute를 대문자로 묶는 이유]
		// : jsp view에서 사용할 변수 역할을 수행할 Attribute를 등록하는 절차
		// 이때 "IOLIST"를 대문자로 사용하는 이유는 대소문자가 섞이면 
		// 문자차이로 인한 오류가 발생하거나 값을 표시하지 못하는 일이 있어 이를 방지하기 위함
		// attribute임을 명시하는 패턴으로도 사용
		model.addAttribute("IOLIST", iolist);
		
		// value에 list가 있으므로 그냥 null로 해도 상관 없지만 명시적으로 그냥 적음
		return "/iolist/list";
		
	}
	
	// 새로등록 버튼을 클릭했을때 입력form을 보여주는 method
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String list(Model model) {
		
		/*
		 * 새로 입력을 수행할 떄 Default값을 만들어서 form에 세팅하기
		 */
		// 서버의 현재날짜를 문자열로 생성
		Date date = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		String curDate = sd.format(date);
		
		// ioDTO를 선언하여 IO_SEQ갑과 IO_DATE를 세팅하고
		IolistDTO ioDTO = IolistDTO.builder().io_seq(0).io_date(curDate).io_price(1000).build();
		
		// model에 담아서 input.jsp폼에 전송
		model.addAttribute("IO_DTO", ioDTO);
		return "iolist/input";
	}
	
	/*
	 * POST에서 데이터를 수신할 때
	 * DTO에 데이터를 받을 경우, DTO의 필드변수 중에 String이 아닌 데이터가 있을 경우,
	 * 입력form에서 해당 값을 입력하지 않고 넘기면(전송하면) 400오류가 발생함
	 * 왜냐하면 Type.valueof() method에서 exception이 발생하기 때문이다.
	 * 즉 입력 form에서 String이 아닌 항목을 비워놓고 전송하면 발생하는 오류임
	 * (지금 단가, 수량 등등이 int형인데 입력 안하고 넘어가서 400오류)
	 */
	// 입력form에서 데이터를 입력하고 전송 또는 저장 버튼을 클릭했을때 데이터를 수신할 method 
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String list(IolistDTO ioDTO, Model model) {
			
		int ret = ioService.insert(ioDTO);
			
			
		// insert(input), update, delete POST를 수행한후, list를 보여서 변경된 사항을 보여줘라
		return "redirect:/iolist/list";
	}
	
	@RequestMapping(value="/view", method=RequestMethod.GET)
	public String view(String id, Model model) {
	
		long io_seq = 0;
		try {
			io_seq = Long.valueOf(id);
		} catch (Exception e) {

		}
		IolistVO io_vo = ioService.findBySEQ(io_seq);
		model.addAttribute("IO_VO", io_vo);
		return "iolist/view";
	}
	
}
