package com.biz.ems.service;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.biz.ems.config.NAVER;
import com.biz.ems.domain.NaverMember;
import com.biz.ems.domain.NaverMemberResponse;
import com.biz.ems.domain.NaverReturnAuth;
import com.biz.ems.domain.NaverTokenVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NaverLoginService {

	@Value("${naver.client.id}")
	private String clientId;
	
	@Value("${naver.client.secret}")
	private String clientSec;
	
	private final String loginAPI_URL = "https://nid.naver.com/oauth2.0/authorize";
	private final String tokenAPI_URL = "https://nid.naver.com/oauth2.0/token";
	private final String naverMemberAPI_URL = "https://openapi.naver.com/v1/nid/me";
	
	
	private final String callbacklocalURL = "http://localhost:8080/ems_hyerinyu/member/naver/ok";
	private final String callbackURL = "https://callor.com:12600/ems_hyerinyu/member/naver/ok";

	
	
	public String oAuthLoginGet() {
		
		/*
		String redirectURI = null;
		
		try {
			redirectURI = URLEncoder.encode(callbackURL,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		*/		
		
		SecureRandom random = new SecureRandom();
		
		// 최대값 130bit 크기의 임의의 정수를 생성하여 문자열로 만들어라.
		String stateKey = new BigInteger(130, random).toString();
		
		log.debug("###STATE KEY : " + stateKey);
		
		/*
		 * [UriComponentsBuilder]
		 * spring 4.1에서 사용하는 uri query문자열을 생성하는 Class
		 * fromHttpUrl() : 요청을 수행할 server주소
		 * queryParam() : 변수=값 형태의 query문자열 생성
		 * build(true) : 생성하는 문자열 중에 encoding이 필요한 부분이 있으면 encoding을 수행하라
		 * 
		 * 5.1 이상에서는 별도로 encoding() method가 있다.
		 * 
		 * queryParam("name", "korea") ==> &name=korea
		 * qureyParam("name", "korea", "usa") ==> &name=korea&name=usa
		 */
		String apiURL = UriComponentsBuilder.fromHttpUrl(loginAPI_URL)
							.queryParam("client_id", this.clientId)
							.queryParam("response_type", "code")
							.queryParam("redirect_uri", callbackURL)
							.queryParam("state", stateKey)
							.build(true).toUriString();
		
		log.debug("###URL : " + apiURL);
		
		return apiURL;
		
	}
	
	
	/*
	 * 네이버에 정보 요구를 할 때 사용할 token을 요청
	 * token을 요청할 때 GET/POST method를 사용할 수 있는데
	 * 여기서는 POST를 사용해 요청을 할 것임(보안면에서 유리)
	 */
	public NaverTokenVO oAuthAccessGetToken(NaverReturnAuth naverOK) {
		
		HttpHeaders headers = new HttpHeaders();
		
		/* 이둘은 선택사항이지만 가끔 clientID와 Security가 없으면 네이버가 오류를 발생시키는 경우가 있으므로 살려둠 */
		headers.set("X-Naver-Client_Id", this.clientId);
		headers.set("X-Naver-Client_secret", this.clientSec);
		
		// 맵과 같으나 http protocal에서 사용가능하도록 내부로직이 변한 것.
		// Map Interface를 상속받아 작성된 spring framework 전용 Map Interface.
		// Http protocal과 관련된 곳에서 기본 Map 대신 사용하는 Interface
		// htp protocol과 관련된 내장 method가 포함되어있다.
		MultiValueMap<String, String> bodies = new LinkedMultiValueMap<String, String>();
		
		bodies.add(NAVER.TOKEN.grant_type, NAVER.GRANT_TYPE.authorization_code);
		bodies.add(NAVER.TOKEN.client_id, this.clientId);
		bodies.add(NAVER.TOKEN.client_secret, this.clientSec);
		bodies.add(NAVER.TOKEN.code, naverOK.getCode());
		bodies.add(NAVER.TOKEN.state, naverOK.getState());
		
		// 위에서 만든 params과 headers를 entity 객체로 변환
		// ### headers에 담긴 정보와 params에 담긴 정보를 HttpEntity 데이터로 변환
		HttpEntity<MultiValueMap<String , String>> request
		 = new HttpEntity<MultiValueMap<String,String>>(bodies, headers);
		
		
		URI restURI = null;
		
		try {
			restURI = new URI(tokenAPI_URL);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		// RestTemplate를 사용하여 Naver에 Token을 요청
		RestTemplate restTemp = new RestTemplate();
		ResponseEntity<NaverTokenVO> result = null;
		
		result = restTemp.exchange(restURI, HttpMethod.POST, request, NaverTokenVO.class);
		
		log.debug("###NAVER TOKEN : " + result.getBody().toString());

		return result.getBody();
		
	}
	
	
	public NaverMember getNaverMemberInfo(NaverTokenVO tokenVO) {
		
		String token = tokenVO.getAccess_token();
		String header = "bearer " + token;
		
		// header 문자열을 GET의 http header에 실어서 GET방식으로 요청
		HttpHeaders headers = new HttpHeaders();
		
		headers.add("Authorization", header);
		
		/*
		 * body부분에 parameter라는 문자열을 추가하고
		 * header부분에 위의 headers에서 설정한 형식으로
		 * Authoriztion="bearer..." 모두 문자열로 변환하여 httpProtocol 데이터 구조로 변경한다.
		 * 그리고 HttpProtocol을 사용하여 데이터를 전송할 수 있도록 준비를 한다.
		 * HttpEntity<String> 형식으로 선언하면 : 단일 생성 방식
		 * 
		 * 
		 * HttpEntity<Multivalue<String,Object>> 형식으로 선언하면
		 * body부분에 데이터를 다음과 같이 생성
		 * 		변수=[값],변수=[값]
		 * header부분의 데이터는 [변수:값, 변수:값]
		 */
		HttpEntity<String> request = new HttpEntity<String>("parameter", headers);
		
		log.debug("ENTITY : " + request.toString());
		
		
		
		ResponseEntity<NaverMemberResponse> result;
		RestTemplate restTemp = new RestTemplate();
		
		result = restTemp.exchange(naverMemberAPI_URL, HttpMethod.GET, request, NaverMemberResponse.class);
		
		NaverMember member = result.getBody().response;
		
		return member;
		
		
	}
	
	
	
}
