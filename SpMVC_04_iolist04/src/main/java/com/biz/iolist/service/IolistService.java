package com.biz.iolist.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.iolist.domain.IolistDTO;
import com.biz.iolist.domain.IolistVO;
import com.biz.iolist.persistence.IolistDao;

@Service
public class IolistService {

	@Autowired
	SqlSession sqlSession;
	
	private IolistDao ioDao;
	
	/*
	 * spring아 내가 ioDao가 필요하니 좀 만들어줘
	 */
	@Autowired
	public void getMapper() {
		
		ioDao = sqlSession.getMapper(IolistDao.class);
	}

	public List<IolistVO> viewAllList() {
		
		// IolistDao ioDao = sqlSession.getMapper(IolistDao.class);
		List<IolistVO> iolist = ioDao.viewSelectAll();
		
		// iolist를 controller로 리턴
		return iolist;
	}

	public int insert(IolistDTO ioDTO) {

		// IolistDao ioDao = sqlSession.getMapper(IolistDao.class);
		int ret = ioDao.insert(ioDTO);
		
		return 0;
	}

	public IolistVO findBySEQ(long io_seq) {
		
		IolistVO io_dto = ioDao.findBySEQ(io_seq);
		return io_dto;
	}


	
	
}
