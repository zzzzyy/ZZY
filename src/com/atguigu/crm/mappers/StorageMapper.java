package com.atguigu.crm.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.atguigu.crm.entity.Product;
import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.entity.Storage;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.orm.Page;

public interface StorageMapper {

	long getTotalElements();

	List<SalesChance> getContent(Map<String, Object> params);

	List<Product> getProductList();

	void save(Storage storage);

	void delete(@Param("id")int id);

	Storage get(@Param("id") Long id);

	void update(Storage storage);
	
	
	
	
	
}
