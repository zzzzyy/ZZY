package com.atguigu.crm.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.Role;
import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.mappers.UserMapper;
import com.atguigu.crm.orm.Page;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Transactional(readOnly=true)
	public User login(String name, String password){
		User user = userMapper.getByName(name);
		
		if(user != null
				&& user.getEnabled() == 1
				&& user.getPassword().equals(password)){
			return user;
		}
		
		return null;
	}

	
	@Transactional
	public Page getPage(int pageNo) {
		Page<SalesChance> page = new Page<>();
		page.setPageNo(pageNo);
		
		long totalElements = userMapper.getTotalElements();
		page.setTotalElements(totalElements);
		
		int firstIndex = (page.getPageNo() - 1) * page.getPageSize() + 1;
		int endIndex = firstIndex + page.getPageSize();
		
		Map<String, Object> params = new HashMap<>();
		
		params.put("firstIndex", firstIndex);
		params.put("endIndex", endIndex);
		
		List<SalesChance> content = userMapper.getContent(params);
		page.setContent(content);
		return page;
		
	}


	@Transactional
	public List<Role> getroleList() {
		return userMapper.getroleList();
	}


	@Transactional
	public void save(User user) {
		userMapper.save(user);
		
	}


	@Transactional
	public void delete(Long id) {
		
		userMapper.delete(id);
	}


	@Transactional
	public User get(Long id) {
		
		return userMapper.get(id);
	}


	@Transactional
	public void update(User user) {
		userMapper.update(user);
	}
}
