package com.atguigu.crm.mappers;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.atguigu.crm.entity.Contact;
import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.entity.Dict;
import com.atguigu.crm.entity.SalesChance;

public interface CustomerMapper {
	long getTotalElements2(Map<String, Object> parameters);

	List<Customer> getContent(Map<String, Object> mybatisParams);

	List<Dict> regions();

	List<Dict> levels();

	Customer getCustomer(long id);

	Set<Contact> customerMapper(long id);

	List<Dict> gatIsfies();

	List<Dict> credits();


	void update(Customer customer);

	

	void deleteajx(Integer id);

	

}
