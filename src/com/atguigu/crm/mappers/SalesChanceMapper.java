package com.atguigu.crm.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.entity.User;

public interface SalesChanceMapper {

	void update(SalesChance chance);
	
	SalesChance get(@Param("id") Long id);
	
	void delete(@Param("id") Long id);
	
	void save(SalesChance chance);
	
	List<SalesChance> getContent(Map<String, Object> params);
	
	long getTotalElements(@Param("createBy") User createBy);

	void stop(Long id);

	void finish(Long id);

	List<User> getUsers();

	void inputContacts(@Param("salesChance") SalesChance salesChance);

	void inputCustomers( SalesChance salesChance);

	void doDatail(SalesChance salesChance);
	
}
