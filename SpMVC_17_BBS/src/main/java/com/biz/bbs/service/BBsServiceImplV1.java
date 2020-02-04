package com.biz.bbs.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.biz.bbs.domain.BBsVO;
import com.biz.bbs.mapper.BBsDao;

import lombok.RequiredArgsConstructor;

@Service("bServiceV1")
@RequiredArgsConstructor
public class BBsServiceImplV1 implements BBsService{
	
	// protected : 상속받은 class에서만 접근 가능
	// 변수 앞에 접근자를 붙이지 않으면 같은 *패키지*에서는 public으로 인식하지만
	// *패키지가 다르면* private로 인식함
	protected final BBsDao bbsDao;

	@Override
	public List<BBsVO> selectAll() {
		// return bbsDao.selectAll();
		return bbsDao.selectMain();
	}

	@Override
	public BBsVO findById(long bbs_id) {
		return bbsDao.findById(bbs_id);
	}

	@Override
	public List<BBsVO> findBySubject(String subject) {
		return null;
	}

	@Override
	public List<BBsVO> findByWriters(String writer) {
		return null;
	}

	@Override
	public List<BBsVO> findBySubjectAndWriter(String subject, String writer) {
		return null;
	}

	@Override
	public int save(BBsVO bbsVO) {
	
		long bbs_id = bbsVO.getBbs_id();
		// bbs_id > 0 ==> 아이디 있음 ==> update수행
		if(bbs_id > 0) {
			bbsDao.update(bbsVO);
		// 아이디없으면 ==> insert수행	
		}else {
			bbsDao.insert(bbsVO);
		}
		return 0;
	}

	@Override
	public int delete(long bbs_id) {
		return 0;
	}

	@Override
	public BBsVO reply(BBsVO bbsVO) {
		
		bbsVO.setBbs_p_id(bbsVO.getBbs_id());
		
		// pk인 p_id의 현재값을 p_id에 주입하고
		// id를 0으로 세팅하면
		// id는 새로 생성이 되고 기존 게시글의 id값이 p_id로 저장된다. 
		bbsVO.setBbs_id(0);
		String subject = "re : " + bbsVO.getBbs_subject();
		bbsVO.setBbs_subject(subject);
		bbsDao.insert(bbsVO);
		
		return null;
	}

	
	
}
