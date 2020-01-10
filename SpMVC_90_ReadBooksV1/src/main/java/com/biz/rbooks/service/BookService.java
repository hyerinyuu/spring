package com.biz.rbooks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.rbooks.domain.BookDTO;
import com.biz.rbooks.repository.BookDao;

@Service
public class BookService {

	/* extend해서 재사용 할수도 있으니까 protected로 선언함 */
	protected final BookDao bDao;
	
	@Autowired
	public BookService(BookDao bDao) {
		super();
		this.bDao = bDao;
	}

	public List<BookDTO> selectAll() {

		return bDao.selectAll();
	}
	
	public BookDTO findById(String b_code) {
		return bDao.findById(b_code);
	}

	public int insert(BookDTO bDTO) {
		return bDao.insert(bDTO);
	}

	public int update(BookDTO bDTO) {
		return bDao.update(bDTO);
	}

	public int delete(String b_code) {
		return bDao.delete(b_code);
	}

	
	

}
