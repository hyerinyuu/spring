package com.biz.rbooks.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

import com.biz.rbooks.domain.ReadBookDTO;

public interface ReadBookDao {

	@Select("SELECT * FROM tbl_read_book")
	public List<ReadBookDTO> selectAll();
	
	@Select("SELECT * FROM tbl_read_book WHERE RB_BCODE = #{rb_bcode}" )
	public ReadBookDTO findByBCode(String rb_bcode);
	
	@InsertProvider(type=ReadBookSQL.class, method="insert_sql" )
	public int insert(ReadBookDTO rbDTO);
	
	@UpdateProvider(type=ReadBookSQL.class, method="update_sql" )
	public int update(ReadBookDTO rbDTO);
	
	@Delete("DELETE tbl_read_book WHERE RB_SEQ = #{rb_seq}" )
	public int delete(long rb_seq);

	
}
