package com.biz.rbooks.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.UpdateProvider;

import com.biz.rbooks.domain.BookDTO;
import com.biz.rbooks.domain.ReadBookDTO;

public interface BookDao {

	@Select("SELECT * FROM tbl_books")
	public List<BookDTO> selectAll();

	@Select("SELECT * FROM tbl_books WHERE B_CODE = #{b_code}")
	@Results(
				value= {
						@Result(property = "b_code" , column = "b_code"),
						@Result(property = "rbList", column="rb_bcode", javaType=List.class, many = @Many(select = "getBooks"))
				}
			)
	public BookDTO findById(String b_code);
	
	
	@Select("SELECT #{b_name} FROM tbl_books WHERE B_CODE = #{b_code}")
	@Results(
				value= {
						@Result(property = "b_code", column = "b_code"),
						@Result(property = "rbName", column = "rb_bname", javaType=List.class, many = @Many(select = "getBookName")) 
				}
			)
	public BookDTO findByName(String b_code);
	
	@InsertProvider(type = BookSQL.class, method="insert_sql")
	public int insert(BookDTO bDTO);
	
	@UpdateProvider(type = BookSQL.class, method="update_sql")
	public int update(BookDTO bDTO);
	
	@Delete("DELETE FROM tbl_books WHERE B_CODE = #{b_code}")
	public int delete(String b_code);


	@Select("SELECT * FROM tbl_read_book WHERE rb_bcode = #{rb_bcode}")
	public ReadBookDTO getBooks(String rb_bcode);
	
	@Select("SELECT * FROM tbl_read_book WHERE rb_bname = #{rb_b_name}")
	public ReadBookDTO getBookName(String b_code);
	
	@Select("SELECT * FROM tbl_read_book WHERE rb_seq = #{rb_seq}")
	public ReadBookDTO findByBookCode(long rb_seq);
	
	@Delete("DELETE FROM tbl_read_book WHERE rb_seq = #{rb_seq}")
	public int deleteBookCode(String rb_seq);
	
	
}
