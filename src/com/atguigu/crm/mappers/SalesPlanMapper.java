package com.atguigu.crm.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.entity.SalesPlan;
import com.atguigu.crm.entity.User;

public interface SalesPlanMapper {

	long getTotalElements(@Param("user") User user);

	List<SalesChance> getContent(Map<String, Object> params);

	SalesChance get(Long id);

	void update(@Param("salesPlan") SalesPlan salesPlan);

	
	void savePlan(SalesPlan salesPlan);

	void delete(SalesPlan salesPlan);

	int getdelete(SalesPlan salesPlan);

	void execUpdatePlan(@Param("salesPlan") SalesPlan salesPlan);
}
