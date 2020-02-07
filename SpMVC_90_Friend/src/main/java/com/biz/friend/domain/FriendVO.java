package com.biz.friend.domain;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Alias("fVO")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FriendVO {
	
	private long f_id;			// bigint
	private String f_name;		//	varchar(10)
	private String f_tel;		//	varchar(20)
	private String f_addr;		//	varchar(255)
	private String f_hobby;		//	varchar(255)
	private String f_rel;		//	varchar(20)


}
