package com.biz.friend.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

import com.biz.friend.domain.FriendVO;

public interface FriendDao {
	
	// 전체 리스트 보는 method
	@Select("SELECT * FROM tbl_friend")
	public List<FriendVO> selectAll();

	// fk로 찾는 method
	@Select("SELECT * FROM tbl_friend WHERE F_ID = #{f_id}")
	public FriendVO findById(Long f_id);
	
	public List<FriendVO> findByName(@Param("searchtype")String searchtype, @Param("f_name") String f_name);
	public List<FriendVO> findByTel(@Param("searchtype")String searchtype, @Param("f_tel") String f_tel);
	
	// insert
	public int insert(FriendVO fVO);
	// update
	public int update(FriendVO fVO);
	// delete
	@Delete("DELETE FROM tbl_friend WHERE f_id = #{f_id}")
	public int delete(long f_id);

	
}
