package com.biz.iolist.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.biz.iolist.domain.ProductDTO;

public interface ProductDao {

	public List<ProductDTO> selectAll();
	public String getMaxCode();
	public int insert(ProductDTO proDTO);
	
	// 변수명을 원래 칼럼명과 다르게 하고 싶을때는 param을 사용한다.
	public List<ProductDTO> findByName(@Param("p_name")String strText);
	public ProductDTO findByPcode(String strText);


	
}
