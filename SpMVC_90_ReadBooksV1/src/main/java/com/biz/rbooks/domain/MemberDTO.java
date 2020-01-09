package com.biz.rbooks.domain;

import com.biz.rbooks.domain.BookDTO.BookDTOBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDTO {
	
	private String m_id;	//varchar2(20 byte)
	private String m_password;	//nvarchar2(125 char)
	private String m_login_date;	//nvarchar2(10 char)
	private String m_rem;	//nvarchar2(125 char)
	
}
