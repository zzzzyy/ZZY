package com.atguigu.crm.handlers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.entity.CustomerService;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.services.CustomerServiceService;
@RequestMapping("/service")
@Controller
public class CustomerServiceHandlers {
	@Autowired
	public CustomerServiceService customerServiceService;
	
	@RequestMapping("/archive")
	public String archive(@RequestParam("id") Integer id,Map<String, Object> map){
		CustomerService customerService=customerServiceService.getarr(id);
		map.put("service", customerService);
		return "service/archive/archive";
	}
	@RequestMapping("/feedback/save")
	public String feedbackSave(CustomerService customerService){
		
		customerServiceService.feedbackSave(customerService);
		
		return "redirect:/service/allot/list/4";
	}
	@RequestMapping("/feedback")
	public String feedback(@RequestParam("id") Integer id,Map<String, Object> map){
		CustomerService customerService=customerServiceService.getarr(id);
		map.put("service", customerService);
		return "service/feedback/feedback";
	}
	
	@RequestMapping("/deal/save")
	public String dealsave(CustomerService customerService){
		Long id = customerService.getId();
		System.out.println(id);
		customerServiceService.dealsave(customerService);
		
		return "redirect:/service/allot/list/3";
	}
	
	@RequestMapping("/deal")
	public String deal(@RequestParam("id") Integer id,Map<String, Object> map){
		CustomerService customerService=customerServiceService.getarr(id);
		map.put("service", customerService);
		return "service/deal/deal";
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("id") Integer id){
		customerServiceService.delete(id);
		return "redirect:/service/allot/list/1";
	}
	
	@ResponseBody
	@RequestMapping("/allot/ajxx")
	public String allotsave(CustomerService customerService){
		customerServiceService.allotsave(customerService);
		return "1";
	}
	
	@RequestMapping("/allot/list/{sign}")
	public String allotList(@PathVariable("sign")Integer sign,HttpSession session,
			Map<String, Object> map,HttpServletRequest request,
			@RequestParam(value = "pageNo", required = false) String pageNoStr){
			Map<String, Object> parameters = WebUtils.getParametersStartingWith(request, "search_");
			System.out.println(parameters);
			String queryString = encodeParameterMapToQueryString(parameters,
					"search_");
			map.put("queryString", queryString);
			List<User> users=customerServiceService.getUsers();
			map.put("users", users);
			User user = (User) session.getAttribute("user");
			int pageNo = 1;
			try {
				pageNo = Integer.parseInt(pageNoStr);
			} catch (Exception e) {
			}
			Page<CustomerService> page = customerServiceService.getPage(pageNo, user, parameters,sign);
			map.put("page", page);
			
			if(sign==1){
				return "service/allot/list";
			}else if(sign==4){
				return "service/archive/list";
			}else if(sign==2){
				return "service/deal/list";
			}else if(sign==3){
				return "service/feedback/list";
			}else{
				return null;
			}
			
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
	
	
	
	
	@RequestMapping("/create/save")
	public String createSave(CustomerService customerService,
			Map<String, Object> map){
		customerServiceService.createSave(customerService);	
		return "redirect:/service/allot/list/1";
	}
	
	@RequestMapping(value="/create")
	public String list(HttpSession session,Map<String, Object> map){
		User user = (User) session.getAttribute("user");
		ArrayList<String> arrayList = new ArrayList<>();
		arrayList.add("咨询");
		arrayList.add("投诉");
		arrayList.add("建议");
		ArrayList<Customer> Customers=customerServiceService.getCustomer();
		map.put("user", user);
		map.put("serviceTypes", arrayList);
		map.put("customers", Customers);
		
		return "service/input";
	}
}
