package com.biz.rbooks.repository;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.biz.rbooks.domain.ReadBookVO;

public interface ReadBookDao {

	@Select("SELECT * FROM tbl_read_book")
	public List<ReadBookVO> selectAll();
	
	@Select("SELECT * FROM tbl_read_book WHERE RB_SEQ = #{rb_seq}" )
	public ReadBookVO findBySeq(long rb_seq);
	
	/*
	 * 도서코드로 조회하기
	 */
	public List<ReadBookVO> findByBCode(String rb_code);
	
	public int insert(ReadBookVO rBookVO);

	
}
