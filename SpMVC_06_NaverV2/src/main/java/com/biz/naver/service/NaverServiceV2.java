package com.biz.naver.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.naver.config.NaverConfig;
import com.biz.naver.domain.PageDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NaverServiceV2 {

	private final String naver_news_url = "https://openapi.naver.com/v1/search/news.json";
	private final String naver_books_url = "https://openapi.naver.com/v1/search/book.json";
	private final String naver_movies_url = "https://openapi.naver.com/v1/search/movie.json";
	
	@Autowired
	PageService pService;
	
	public PageDTO getPage(String cat, String search, long currentPageNo) throws IOException, ParseException {
		// 전체 데이터개수 계산
		String totalQuery = this.queryNaver(cat, search); // 1~10페이지까지만 query하는 부분
				
		log.debug("쿼리" + totalQuery);
				
		String totalString = this.getNaverString(totalQuery);
		JSONObject totalJObject= this.strToJson(totalString);
				
		// jSONObject에서 key가 total인 항목을 찾아서 값을 문자열ㄹ ㅗ추출
		String strTotal = totalJObject.get("total").toString();
		long totalCount = Long.valueOf(strTotal);
		
		if(totalCount > 1000) totalCount = 1000;
		
		PageDTO pageDTO = pService.makePagiNation(totalCount, currentPageNo);
				
		log.debug("************ 전체개수" + totalCount);
		
		return pageDTO;
		
	}
	
	
	// [실행순서]
	// 0
	public JSONArray getNaver(String cat, String search, long currentPageNo) throws IOException, ParseException {
		
		PageDTO pageDTO = this.getPage(cat, search, currentPageNo);

		
		// page가 1이면 : 1 ~ 10
		// 2 : 11 ~ 20
		if(currentPageNo == 1) currentPageNo = 1;
		else currentPageNo = (currentPageNo -1) * pageDTO.getListPerPage() +1;
		// 1. queryString으로부터 문자열을 받고
		String queryString = this.queryNaver(cat, search, currentPageNo, 
				pageDTO.getListPerPage());
		
		// 2.
		String resString = this.getNaverString(queryString);
		//3.
		JSONObject resjObject = this.strToJson(resString);
		//4.
		JSONArray resArray = this.getArray(resjObject, "items");
		
		return resArray;
	}
	
	
	
	// [실행순서]
	// 1. cat, search, start, disp값을 매개변수로 받아서
	// start부터 display까지의 개수만큼 데이터를 가져오기 위한 queryString을 생성함
	public String queryNaver(String cat, String search, long start, long display) throws UnsupportedEncodingException {
		
		// https:......?query=aaa
		String queryString = URLEncoder.encode(search, "UTF-8");
		queryString = this.queryURL(cat) + "?query=" + queryString;
		
		
		// start와 display값을 query,에 포함하면
		// start부터 display 갯수 만큼 데이터를 보내라 라는 의미
		queryString += "&start=" + start;
		queryString += "&display=" + display;
		
		return queryString;
	}
	
	// [실행순서]
	// 2. queryNaver에서 생성한 queryString 문자열을 매개변수로 받아서
	// 네이버에게 전송하고 response된 문자열을 return함
	public String getNaverString(String queryString) throws IOException {
		
		URL url = new URL(queryString);
		HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();
		
		httpConn.setRequestMethod("GET");
		httpConn.setRequestProperty("X-Naver-Client-Id", NaverConfig.NaverClientId);
		httpConn.setRequestProperty("X-Naver-Client-Secret", NaverConfig.NaverClientSecret);
		
		int resCode = httpConn.getResponseCode();
		
		BufferedReader buffer = null;
		
		//  naver가 정상요청을 받아서 기다리라고 요청을 보낸것
		if(resCode == 200) {
			
			InputStreamReader is = new InputStreamReader(httpConn.getInputStream());
			buffer = new BufferedReader(is);
			
		}else {
			
			// 오류가 발생하면 inputStream이 아닌 ErrorStream을 통해 받기
			InputStreamReader is = new InputStreamReader(httpConn.getErrorStream());
			buffer = new BufferedReader(is);
		}
		// String resString = "";
		StringBuffer resString = new StringBuffer();
		String reader = "";
		while(true) {
			reader = buffer.readLine();
			if(reader == null) break;
			resString.append(reader);
		}	
		// 오류가 발생했다는 소리, 디버깅을 위한 코드
		if(resCode == 200 ) {
			return resString.toString();

		}else {
			log.debug("**************** 네이버 오류", resString.toString());
		}
		return null;
	}

	// [실행순서]
	// 3. 네이버에서 response(수신)한 문자열을 통째로 JSON Object로 변환
	
	// 문자열을 JSONObject로 변환함
	public JSONObject strToJson(String jsonString) throws ParseException {
		
		JSONParser jParser = new JSONParser();
		JSONObject jObject = (JSONObject) jParser.parse(jsonString);
		
		return jObject;
	}
	
	// [실행순서]
	// 4. JSONObject로부터 Naver의 items만 추출하여 JSONArray로 변환
	// naver로부터 받은 데이터에서 items항목을 추출하여
	// 실제 데이터들을 Array로 만들어주는 method(데이터 추출용으로 사용)
	public JSONArray getArray(JSONObject jObject, String keyString) {
		
		return (JSONArray) jObject.get(keyString);
	}
	
	public String queryURL(String cat) {
		
		String queryURL = naver_news_url;
		if(cat.equalsIgnoreCase("NEWS")) {
			queryURL = naver_news_url;
		}else if(cat.equalsIgnoreCase("BOOKS")) {
			queryURL = naver_books_url;
		}else if(cat.equalsIgnoreCase("MOVIES")) {
			queryURL = naver_movies_url;
		}
		
		return queryURL;
		
	}
	
	public String queryNaver(String cat, String search) throws UnsupportedEncodingException {
		
		// https:......?query=aaa
		String queryString = URLEncoder.encode(search, "UTF-8");
		queryString = this.queryURL(cat) + "?query=" + queryString;
		
		return queryString;
	}
	
	
	
	
	
}
