package com.atguigu.crm.handlers;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.services.SalesChanceService;

@RequestMapping("/chance")
@Controller
public class SalesChanceHandler {

	@Autowired
	private SalesChanceService salesChanceService;
	
	
	@RequestMapping(value="/dispatch",method=RequestMethod.POST)
	public String doDatail(SalesChance salesChance){
		salesChanceService.doDatail(salesChance);
		
		return "redirect:/plan/list";
	}
	
	@RequestMapping("/dispatch/{id}")
	public String toDatail(@PathVariable("id") Long id, Map<String, Object> map){
		
		SalesChance salesChance = salesChanceService.get(id);
		List<User> users= salesChanceService.getUsers();
		salesChance.setDesigneeDate(new Date());
		map.put("users", users);
		map.put("chance", salesChance);
		return "/chance/dispatch";
	}
	
	@RequestMapping("/finish/{id}")
	public String finish(@PathVariable("id") Long id, RedirectAttributes attributes){
		salesChanceService.finish(id);
		SalesChance salesChance = salesChanceService.get(id);
					salesChance = salesChanceService.inputCustomers(salesChance);
		salesChanceService.inputContacts(salesChance);
		
		attributes.addFlashAttribute("message", "执行开发成功!");
		return "redirect:/plan/list";
	}
	@RequestMapping("/stop/{id}")
	public String stop(@PathVariable("id") Long id, RedirectAttributes attributes){
		salesChanceService.stop(id);
		attributes.addFlashAttribute("message", "设置开发失败执行!");
		return "redirect:/plan/list";
	}
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public String update(SalesChance chance, RedirectAttributes attributes){
		salesChanceService.update(chance);
		attributes.addFlashAttribute("message", "操作成功!");
		return "redirect:/chance/list";
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public String input(@PathVariable("id") Long id, Map<String, Object> map){
		map.put("chance", salesChanceService.get(id));
		return "chance/input";
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id, RedirectAttributes attributes){
		salesChanceService.delete(id);
		attributes.addFlashAttribute("message", "操作成功!");
		return "redirect:/chance/list";
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public String save(SalesChance chance, 
			HttpSession session,
			RedirectAttributes attributes){
		User user = (User) session.getAttribute("user");
		chance.setCreateBy(user);
		salesChanceService.save(chance);
		
		attributes.addFlashAttribute("message", "操作成功!");
		return "redirect:/chance/list";
	}
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String input(Map<String, Object> map){
		SalesChance chance = new SalesChance();
		chance.setCreateDate(new Date());
		map.put("chance", chance);
		
		return "chance/input";
	}
	
	/**
	 * 1. 对 SalesChance 的不带查询条件的分页.
	 * 2. 显示的是 "才创建" 的销售机会, 且是 "当前登录用户创建" 的销售机会. 
	 * 1). 才创建: status=1 
	 * 2). 当前登录用户创建: createBy 为当前登录用户. 
	 * 
	 * 2. 关于分页:
	 * 1). 前端页面传入: pageNo 和 pageSize(可选). 
	 * 2). 服务端查询: totalElements 和 content. 
	 * 3). 注意: 一定要先查询 totalElements, 然后来校验 pageNo 的合法性, 然后再查询 content. 
	 * 
	 * 3. 编写 Page 类: 把分页信息都封装到 Page 类中. 
	 * 4. myBatis 分页: 因为 mybatis 的底层是一个 Mapper 接口, 则分页逻辑需要上移到 S而vice 层.
	 */
	@RequestMapping(value="/list")
	public String list(@RequestParam(value="pageNo", required=false) String pageNoStr,
			HttpSession session,
			Map<String, Object> map){
		User user = (User) session.getAttribute("user");
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {}
		Page<SalesChance> page = salesChanceService.getPage(pageNo, user);
		map.put("page2", page);
		
		return "chance/list";
	}
	
}
