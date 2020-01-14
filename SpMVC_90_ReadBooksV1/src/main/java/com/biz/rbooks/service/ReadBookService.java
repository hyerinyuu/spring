package com.biz.rbooks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.rbooks.domain.BookDTO;
import com.biz.rbooks.domain.ReadBookDTO;
import com.biz.rbooks.repository.BookDao;
import com.biz.rbooks.repository.ReadBookDao;

@Service
public class ReadBookService {

	protected final ReadBookDao rbDao;
	protected final BookDao bDao;
	
	
	@Autowired
	public ReadBookService(ReadBookDao rbDao, BookDao bDao) {
		super();
		this.rbDao = rbDao;
		this.bDao = bDao;
	}

	public List<ReadBookDTO> selectAll() {
		return rbDao.selectAll();
	}
	
<<<<<<< HEAD
	public ReadBookDTO findBySeq(String rb_seq) {
		
		return rbDao.findBySeq(rb_seq);
	}
	
=======
>>>>>>> e58f25e4f22b7e4b15d9ec7f7e0cb6c89fe87e93
	public ReadBookDTO findByBCode(String rb_bcode) {
		return rbDao.findByBCode(rb_bcode);
	}

	public int insert(ReadBookDTO rbDTO) {
		return rbDao.insert(rbDTO);
	}

	public int update(ReadBookDTO rbDTO) {
		return rbDao.update(rbDTO);
	}

	public int delete(long rb_seq) {
		return rbDao.delete(rb_seq);
	}
<<<<<<< HEAD
	
=======
>>>>>>> e58f25e4f22b7e4b15d9ec7f7e0cb6c89fe87e93

}
