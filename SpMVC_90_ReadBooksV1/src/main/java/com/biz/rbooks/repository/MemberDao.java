package com.biz.rbooks.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.biz.rbooks.domain.MemberDTO;

public interface MemberDao {
	
	@Select("SELECT * FROM tbl_member WHRER m_id = #{m_id}")
	public MemberDTO findById(String m_id);
	
	@Insert("INSERT INTO tbl_member( m_id, m_password, m_login_date, m_rem ) "
			+ " VALUES(#{m_id}, #{m_password}, #{m_login_date}, #{m_rem} )")
	public int insert(MemberDTO memberDTO);


}
