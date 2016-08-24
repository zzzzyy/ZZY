package com.atguigu.crm.handlers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.atguigu.crm.entity.Product;
import com.atguigu.crm.entity.Role;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.orm.Navigation;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.services.UserService;
import com.google.common.collect.Maps;

@RequestMapping("/user")
@Controller
public class UserHandler {
	
private static Map<Integer, String> allStatus = Maps.newHashMap();

static{
	allStatus.put(1, "有效");
	allStatus.put(0, "无效");
}
	@Autowired
	private UserService userSerice;
	
	@Autowired
	private ResourceBundleMessageSource messageSource;
	
//	@ResponseBody
//	@RequestMapping("/navigate")
//	public List<Navigation> navigate(){
//		Navigation navigations = new Navigation(Long.MAX_VALUE,"客服管理系统");
//		
//		
//		return Navigation;
//	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public String update(User user, RedirectAttributes attributes){
		userSerice.update(user);
		attributes.addFlashAttribute("message", "操作成功!");
		return "redirect:/user/list";
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public String input(@PathVariable("id") Long id, Map<String, Object> map){
		User user=userSerice.get(id);
		map.put("user", user);
		List<Role> list=userSerice.getroleList();
		map.put("allStatus", allStatus);
		map.put("list", list);
		return "user/input";
	}
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id,Map<String, Object> map,RedirectAttributes attributes){
		userSerice.delete(id);
		
		attributes.addFlashAttribute("message", "操作成功!");
		return "redirect:/user/list";
	}
	@RequestMapping(value="/", method=RequestMethod.POST)
	public String save(User user,Map<String, Object> map,RedirectAttributes attributes){
		userSerice.save(user);
		
		attributes.addFlashAttribute("message", "操作成功!");
		return "redirect:/user/list";
	}
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String input(Map<String, Object> map){
				User user = new User();
		map.put("user", user);
		List<Role> list=userSerice.getroleList();
		map.put("allStatus", allStatus);
		map.put("list", list);
		return "user/input";
	
	}
	@RequestMapping("/list")
	public String list(@RequestParam(value="pageNo", required=false) String pageNoStr,
			Map<String, Object> map){
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {}
		Page page = userSerice.getPage(pageNo);
		map.put("page", page);
		
		return "user/list";	
	}
	
	
	
	
	
	/**
	 * SpringMVC 在重定向情况下如何在页面得到 key-val
	 * 1. 在 Handler 方法中加入一个 RedirectAttributes 的类型的参数. 
	 * 2. 调用 RedirectAttributes 的 addFlashAttribute 方法加入 key-val 对
	 * 3. 在 Handler 方法中不能直接重定向到一个物理的 JSP 页面. 而是要要通过 SpringMVC 重定向到该页面.
	 * 1). 在方法中返回: return "redirect:/index";
	 * 2). SpringMVC 的配置文件: <mvc:view-controller path="/index" view-name="index"/>
	 * 
	 * SpringMVC 如何把消息放入到国际化资源文件中.
	 * 1. 在类路径下加入国际化资源文件
	 * 2. 在 SpringMVC 的配置文件中配置国际化资源文件
	 * <bean id="messageSource" class="org.springframework.context.support.ResourceBundleMessageSource">
	 * 	<!-- 配置国际化资源文件的基名 -->
	 *  <property name="basename" value="i18n"></property>
	 * </bean>
	 * 3. 在 Handler 中注入 ResourceBundleMessageSource 的实例.
	 * 4. 在 Handler 的方法中调用 ResourceBundleMessageSource 的 getMessage(code, args, locale) 方法来获取资源文件中的
	 * val. 
	 */
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@RequestParam(value="username", required=false) String name,
			@RequestParam(value="password", required=false) String password,
			HttpSession session,
			RedirectAttributes attributes,
			Locale locale){
		User user = userSerice.login(name, password);
		
		if(user == null){
			String code = "error.user.login";
			String [] args = null;
			String val = messageSource.getMessage(code, args, locale);
			attributes.addFlashAttribute("message", val);
			return "redirect:/index";
		}
		
		session.setAttribute("user", user);
		
		return "redirect:/success";
	}
}
