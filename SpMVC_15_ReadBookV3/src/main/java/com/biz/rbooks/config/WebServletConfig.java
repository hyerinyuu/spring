package com.biz.rbooks.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/*
 * xml이 없는 mvc project의 web.xml을 대신할 Class인데
 * 실제상황은 servlet-context.xml의 일부 기능을 추가하는 class
 * 
 * 1. WebMvcConfigurer를 implement하고 add implements method해서 addResourceHandler override해주기(resources경로 설정해주면 됨)
 * 
 * (@configuration Annotaion이 기본값)
 * [@EnableWebMvc] : Spring legacy project라는 것을 인식하는 annotaion
 * [@ComponentScan] : base package 경로 안내(배열로 선언)
 * 
 * 
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.biz.rbooks.controller", "com.biz.rbooks.service"})
public class WebServletConfig implements WebMvcConfigurer{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry.addResourceHandler("/css/**").addResourceLocations("/css/");
		
		WebMvcConfigurer.super.addResourceHandlers(registry);
	}
	
	@Bean
	public InternalResourceViewResolver resolver() {
		
		InternalResourceViewResolver resolver
		= new InternalResourceViewResolver();
		
		resolver.setViewClass(JstlView.class);
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		
		return resolver;
	}
	
	
}
