package com.biz.iolist.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.iolist.domain.ProductDTO;
import com.biz.iolist.persistence.ProductDao;

@Service
public class ProductService {

	@Autowired
	SqlSession sqlSession;

	public List<ProductDTO> getAllList() {
		
		ProductDao pDao = sqlSession.getMapper(ProductDao.class);
		
		
		List<ProductDTO> proList = pDao.selectAll();
		return proList;
	}

	public int insert(ProductDTO proDTO) {
		
		ProductDao proDao = sqlSession.getMapper(ProductDao.class);
		
		// 상품코드 자동으로 생성하게 해서
		// p_code에 자동저장되게 하기
		String p_code = proDao.getMaxCode();
		String p_num = p_code.substring(1);
		int int_pcode = Integer.valueOf(p_num);
		
		// p
		p_code = p_code.substring(0, 1);
		p_code += String.format("%04d",	int_pcode);
		
		proDTO.setP_code(p_code);
		int ret = proDao.insert(proDTO);
		
		return ret;
		
	}
	
}
