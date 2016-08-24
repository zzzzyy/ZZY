package com.atguigu.crm.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.Product;
import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.entity.Storage;
import com.atguigu.crm.mappers.StorageMapper;
import com.atguigu.crm.orm.Page;

@Service
public class StorageService {
	@Autowired
	private StorageMapper storageMapper;
	@Transactional
	public Page getPage(int pageNo) {
		Page<SalesChance> page = new Page<>();
		page.setPageNo(pageNo);
		
		long totalElements = storageMapper.getTotalElements();
		page.setTotalElements(totalElements);
		
		int firstIndex = (page.getPageNo() - 1) * page.getPageSize() + 1;
		int endIndex = firstIndex + page.getPageSize();
		
		Map<String, Object> params = new HashMap<>();
		
		params.put("firstIndex", firstIndex);
		params.put("endIndex", endIndex);
		
		List<SalesChance> content = storageMapper.getContent(params);
		page.setContent(content);
		return page;
		 
	}
	@Transactional
	public List<Product> getProductList() {
		return storageMapper.getProductList();
		
	}
	@Transactional
	public void save(Storage storage) {
		storageMapper.save(storage);
		
	}

	public void delete(int id) {
		storageMapper.delete(id);
	}

	public Storage get(Long id) {
		return storageMapper.get(id);
		
	}

	public void update(Storage storage,int add) {
		int a=storage.getStockCount()+add;
				System.out.println(a);
		 storage.setStockCount(a);
		storageMapper.update(storage);
	}

	

}
