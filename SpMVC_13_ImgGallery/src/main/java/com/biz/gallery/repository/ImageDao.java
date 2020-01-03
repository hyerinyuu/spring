package com.biz.gallery.repository;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;

import com.biz.gallery.domain.ImageVO;

public interface ImageDao {

	@Select("SELECT * FROM tbl_gallery")
	public List<ImageVO> selectAll();
	
	@InsertProvider(type=ImagesSQL.class, method="insert_sql")
	public int insert(ImageVO imageVO);
	
	
}
