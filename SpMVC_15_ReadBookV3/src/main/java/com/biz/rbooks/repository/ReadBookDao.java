package com.biz.rbooks.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.biz.rbooks.domain.ReadBookVO;

// Dao를 Mapper라고 확인시켜주는 Annotation(굳이 안넣어도 상관x)
@Mapper
public interface ReadBookDao {

	@Select("SELECT * FROM tbl_read_book")
	public List<ReadBookVO> selectAll();

	
	
}
