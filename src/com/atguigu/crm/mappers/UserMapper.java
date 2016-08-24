package com.atguigu.crm.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.atguigu.crm.entity.Role;
import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.orm.Page;

public interface UserMapper {

	User getByName(@Param("name") String name);

	long getTotalElements();

	List<SalesChance> getContent(Map<String, Object> params);

	List<Role> getroleList();

	void save(User user);

	void delete(@Param("id") Long id);

	User get(Long id);

	void update(User user);

	
	
}
