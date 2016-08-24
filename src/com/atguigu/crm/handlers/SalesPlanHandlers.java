package com.atguigu.crm.handlers;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.crm.entity.Product;
import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.entity.SalesPlan;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.services.SalesPlanService;

@RequestMapping("/plan")
@Controller
public class SalesPlanHandlers {
	
	@Autowired
	private SalesPlanService planService;
	
	@RequestMapping("/detail/{id}")
	public String detail(@PathVariable("id") Long id, Map<String, Object> map){
		map.put("chance", planService.get(id));
		
		
		return "plan/detail";
	}	
	@ResponseBody
	@RequestMapping(value="/exec-ajax" )
	public String execUpdatePlan(SalesPlan salesPlan ){
		planService.execUpdatePlan(salesPlan);
		return "1";
	}
	@RequestMapping(value="/execution/{id}" )
	public String execution(@PathVariable("id") Long id, Map<String, Object> map){
		map.put("chance", planService.get(id));
		return "plan/exec";
	}
	@ResponseBody
	@RequestMapping(value="/delete-ajax" )
	public String delete(SalesPlan salesPlan ){
		int a=planService.getdelete(salesPlan);
		planService.delete(salesPlan);
		int b=planService.getdelete(salesPlan);
		if(a==1&b==0){
			return "1";
		}else
		{
			return "0";
		}
	}
	
	
	@ResponseBody
	@RequestMapping(value="/savePlan" )
	public String savePlan(SalesPlan salesPlan ){
		long savePlan = planService.savePlan(salesPlan);
		return ""+savePlan;
	}
	@ResponseBody
	@RequestMapping(value="/makes-ajax" )
	public String update(SalesPlan salesPlan ){
		planService.update(salesPlan);
		return "1";
	}
	@RequestMapping(value="/make/{id}" , method=RequestMethod.GET)
	public String input(@PathVariable("id") Long id, Map<String, Object> map){
		map.put("chance", planService.get(id));
		return "plan/make";
	}
	@RequestMapping(value="/list")
	public String list(@RequestParam(value="pageNo", required=false) String pageNoStr,
			HttpSession session,
			Map<String, Object> map){
		User user = (User) session.getAttribute("user");
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {}
		Page<SalesChance> page = planService.getPage(pageNo, user);
		map.put("page", page);
		
		return "plan/list";
	}
}
