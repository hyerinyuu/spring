package com.biz.ems.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.biz.ems.domain.EmailVO;
import com.biz.ems.repository.EmailDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MailService {

	private final EmailDao emsDao;
	
	public int insert(EmailVO emailVO) {
		
		// .save() ==> insert와 udpate를 같이 수행하는 method
		// id값이 있으면 insert를 수행하고 id값이 없으면 update를 수행함
		emsDao.save(emailVO);
		return 0;
	}
	
	public List<EmailVO> selectAll(){
		
		List<EmailVO> mailList = (List<EmailVO>) emsDao.findAll();
		return mailList;
	}
	
	public EmailVO findBySeq(long ems_seq) {
		
		EmailVO emailVO = emsDao.findByEmsSeq(ems_seq);
		return emailVO;
	}

	public int update(EmailVO emailVO) {
		emsDao.save(emailVO);
		return 0;
	}

	public int delete(String ems_seq) {
		emsDao.deleteById(Long.valueOf(ems_seq));
		return 0;
	}
	
}
