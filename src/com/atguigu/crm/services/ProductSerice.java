package com.atguigu.crm.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.atguigu.crm.entity.Product;
import com.atguigu.crm.mappers.ProductMapper;
import com.atguigu.crm.orm.Page;

@Service
public class ProductSerice {
	@Autowired
	private ProductMapper productMapper;
	
	@Transactional(readOnly=true)
	public Page<Product> getPage(int pageNo){
		Page<Product> page = new Page<>();
		page.setPageNo(pageNo);
		
		long totalElements = productMapper.getTotalElements();
		page.setTotalElements(totalElements);
		
		int firstIndex = (page.getPageNo() - 1) * page.getPageSize() + 1;
		int endIndex = firstIndex + page.getPageSize();	
		
		Map<String, Object> params = new HashMap<>();
		params.put("firstIndex", firstIndex);
		params.put("endIndex", endIndex);
		
		
		List<Product> content = productMapper.getContent(params);
		page.setContent(content);
		return page;
	}

	@Transactional
	public void save(Product product){
		productMapper.save(product);
	}
	@Transactional
	public void delete(Long id) {
		productMapper.delete(id);
		
	}
	@Transactional
	public Product get(Long id) {
		Product product=productMapper.get(id);
		return product;
	}
	@Transactional
	public void update(Product product) {
		productMapper.update(product);
		
	}

}
