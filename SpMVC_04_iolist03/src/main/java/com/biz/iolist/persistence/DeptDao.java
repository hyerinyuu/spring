package com.biz.iolist.persistence;

import java.util.List;

import com.biz.iolist.domain.DeptDTO;

public interface DeptDao {

	public List<DeptDTO> selectAll();
	public List<DeptDTO> findAll();
	public int insert(DeptDTO deptDTO);
	
	public String getDcode();
	public DeptDTO findByDcode(String d_code);
	public int delete(String d_code);
	public int update(DeptDTO deptDTO);
}
