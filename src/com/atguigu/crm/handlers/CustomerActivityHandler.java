package com.atguigu.crm.handlers;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.atguigu.crm.entity.CustomerActivity;
import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.services.CustomerActivityService;
@RequestMapping("/activity")
@Controller		  
public class CustomerActivityHandler {
@Autowired
public CustomerActivityService customerActivityService;

@RequestMapping("/delete")
public String delete(@RequestParam(value="customerid") Integer customerid,
		@RequestParam(value="id") Integer activityId,Map<String,Object>map){
	customerActivityService.delete(activityId);
	
	return "redirect:/activity/list?customerid="+customerid;
}

@RequestMapping("/create/in")
public String saveupdate(CustomerActivity customerActivity,Map<String,Object>map,RedirectAttributes attributes){
	Long id = customerActivity.getId();
	Long id2 = customerActivity.getCustomer().getId();
	if(id!=null){
		customerActivityService.update(customerActivity);
		attributes.addFlashAttribute("message", "更新成功!");
	}else{
		customerActivityService.save(customerActivity);
		attributes.addFlashAttribute("message", "保存成功!");
	}
	return "redirect:/activity/list?customerid="+id2;
}

@RequestMapping("/create")
public String inputup(@RequestParam(value="customerid") Integer customerid,
		@RequestParam(value="id") Integer activityId,Map<String,Object>map){
	if(activityId!=0){
	CustomerActivity customerActivity =customerActivityService.getcustomerActivity(activityId);
	map.put("activity", customerActivity);
	}else{
		CustomerActivity customerActivity = new CustomerActivity();
		map.put("activity", customerActivity);
	}
	return "activity/input";
}


	@RequestMapping(value="/list")
	public String list(@RequestParam(value="pageNo", required=false) String pageNoStr,
			@RequestParam(value="customerid") long customerid,
			Map<String, Object> map){
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {}
		Page<CustomerActivity> page = customerActivityService.getPage(pageNo,customerid);
		map.put("page", page);
		map.put("customerid", customerid);
		return "activity/list";
	}
}
