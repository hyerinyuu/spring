package com.biz.gallery.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AjaxInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		HttpSession httpSession = request.getSession();
		Object memberVO = httpSession.getAttribute("MEMBER");
		
		if(memberVO == null) {  
			response.sendRedirect( request.getContextPath() + "/member/login");
			
			// 403 status는 권한없음
			response.setStatus(403);  // == response.setStatus(HttpServletResponse.SC_FORBIDDEN); 
			return false;
		}
		return super.preHandle(request, response, handler);
	}

	
	
	
}
