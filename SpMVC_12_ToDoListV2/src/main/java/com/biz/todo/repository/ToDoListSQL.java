package com.biz.todo.repository;

import org.apache.ibatis.jdbc.SQL;

import com.biz.todo.domain.ToDoList;

public class ToDoListSQL {

	public final static String complete_sql = "UPDATE tbl_todoList "
			+ " SET td_complete = DECODE(td_complete, 'Y', 'N', 'Y') "
			+ " WHERE td_seq = #{td_seq,jdbcType=VARCHAR} ";
	
	// 익명클래스({{}}})를 이용한 builder pattern 방식
	// 보통 안드로이드 개발에 많이 썼으나 요새 자바에서도 사용하기 시작함
	public String insert_sql(final ToDoList todoList) {
		
		return new SQL() {{
			INSERT_INTO("tbl_todoList");
			
				INTO_COLUMNS("TD_SEQ");
				INTO_COLUMNS("TD_DATE");
				INTO_COLUMNS("TD_TIME");
				INTO_COLUMNS("TD_SUBJECT");
				INTO_COLUMNS("TD_TEXT");
				INTO_COLUMNS("TD_FLAG");
				INTO_COLUMNS("TD_COMPLETE");
				INTO_COLUMNS("TD_ALARM");
				
				INTO_VALUES("SEQ_TODO.NEXTVAL");
				INTO_VALUES("#{td_date,jdbcType=VARCHAR}");
				INTO_VALUES("#{td_time,jdbcType=VARCHAR}");
				INTO_VALUES("#{td_subject,jdbcType=VARCHAR}");
				INTO_VALUES("#{td_text,jdbcType=VARCHAR}");
				INTO_VALUES("#{td_flag,jdbcType=VARCHAR}");
				INTO_VALUES("#{td_complete,jdbcType=VARCHAR}");
				INTO_VALUES("#{td_alarm,jdbcType=VARCHAR}");
			
		}}.toString();
		
	}
	
	public String update_sql() {
		
		return new SQL() {{
			UPDATE("tbl_todolist");
				WHERE("td_seq 	= #{td_seq,jdbcType=VARCHAR}");
				SET("td_date 	= #{td_date,jdbcType=VARCHAR}");
				SET("td_time 	= #{td_time,jdbcType=VARCHAR}");
				SET("td_subject = #{td_subject,jdbcType=VARCHAR}");
				SET("td_text	= #{td_text,jdbcType=VARCHAR}");
				SET("td_flag 	= #{td_flag,jdbcType=VARCHAR}");
				SET("td_complete = #{td_complete,jdbcType=VARCHAR}");
				SET("td_alarm 	= #{td_alarm,jdbcType=VARCHAR}");
		}}.toString();
	}
	
	
	/*
	 * SQL Class
	 * mybatis 3.4(정상작동은 3.5이상) 이상에서 사용되는 동적 query를 지원하는 class
	 */
	public String alarm_sql() {
		
		SQL sql = new SQL();
		sql.UPDATE("tbl_todolist");
		sql.WHERE("td_seq = #{td_seq,jdbcType=VARCHAR}");
		sql.SET("td_alarm = DECODE(td_alarm, 'Y', 'N', 'Y')");
		return sql.toString();
	}
	
}
