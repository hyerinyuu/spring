package com.biz.rbooks.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

// abs 추상 클래스
/*
 * tomcat이 작동하면서 제일 먼저 호출할 클래스(dispatcher)
 * 3개의 method를 순서대로 읽음
 * 
 * [getRootconfigClasses]
 * getRootConfig method는 실제로는 하는 일이 없으나 없으면 오류가 발생하므로 만들기
 * 
 * [getServletConfigClasses]
 * 
 * 
 */
public class ProjectInit extends AbstractAnnotationConfigDispatcherServletInitializer{

	// rootContext를 읽어들이는 부분
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {RootConfig.class};
	}

	// web.xml의 servlet을 대신하는 부분
	@Override
	protected Class<?>[] getServletConfigClasses() {

		// == Class[] servlet = new Class[]{WebConfig.class};
		// myBatisConfig 클래스를 읽어들일 수 있도록 배열에 추가
		return new Class[]{WebServletConfig.class, MybatisConfig.class};
	}

	// web.xml의 
	@Override
	protected String[] getServletMappings() {
		
		// == String[] mapping = new String[]{"/", "*.do"};
		return new String[]{"/", "*.do"};
	}

}
