package com.biz.ems.service;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.SecureRandom;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NaverLoginService {

	private final String clientId = "gfAl05jY6CFkmHuTd7d6";
	private final String loginAPI_URL = "https://nid.naver.com/oauth2.0/authorize";
	private final String callbackLocalURL = "https://callor.com:12600/ems/naver/ok";
	private final String callbackURL = "http://localhost:8080/ems/naver/ok";
	
	
	public String oAuthLoginGet() {
		
		String redirectURI = null;
		
		try {
			redirectURI = URLEncoder.encode(callbackURL,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
				
		
		SecureRandom random = new SecureRandom();
		
		// 최대값 130bit 크기의 임의의 정수를 생성하여 문자열로 만들어라.
		String stateKey = new BigInteger(130, random).toString();
		
		log.debug("###STATE KEY : " + stateKey);
		
		String apiURL = loginAPI_URL;
		apiURL += "?client_id=" + clientId;
		apiURL += "&redirect_uri=" + redirectURI;
		apiURL += "&state=" + stateKey;
		
		log.debug("###URL : " + apiURL);
		
		return apiURL;
		
	}
	
}
