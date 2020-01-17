package com.biz.rbooks.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

import com.biz.rbooks.domain.ReadBookVO;

public interface ReadBookDao {

	@Select("SELECT * FROM tbl_read_book")
	public List<ReadBookVO> selectAll();
	
	@Select("SELECT * FROM tbl_read_book WHERE RB_SEQ = #{rb_seq}" )
	public ReadBookVO findBySeq(long rb_seq);
	
	// 도서코드로 조회하기
	public List<ReadBookVO> findByBCode(String rb_bcode);
	
	public int insert(ReadBookVO rBookVO);

	@UpdateProvider(type=ReadBookSQL.class, method="update_sql")
	public int update(ReadBookVO rBookVO);

	@Delete("DELETE FROM tbl_read_book WHERE RB_SEQ = #{rb_seq}")
	public int delete(Long rb_seq);

	
}
