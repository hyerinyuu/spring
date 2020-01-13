package com.biz.rbooks.domain;

import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Alias("bookDTO")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDTO {

	private String b_code;	//varchar2(20 byte)
	private String b_name;	//	nvarchar2(125 char)
	private String b_auther;	//	nvarchar2(125 char)
	private String b_comp;	//	nvarchar2(125 char)
	private String b_year;	//	varchar2(10 byte)
	private long b_iprice;	//	number
	
	// b_code값을 참조해서 독서록리스트를 가져올 변수
	private List<ReadBookDTO> rbList;
	
	// b_name값을 참조해서 독서록리스트에 도서명을 가져올 변수
	private List<ReadBookDTO> rbNameList;
	
}
