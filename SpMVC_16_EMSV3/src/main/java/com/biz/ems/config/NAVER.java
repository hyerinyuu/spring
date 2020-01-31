package com.biz.ems.config;

public class NAVER {
	
	public static class TOKEN {
		
		public final static String grant_type = "grant_type";		//  string
		public final static String client_id = "client_id";		//	string
		public final static String client_secret = "client_secret";	//	string
		public final static String code = "code";			//	string
		public final static String state = "state";			//	string
		
		// 갱신/삭제시에만 필요한 변수들이라 사용하지 않을 예정
		// (네이버 개발자센터 네이버아이디로 로그인 API명세 참조)
		public final static String refresh_token = "refresh_token";	//	string
		public final static String access_token = "access_token";	//	string
		public final static String service_provider = "service_provider";//	string
		
		
	}	
	
	public static class GRANT_TYPE {
		
		public final static String authorization_code = "authorization_code";  // 1. 발급
		public final static String refresh_token = "refresh_token";       	   // 2. 갱신
		public final static String delete = "delete";						   // 3. 삭제
		
		
	}
	
	
}
