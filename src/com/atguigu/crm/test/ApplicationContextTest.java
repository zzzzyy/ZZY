package com.atguigu.crm.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.mappers.SalesChanceMapper;
import com.atguigu.crm.mappers.UserMapper;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.services.SalesChanceService;

public class ApplicationContextTest {

	private ApplicationContext ctx = null;
	private UserMapper userMapper = null;
	private SalesChanceMapper salesChanceMapper = null;
	private SalesChanceService salesChanceService = null;
	
	{
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		userMapper = ctx.getBean(UserMapper.class);
		salesChanceMapper = ctx.getBean(SalesChanceMapper.class);
		salesChanceService = ctx.getBean(SalesChanceService.class);
	}
	
	@Test
	public void testGetPage(){
		User createBy = new User();
		createBy.setId(21L);
		Page<SalesChance> page = salesChanceService.getPage(2, createBy);
		
		System.out.println(page.getTotalPages());
		for(SalesChance chance: page.getContent()){
			System.out.println(chance.getId());
		}
	}
	
	@Test
	public void testGetContent(){
		User createBy = new User();
		createBy.setId(21L);
		
		int firstIndex = 1;
		int endIndex = 4;
		
		Map<String, Object> params = new HashMap<>();
		params.put("createBy", createBy);
		params.put("firstIndex", firstIndex);
		params.put("endIndex", endIndex);
		
		List<SalesChance> content = salesChanceMapper.getContent(params);
		
		for(SalesChance chance: content){
			System.out.println(chance.getId());
		}
	}
	
	@Test
	public void testGetTotalElements(){
		User createBy = new User();
		createBy.setId(21L);
		
		long totalElements = salesChanceMapper.getTotalElements(createBy);
		System.out.println(totalElements);
	}
	
	@Test
	public void testGetByName() {
		User user = userMapper.getByName("bcde");
		
		System.out.println(user.getPassword());
		System.out.println(user.getRole().getName());
	}

}
