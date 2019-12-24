package com.biz.iolist.persistence;

import java.util.List;

import com.biz.iolist.domain.ProductDTO;

public interface ProductDao {

	public List<ProductDTO> selectAll();
	public String getMaxCode();
	public int insert(ProductDTO proDTO);

	
	
}
