package com.biz.rbooks.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

import com.biz.rbooks.domain.BookDTO;

public interface BookDao {

	@Select("SELECT * FROM tbl_books")
	public List<BookDTO> selectAll();

	@Select("SELECT * FROM tbl_books WHERE B_CODE = #{b_code}")
	public BookDTO selectById(String b_code);
	
	@InsertProvider(type = BookSQL.class, method="insert_sql")
	public int insert(BookDTO bDTO);
	
	@UpdateProvider(type = BookSQL.class, method="update_sql")
	public int update(BookDTO bDTO);
	
	@Delete("DELETE * FROM tbl_books WHERE B_CODE = #{b_code}")
	public int delete(String b_code);

	
	
	
}
