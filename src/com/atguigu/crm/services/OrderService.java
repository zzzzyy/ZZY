package com.atguigu.crm.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.Order;
import com.atguigu.crm.entity.OrderItem;
import com.atguigu.crm.mappers.OrderMapper;
import com.atguigu.crm.orm.Page;

@Service
public class OrderService {
	
	@Autowired
	public OrderMapper orderMapper;
	
	
	
	@Transactional
	public Page<Order> getPage(int pageNo,int id) {
		Page<Order> page = new Page<>();
		page.setPageNo(pageNo);
		
		long totalElements = orderMapper.getTotalElements(id);
		page.setTotalElements(totalElements);
		
		int firstIndex = (page.getPageNo() - 1) * page.getPageSize() + 1;
		int endIndex = firstIndex + page.getPageSize();
		
		Map<String, Object> params = new HashMap<>();
		params.put("chanceid", id);
		params.put("firstIndex", firstIndex);
		params.put("endIndex", endIndex);
		
		List<Order> content = orderMapper.getContent(params);
		page.setContent(content);
		
		return page;
	}



	public Set<OrderItem> getitems(int id) {
		return orderMapper.getitems(id);
	}



	public Order getOrder(int id) {
		// TODO Auto-generated method stub
		return orderMapper.getOrder(id);
	}

}
