package com.atguigu.crm.mappers;

import java.util.List;
import java.util.Map;

import com.atguigu.crm.entity.CustomerActivity;
import com.atguigu.crm.entity.SalesChance;

public interface CustomerActivityMapper {

	long getTotalElements(long customerid);

	List<CustomerActivity> getContent(Map<String, Object> params);

	void save(CustomerActivity customerActivity);

	CustomerActivity getcustomerActivity(Integer activityId);

	void update(CustomerActivity customerActivity);

	void delete(Integer activityId);

}
