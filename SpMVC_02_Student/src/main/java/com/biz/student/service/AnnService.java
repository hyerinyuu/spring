package com.biz.student.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

// @Service // MVC패턴에서 Service클래스에 사용
// @bean ==> 종류에 관계없이 사용, JDK에서 제공하는 클래스
// 			xml에 <bean> 
// @Component  // ==> 종류에 관계 없이 사용, 사용자가 작성한 클래스
@Service
public class AnnService {

	public void viewAnn() {
		
		System.out.println("대한민국만세");
		System.out.println("Republic of Korea");
	}
	
	
}
