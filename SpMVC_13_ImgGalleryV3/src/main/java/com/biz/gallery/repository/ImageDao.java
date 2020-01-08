package com.biz.gallery.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.UpdateProvider;

import com.biz.gallery.domain.ImageFilesVO;
import com.biz.gallery.domain.ImageVO;

public interface ImageDao {

	@Select("SELECT * FROM tbl_gallery")
	public List<ImageVO> selectAll();
	
	@Select(" SELECT * FROM tbl_gallery " 
			+ " WHERE img_seq = #{img_seq} AND "
			+ " img_file = #{img_file} " )
	public ImageVO findBYSeqAndFile(@Param("img_seq") long img_seq,  @Param("img_file") String img_file);
	
	// insert문을 먼저 실행한 후 SelectKey값을 keyColumn을 이용해 참조
	// @SelectKey(keyProperty = "img_seq", keyColumn = "img_seq", before = false, resultType = Long.class, statement= "SELECT SEQ_GALLERY.NEXTVAL FROM DUAL" )
	// @InsertProvider(type=ImagesSQL.class, method="insert_sql")
	// public int insert(ImageVO imageVO);

	// 두개의 테이블을 가지고 데이터를 join해서 사용해야하기 때문에(seq랑 pcode 참조값)
	// insert를 실행하기 전에 selectKey부분을 먼저 실행해서 값이 나오면(true) img_seq값을 보내서 insert를 수행하라
	@SelectKey(keyProperty = "img_seq", keyColumn = "img_seq", before = true, resultType = Long.class, statement= "SELECT SEQ_GALLERY.NEXTVAL FROM DUAL" )
	@InsertProvider(type=ImagesSQL.class, method="insert_sql")
	public int insert(ImageVO imageVO);

	@Select("SELECT * FROM tbl_gallery WHERE img_seq = #{img_seq}")
	@Results(
			value= {
					@Result(property = "img_seq", column = "img_seq"),
					@Result(property = "img_files", column = "img_seq", javaType = List.class, many = @Many(select = "getFiles")) // many == 1:다 관
			})
	public ImageVO findBySeq(String img_seq);

	@UpdateProvider(type=ImagesSQL.class, method="update_sql")
	public int update(ImageVO imageVO);

	@Delete("DELETE FROM tbl_gallery WHERE img_seq = #{img_seq}")
	public int delete(String img_seq);
	
	
	
	@Select("SELECT * FROM tbl_images WHERE img_file_p_code = ${img_file_p_code}")
	public ImageFilesVO getFiles(long img_file_p_code);
	
	@Select("SELECT * FROM tbl_images WHERE img_file_seq = #{img_file_seq}")
	public ImageFilesVO findByFileSeq(String img_file_seq);
	
	@Delete("DELETE FROM tbl_images WHERE img_file_seq = #{img_file_seq}")
	public int deleteFileSeq(String img_file_seq);
	
	
}
