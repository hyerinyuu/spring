package com.biz.iolist.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.iolist.domain.DeptDTO;
import com.biz.iolist.persistence.DeptDao;

@Service
public class DeptService {
	
	@Autowired
	SqlSession sqlSession;
	
	DeptDao deptDao;
	
	@Autowired
	public void getMapper() {
		deptDao = sqlSession.getMapper(DeptDao.class);
	}
	
	public List<DeptDTO> getAllList(){
		
	//	DeptDao deptDao = sqlSession.getMapper(DeptDao.class);
		
		List<DeptDTO> deptList = deptDao.selectAll();
		
		return deptList;
		
	}

	public int insert(DeptDTO deptDTO) {
		
		// DeptDao deptDao = sqlSession.getMapper(DeptDao.class);
		
		/*
		 * 거래처코드 자동생성을 해서
		 * deptDTO의 d_code에 저장 수행
		 */
		
		// D0900
		String d_code = deptDao.getDcode();
		
		// 0900만 추출
		String d_code_num = d_code.substring(1);
		
		// 901
		int int_dcode = Integer.valueOf(d_code_num) +1;
		
		// d만 추출
		d_code = d_code.substring(0,1);
		
		// "d" + "0901"을 문자열로
		d_code += String.format("%04d", int_dcode);
		
		// 생성된 코드 setting
		deptDTO.setD_code(d_code);
		int ret = deptDao.insert(deptDTO);
		return ret;
	}

	public DeptDTO findByDcode(String d_code) {

		// DeptDao deptDao = sqlSession.getMapper(DeptDao.class);
		
		DeptDTO dDTO = deptDao.findByDcode(d_code);
		
		return dDTO;
	}

	public int delete(String d_code) {
		
		// DeptDao deptDao = sqlSession.getMapper(DeptDao.class);
		
		int ret = deptDao.delete(d_code);
		return ret;
		
	}

	public int update(DeptDTO deptDTO) {
		
		// DeptDao deptDao = sqlSession.getMapper(DeptDao.class);
		int ret = deptDao.update(deptDTO);
		
		return ret;
	}

	public List<DeptDTO> selectNameSearch(String strText) {

		/*
		 * 매개변수로 전달받은 strText문자열을 
		 * 1. 거래처 코드로 조회를 해보고
		 *  조회가 되면 해당 거래처정보를 리스트에 담아서 return
		 *  조회가 되지 않으면
		 *  2. 거래처 이름으로 조회를 하여
		 *  리스트를 return
		 */
		List<DeptDTO> deptList;
		DeptDTO dDTO = deptDao.findByDcode(strText);
		if(dDTO != null) {
			deptList = new ArrayList<DeptDTO>();
			deptList.add(dDTO);
		}else {
			deptList = deptDao.findByDname(strText);
		}
		return deptList;
	}
	
	
}
