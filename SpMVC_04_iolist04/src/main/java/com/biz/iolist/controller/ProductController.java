package com.biz.iolist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.biz.iolist.domain.ProductDTO;
import com.biz.iolist.service.ProductService;

@Controller
@RequestMapping(value="/pro")
public class ProductController {

	// 상품리스트 보여주기
	// insert
	// delete
	// update 
	@Autowired
	ProductService pService;
	
	/*
	 * 상품이름을 전달받아서 해당상품을 검색하여 보여주기
	 */
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public String search(String strText, Model model) {
		
		/*
		 * 입력박스에 상품코드가 입력된 상태에서 Enter를 누르면
		 * 상품코드로 상품을 조회하고
		 * 그렇지 않으면 이름을 조회하라
		 */
		List<ProductDTO> proList = pService.selectNameSearch(strText);
		model.addAttribute("PROLIST", proList);
		return "pro/list-body";
	}
	
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView mView = new ModelAndView();
		List<ProductDTO> proList = pService.getAllList();
		mView.setViewName("/pro/list");
		
		mView.addObject("PROLIST", proList);
		
		return mView;
	}
	
	// 데이터를 입력할 입력 폼을 보여주는 method
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String input(Model model) {
	
		return "pro/input";
		
	}
	
	// 입력폼에서 데이터를 입력해 전송하면 데이터를 가지고 호출될 method
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String input(ProductDTO proDTO, Model model) {
	
		int ret = pService.insert(proDTO);
		return "redirect/pro/list";
		
	}
	
}
