package com.biz.rbooks.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
// HandlerInterceptorAdapter를 extends
public class AuthInterceptor extends HandlerInterceptorAdapter{

	// preHandler override
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String urlPath = request.getRequestURL().toString();
		String uriPath = request.getRequestURI().toString();
		
		String msg = String.format("URL : %s, URI : $s", urlPath, uriPath);

		// login 정보 확인하기
		// 1. Session정보 뽑기 
		HttpSession httpSession = request.getSession();
		
		// 2. member session 을 추출해서 sessionObj객체에 담아줌
		Object sessionObj = httpSession.getAttribute("MEMBER");
		
		// 3. login이 안되어있으면
		if(sessionObj == null) {
			
			// 4. 로그인화면으로 redirect시켜줌 
			response.sendRedirect(request.getContextPath() + "/member/login");
			
			// 5. 로그인이 안되어있는 상태이므로 controller로 전송하는걸 막아주기
			return false;
		}

		log.debug("###interceptor");
		log.debug(msg);
		
		// true return
		return super.preHandle(request, response, handler);
	}
	
	
}
