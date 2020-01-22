package com.biz.ems.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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

/*
 *  [@Entity] : jpa 속성(**import javax.persistence.Entity)
 *  - JPA interface에 정의된 속성 지정
 *  - 지금부터 이 클래스는 Entity이니 물리적 table과 연동되도록 준비하라 라고 jpa에게 알리는 기능
 *  
 *  <prop key="hibernate.hbm2ddl.auto">create</prop>과 같은 속성은
 *  Entity에 지정된 속성에 따라 table을 생성하라는 의미
 *  create : drop and create(기존에 존재하는 table이 있으면 데이터가 있건 없건 지워버림)
 *  default가 create임(보통 실무에서는 update로 지정함 // 공식문서에서는 default가 none으로 되어있음(아무일도 안하는 속성))
 *  	: update로 지정을 하면 table이 없으면 생성을 하고 만약 물리적 table과 구조가 다르면 오류 발생
 * 
 * **주의**
 * - 만약 hib.hbm2ddl.auto 속성을 지정하지 않으면 기본 값으로 create가 지정되어
 *   기존의 table을 drop한 후 재작성 해버리므로 주의해야함
 *   
 * none : 기본값, 아무일도 하지 않는다.
 * create-only : 테이블을 새로 생성
 * drop : 삭제
 * create : drop and create
 * create-drop : 시작할 때 drop and create를 수행하고, session이 만료되면 다시 drop(테스트용으로 주로 사용)
 * 
 * validate : 검증만
 * update : 스키마를 갱신한다고 공식문서에 나와있으나 실제로는 없으면 만들고 있으면 validate를 수행한다. 
 */
@Entity

@Table(name="tbl_ems", schema="emsDB")
public class EmailVO {
	
	/*
	 * 필드변수에 @Id, @Column 속성을 지정하면 -테이블의 칼럼으로 생성
	 * @Id : PRIMARY KEY 칼럼으로 생성
	 * @GeneratedValue() : GenerationType.AUTO로 지정하면 auto_increment를 수행하여 insert할 때 자동 값 채우기가 된다.
	 * 
	 * @Column(name="ems_seq")
	 * table의 칼럼명은 ems_seq로 지정하고
	 * vo클래스의 필드변수는 emsSeq로 하겠다는 의미
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)   // sequence auto increment
	@Column(name="ems_seq")
	private long ems_seq;  // EmailDao의 findBySeq는 VO에 있는 변수명과 일치해야해서 camel case를 사용하고 name을 따로 설정해줌
	
	/*
	 * @Column() 지정하는 속성
	 * nullable : not null과 연관, nullable = false ==> NOT NULL 설정 // nullable = true ==> default null 설정
	 * length : 칼럼의 길이
	 * 			지정하지 않으면 DB기본 값으로 255설정(거의 모든 DB가 같은상황에서 값을 지정하지 않으면 255로 설정함)
	 * 칼럼을 생성할 때는 필드변수의 이름과 같이 생성
	 */
	@Column(name="from_email", nullable = false, length=20)
	private String from_email;  // 보내는 email

	/*
	 * @Column(name="to_email")
	 * - 필드변수와 칼럼의 이름을 달리 설정하고자 할 때
	 */
	@Column(name="to_email", nullable = false)
	private String to_email;   	// 받는 email
	
	@Column(name="send_date", nullable = true)
	private String send_date;	// 보낸 날짜
	
	@Column(name="send_time", nullable = true)
	private String send_time;	// 보낸 시간
	
	/*
	 * @Column(ColumnDefinition = "")
	 * : 칼럼의 type을 DB의 고유한 type으로 강제 적용
	 */
	@Column(name="from_name", nullable = true, columnDefinition="nVARCHAR(20)")
	private String from_name;	// 보낸사람 이름
	
	@Column(nullable = true, columnDefinition = "nVARCHAR(20)")
	private String to_name;		// 받는사람 이름
	
	@Column(nullable = false, columnDefinition = "nVARCHAR(100)")
	private String subject;		// 제목
	
	@Column(nullable = true, columnDefinition = "nVARCHAR(1000)")
	private String content;		// 내용
	
	
}
