package com.biz.ems.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.biz.ems.domain.EmailVO;

@Repository
public interface EmailDao extends CrudRepository<EmailVO, Long> {

	public EmailVO findByEmsSeq(long ems_seq);
	
	/*
	// return type이 List일 경우에는 findBy가 아닌 findAllBy를 사용해야 한다.
	// select * from tbl_ems WHERE from_email = #{from_email}
	public List<EmailVO> findByFromEmail(@Param("from_email")String from_email);
	public List<EmailVO> findAllByFromEmailOrderByEmsSeqAsc(@Param("from_email")String from_email);
	
	
	public List<EmailVO> findAllByFromEmailGraterThan(String send_date);
	public List<EmailVO> findAllByFromEmailLessThan(String send_date);
	
	
	List<EmailVO> findAllByFromEmailAndFromName(@Param("from_email")String from_email, @Param("from_name")String from_name);
	List<EmailVO> fineAllByFromEmailOrFromName(@Param("from_email")String from_email, @Param("from_name")String from_name);
	*/
	
	
	
}
