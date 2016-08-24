package com.atguigu.crm.mappers;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.atguigu.crm.entity.Order;
import com.atguigu.crm.entity.OrderItem;

public interface OrderMapper {

	public long getTotalElements(int id);

	public List<Order> getContent(Map<String, Object> params);

	public Set<OrderItem> getitems(int id);

	public Order getOrder(int id);

}
