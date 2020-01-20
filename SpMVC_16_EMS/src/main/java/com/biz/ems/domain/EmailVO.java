package com.biz.ems.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailVO {

	private String from_email;  // 보내는 email
	private String to_email;   	// 받는 email
	private String send_date;	// 보낸 날짜
	private String send_time;	// 보낸 시간 
	private String from_name;	// 보낸사람 이름
	private String to_name;		// 받는사람 이름
	private String subject;		// 제목
	private String content;		// 내용
	
	
}
