package com.atguigu.crm.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.mappers.SalesChanceMapper;
import com.atguigu.crm.orm.Page;

@Service
public class SalesChanceService {

	@Autowired
	private SalesChanceMapper salesChanceMapper;
	
	@Transactional
	public void update(SalesChance chance){
		salesChanceMapper.update(chance);
	}
	
	@Transactional(readOnly=true)
	public SalesChance get(Long id){
		return salesChanceMapper.get(id);
	}
	
	@Transactional
	public void delete(Long id){
		salesChanceMapper.delete(id);
	}
	
	@Transactional
	public void save(SalesChance chance){
		salesChanceMapper.save(chance);
	}
	
	@Transactional(readOnly=true)
	public Page<SalesChance> getPage(int pageNo, User createdBy){
		Page<SalesChance> page = new Page<>();
		page.setPageNo(pageNo);
		
		long totalElements = salesChanceMapper.getTotalElements(createdBy);
		page.setTotalElements(totalElements);
		
		int firstIndex = (page.getPageNo() - 1) * page.getPageSize() + 1;
		int endIndex = firstIndex + page.getPageSize();
		
		Map<String, Object> params = new HashMap<>();
		params.put("createBy", createdBy);
		params.put("firstIndex", firstIndex);
		params.put("endIndex", endIndex);
		
		List<SalesChance> content = salesChanceMapper.getContent(params);
		page.setContent(content);
		
		return page;
	}
	@Transactional
	public void stop(Long id) {
		salesChanceMapper.stop(id);
	}


	@Transactional
	public void finish(Long id) {
		salesChanceMapper.finish(id);
		
	}
	
	@Transactional
	public SalesChance inputCustomers(SalesChance salesChance) {
		UUID randomUUID = UUID.randomUUID();
		String NO = String.valueOf(randomUUID);
		salesChance.setDescription(NO);
		 salesChanceMapper.inputCustomers(salesChance);
		 return salesChance;
	}
	@Transactional
	public void inputContacts(SalesChance salesChance) {
		salesChanceMapper.inputContacts(salesChance);
		
	}
	@Transactional
	public List<User> getUsers() {
		return salesChanceMapper.getUsers();
		
	}
	@Transactional
	public void doDatail(SalesChance salesChance) {
		salesChanceMapper.doDatail(salesChance);
		
	}
}
