package com.biz.gdata.controller;

import java.io.IOException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biz.gdata.domain.AreaBaseDTO;
import com.biz.gdata.domain.TourDTO;
import com.biz.gdata.service.TourAppService;
import com.biz.gdata.service.TourService;

@Controller
public class TourController {

	@ModelAttribute
	public TourDTO newTour() {
		return new TourDTO();
	}
	
	@Autowired
	TourAppService tService;
	
	@Autowired
	TourService tourService;
	
	@ResponseBody
	@RequestMapping(value="total", method=RequestMethod.GET, produces = "text/json;charset=UTF-8")
	public String total(@RequestParam(value="t_city", required = false, defaultValue = "1") String t_ccity, Model model) {
		
		List<AreaBaseDTO> areaList;
		
		JSONArray jArray = null;
		try {
			jArray = tourService.getData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("CITY", jArray);
		return "home";
	}
	
}
