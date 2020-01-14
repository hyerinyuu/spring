package com.biz.gallery.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;

/*
 * [Interceptor를 활용한 가장 간단한 login]
 * 
 * [Request를 가로채기를 실시하도록 만든 class]
 * 	HandlerInterceptorAdapter를 extends하고 preHandle method override
 * 	appServlet아래에 login-context.xml 생성(bean, context,  mvc 체크 )
 * 	
 * 	1. Request로부터 Session ID 추출
 *  2. member Session을 확인하기 위해서 attribute를 추출해서 object 객체에 담기
 *  4. null 검사(=회원 정보 없어서 로그인 실패)
 *  login-context.xml에 path와 exclude path 지정해주기
 */
@Slf4j
public class AuthInterceptor extends HandlerInterceptorAdapter{

	/*
	 * HandlerInterceptorAdapter에 들어있는 preHandle 사용
	 * : Dispatcher에서 Controller로 가는 데이터를 중간에 가로채서 로그인 유무를 체크할 method
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		String urlPath = request.getRequestURL().toString();
		String uriPath = request.getRequestURI().toString();
		
		String msg = String.format("URL : %s , URI : %s", urlPath, uriPath);

		// login 정보를 확인하기
		// 1. Session정보(SessionID)를 request로부터 뽑음(Dispatcher에 없어서 뽑아줘야함)
		HttpSession httpSession = request.getSession();
		
		// 2. Member Session을 확인하기 위해서 Attribute를 추출해서 Object객체 (sessionObj)에 담기
		Object sessionObj = httpSession.getAttribute("MEMBER");
		
		
		// 3. Object 객체가 null인지 확인
		// null == Member Session 객체가 없다 == login 안되어 있음
		if(sessionObj == null) {  // 로그인이 안되어있으면 login 창으로 redirect
			
			
			// 4. 로그인 화면으로 redicrect를 수행하여 login을 하도록 유도
			response.sendRedirect( request.getContextPath() + "/member/login");
				//	 		[..] 
				// ==> context/image/update에서 로그인 path로 redirect를 하는데
				// 경로 지정이 애매하게 작동해서(interceptor이 spring과 matching이 잘 안됨)
				// 현재 경로가 /image/upload 이기 때문에
				// ..을 수행하면 바로 root Context부터 바로 경로를 시작함
				// == Gallery/image/../member/login
			
			
			// 5. 현재 로그인이 안되어 있는 상태이므로 
			// Dispatcher에게 Controller로 전송하지 말라고 알려주기 
			return false;
		}
		log.debug("나는 interceptor");
		log.debug(msg);

		// 이 값은 default가 true(Controller에게 전달)
		return super.preHandle(request, response, handler);
	}

	
	
	
}
