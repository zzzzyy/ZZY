package com.atguigu.crm.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.CustomerActivity;
import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.mappers.CustomerActivityMapper;
import com.atguigu.crm.orm.Page;

@Service
public class CustomerActivityService {
	
	@Autowired
	public CustomerActivityMapper customerActivityMapper;
	@Transactional
	public Page<CustomerActivity> getPage(int pageNo, long customerid) {
		Page<CustomerActivity> page = new Page<>();
		page.setPageNo(pageNo);
		
		long totalElements = customerActivityMapper.getTotalElements(customerid);
		page.setTotalElements(totalElements);
		
		int firstIndex = (page.getPageNo() - 1) * page.getPageSize() + 1;
		int endIndex = firstIndex + page.getPageSize();
		
		Map<String, Object> params = new HashMap<>();
		params.put("customerid", customerid);
		params.put("firstIndex", firstIndex);
		params.put("endIndex", endIndex);
		
		List<CustomerActivity> content = customerActivityMapper.getContent(params);
		page.setContent(content);
		
		return page;
	}
	@Transactional
	public void save(CustomerActivity customerActivity) {
		customerActivityMapper.save(customerActivity);
	}
	@Transactional
	public CustomerActivity getcustomerActivity(Integer activityId) {
		return customerActivityMapper.getcustomerActivity(activityId);
	}
	@Transactional
	public void update(CustomerActivity customerActivity) {
		customerActivityMapper.update(customerActivity);
		
	}
	public void delete(Integer activityId) {
		customerActivityMapper.delete(activityId);
		
	}


}
