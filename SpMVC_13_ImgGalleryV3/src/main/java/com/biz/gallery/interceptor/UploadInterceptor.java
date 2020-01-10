package com.biz.gallery.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UploadInterceptor extends HandlerInterceptorAdapter {@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		// 로그인이 됐는지 확인하는 절차
		HttpSession httpSession = request.getSession();
		Object memberVO = httpSession.getAttribute("MEMBER");
		// 로그인이 안됐으면
		if(memberVO == null) {
			request.setAttribute("MODAL", "LOGIN");
		}
	
		super.postHandle(request, response, handler, modelAndView);
		
		
	}

	
	
	
}
