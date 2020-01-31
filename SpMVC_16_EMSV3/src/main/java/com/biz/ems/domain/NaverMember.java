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
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NaverMember {
	
	private String id;
	private String email;
	private String name;
	private String nickname;
	private String gender;
	private String age;
	private String birthday;
	private String profile_image;

}
