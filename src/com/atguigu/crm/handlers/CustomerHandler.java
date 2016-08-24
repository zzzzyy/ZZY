package com.atguigu.crm.handlers;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.entity.Contact;
import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.entity.Product;
import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.entity.SalesPlan;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.services.CustomerServices;

@RequestMapping("/customer")
@Controller
public class CustomerHandler {
	@Autowired
	private CustomerServices customerServices;
	
	
	@ResponseBody
	@RequestMapping(value="/delete-ajx" )
	public String deleteajx(@RequestParam(value="id", required=false) Integer id){
		if(id!=null){
			customerServices.deleteajx(id);
		}
		
		return id==null?"0":"1";
	}
	
	@RequestMapping("/create/update")
	public String update(Map<String, Object> map,Customer customer,RedirectAttributes attributes){
		customerServices.update(customer);
		attributes.addFlashAttribute("message", "操作成功!");
		return "redirect:/customer/list";
	
	}
	
	@RequestMapping("/create/{id}")
	public String input(@PathVariable("id") int id,Map<String, Object> map){
		Customer customer = customerServices.getCustomer(id);
		
		Set<Contact> contacts=customerServices.getContacts(id);
		customer.setContacts(contacts);
		map.put("customer", customer);
		map.put("regions", customerServices.regions());
		map.put("levels", customerServices.levels());						
		map.put("satisfies", customerServices.gatIsfies());						
		map.put("credits", customerServices.credits());						
		
		return "customer/input";
	
	}

	@RequestMapping(value = "/list")
	public String list(
			@RequestParam(value = "pageNo", required = false) String pageNoStr,
			HttpSession session, Map<String, Object> map,
			HttpServletRequest request) {
		// 1. 获取查询条件的请求参数. 能不能批量的获取查询条件的请求参数.
		// 1.1 查询条件的请求参数要和一般的参数有区别: 使请求参数有前缀: search_
		// 1.2 可以通过 request.getParameterMap 来编写一个工具方法. 来获取指定前缀的请求参数的 Map.
		Map<String, Object> parameters = WebUtils.getParametersStartingWith(
				request, "search_");
		System.out.println(parameters);

		// 2. 如何能能够保证查询条件不丢: 在点击 "翻页" 链接时, 可以保留查询条件.
		// 2.1 把前面获取的请求参数的 Map 序列化为一个查询字符串: {LIKE_contact=c, LIKE_custName=a,
		// LIKE_title=b}
		// search_LIKE_contact=c&search_LIKE_custName=a&search_LIKE_title=b
		// 2.2 把改查询字符串传回到客户端
		// 2.3 点击 "翻页" 链接时, 在传回来.
		String queryString = encodeParameterMapToQueryString(parameters,
				"search_");
		map.put("queryString", queryString);

		User user = (User) session.getAttribute("user");
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {
		}
		Page<Customer> page = customerServices
				.getPage(pageNo, user, parameters);
		map.put("page", page);
		map.put("regions", customerServices.regions());
		map.put("levels", customerServices.levels());
		return "customer/list";
	}

	private String encodeParameterMapToQueryString(
			Map<String, Object> parameters, String prefix) {
		StringBuilder queryString = new StringBuilder();

		if (parameters != null && parameters.size() > 0) {
			for (Map.Entry<String, Object> entry : parameters.entrySet()) {
				String key = entry.getKey();
				Object val = entry.getValue();

				if (val == null || val.toString().trim().equals("")) {
					continue;
				}

				queryString.append("&").append(prefix).append(key).append("=")
						.append(val);
			}
		}
		if (queryString.length() > 0) {
			return queryString.toString();
		}

		return null;
	}

}
