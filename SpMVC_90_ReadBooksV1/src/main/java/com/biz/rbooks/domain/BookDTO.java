package com.biz.rbooks.domain;

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
	private String b_iprice;	//	number
	
}
