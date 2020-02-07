package com.biz.friend.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.biz.friend.domain.FriendVO;
import com.biz.friend.persistence.FriendDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FriendService {
	
	private final FriendDao fDao;
	
	public List<FriendVO> selectAll() {
		return fDao.selectAll();
	}

	public int insert(FriendVO fVO) {
		return fDao.insert(fVO);
	}

	public FriendVO findById(Long f_id) {
		return fDao.findById(f_id);
	}

	public int update(FriendVO fVO) {
		return fDao.update(fVO);
	}

	public int delete(Long f_id) {
		return fDao.delete(f_id);
	}

	public List<FriendVO> search(@RequestParam("searchType") String searchtype, @RequestParam("keyword") String keyword) {

		List<FriendVO> fList = null;
		
		if(searchtype.equals("이름")) {
			fList = fDao.findByName(searchtype, keyword);
		}else if(searchtype.equals("전화번호")){
			fList = fDao.findByTel(searchtype, keyword);
		}
		return fList;
	}

}
