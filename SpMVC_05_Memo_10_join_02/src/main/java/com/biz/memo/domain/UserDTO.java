package com.biz.memo.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.ScriptAssert;

import com.sun.istack.internal.NotNull;

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
@ScriptAssert(lang="javascript",
				script="(_this.u_password == _this.re_password)", 
				reportOn="re_password", message="비밀번호와 확인비밀번호가 일치하지 않습니다.")
public class UserDTO {

	/*
	 *  @Email : email형식 검사
	 *  @NotBlank : 공백검사
	 *  @NotNull : null 검사, null이 아닐 경우만 통과
	 *  @Null: null일 경우만 통과
	 *  @Max(x), @Min(x) : 숫자의 최대값 최소값 제한
	 *  @Size(min=x, max=x) : 문자열일 경우 
	 *  @DecimalMax(x) : x값 이하의 실수
	 *  @DecialMin(x) : x값 이상의 실수
	 *  @Digits(integer=x) : x자릿수 이하의 정수
	 *  @Digits(integer=x, fraction=y) : x자릿수 이하의 정수이면서, y자릿수 이하 소수점 자릿수
	 */
	@NotNull
	@NotBlank(message = "*id는 공백일 수 없습니다.")
	@Email(message = "*이메일형식으로만 써주세요")
	@Size(min = 5, max=100)
	private String u_id;
	
	private String u_password;
	private String re_password;
	
	private String u_name;
	
	@NotBlank(message = "*닉네임은 반드시 입력하셔야합니다")
	private String u_nick;
	
	
	private String u_grade;
	
	// 정규형 표현식
	@Pattern(regexp = "\\d{1,15}", message="1~15자리 까지의 숫자만 입력가능합니다.")
	private String u_tel;
	
	
	
}
