package com.atguigu.crm.handlers;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.entity.CustomerActivity;
import com.atguigu.crm.entity.Order;
import com.atguigu.crm.entity.OrderItem;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.services.OrderService;
@RequestMapping("/order")
@Controller
public class OrderHandler {

	@Autowired
	public OrderService orderService;
	
	@RequestMapping("/details/{Orderid}")
	public String inDispatch(Map<String, Object> map,@PathVariable("Orderid") int id){
		Order order=orderService.getOrder(id);
		Set<OrderItem> items=orderService.getitems(id);
		order.setItems(items);
		map.put("order", order);
		return "order/details";
	
	}
	
	
	@RequestMapping("/list/{id}")
	public String list(
			@PathVariable("id") int id,
			@RequestParam(value = "pageNo", required = false) String pageNoStr,
			Map<String, Object> map) {
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {}
		
		Page<Order> page=orderService.getPage(pageNo,id);
		
		map.put("page", page);
		return "order/list";
	}
}
