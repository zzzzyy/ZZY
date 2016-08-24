package com.atguigu.crm.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.entity.CustomerService;
import com.atguigu.crm.entity.User;

public interface CustomerServiceMapper {

	ArrayList<Customer> getCustomer();

	void createSave(CustomerService customerService);

	long getTotalElements2(Map<String, Object> mybatisParams);

	List<CustomerService> getContent(Map<String, Object> mybatisParams);

	List<User> getUsers();

	void allotsave(CustomerService customerService);

	void delete(Integer id);

	CustomerService getarr(Integer id);

	void dealsave(CustomerService customerService);

	void feedbackSave(CustomerService customerService);

}
