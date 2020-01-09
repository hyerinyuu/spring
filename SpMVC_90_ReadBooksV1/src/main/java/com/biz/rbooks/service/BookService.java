package com.biz.rbooks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.rbooks.domain.BookDTO;
import com.biz.rbooks.repository.BookDao;

@Service
public class BookService {

	/* extend해서 재사용 할수도 있으니까 protected로 선언함 */
	protected final BookDao rbDao;
	
	@Autowired
	public BookService(BookDao rbDao) {
		super();
		this.rbDao = rbDao;
	}

	public List<BookDTO> selectAll() {

		return rbDao.selectAll();
	}
	
	public BookDTO selectById(String b_code) {
		return rbDao.selectById(b_code);
	}

	public int insert(BookDTO bDTO) {
		return rbDao.insert(bDTO);
	}

	

}
