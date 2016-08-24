package com.atguigu.crm.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.entity.SalesPlan;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.mappers.SalesPlanMapper;
import com.atguigu.crm.orm.Page;

@Service
public class SalesPlanService {
	@Autowired
	private SalesPlanMapper planMapper;
	
	@Transactional
	public Page<SalesChance> getPage(int pageNo, User createdBy) {
		Page<SalesChance> page = new Page<>();
		page.setPageNo(pageNo);
		
		long totalElements = planMapper.getTotalElements(createdBy);
		page.setTotalElements(totalElements);
		
		int firstIndex = (page.getPageNo() - 1) * page.getPageSize() + 1;
		int endIndex = firstIndex + page.getPageSize();
		
		Map<String, Object> params = new HashMap<>();
		params.put("createBy", createdBy);
		params.put("firstIndex", firstIndex);
		params.put("endIndex", endIndex);
		
		List<SalesChance> content = planMapper.getContent(params);
		page.setContent(content);
		
		return page;
	}

	
	@Transactional
	public Object get(Long id) {
		return planMapper.get(id);
	}






	@Transactional
	public void update(SalesPlan salesPlan) {
		planMapper.update(salesPlan);
		
	}


	@Transactional
	public long savePlan(SalesPlan salesPlan) {
		planMapper.savePlan(salesPlan);
		return salesPlan.getId();
		
	}


	@Transactional
	public long delete(SalesPlan salesPlan) {
		planMapper.delete(salesPlan);
		return salesPlan.getId();
	}


	@Transactional
	public int getdelete(SalesPlan salesPlan) {
		return planMapper.getdelete(salesPlan);
		 
	}


	@Transactional
	public void execUpdatePlan(SalesPlan salesPlan) {
		 planMapper.execUpdatePlan(salesPlan);
		
	}

}
