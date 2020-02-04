package com.biz.bbs.service;

import java.util.List;

import com.biz.bbs.domain.BBsVO;

public interface BBsService {

	public List<BBsVO> selectAll();
	public BBsVO findById(long bbs_id);
	
	// 글 제목으로 검색하는 method
	public List<BBsVO> findBySubject(String subject);
	// 글 작성자로 검색하는 method
	public List<BBsVO> findByWriters(String writer);
	// 글 제목+작성자로 검색하는 method
	public List<BBsVO> findBySubjectAndWriter(String subject, String writer);
	
	
	// insert와 update를 처리할 service
	public int save(BBsVO bbsVO);
	public int delete(long bbs_id);
	public BBsVO reply(BBsVO bbsVO);
	
}
