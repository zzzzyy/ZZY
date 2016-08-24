package com.atguigu.crm.handlers;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.atguigu.crm.entity.Product;
import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.services.ProductSerice;
@RequestMapping("/product")
@Controller
public class ProductHandler {
	@Autowired
	private ProductSerice productSerice;
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public String update(Product product, RedirectAttributes attributes){
		productSerice.update(product);
		attributes.addFlashAttribute("message", "操作成功!");
		return "redirect:/product/list";
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public String input(@PathVariable("id") Long id, Map<String, Object> map){
		map.put("product", productSerice.get(id));
		return "product/input";
	}
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id,Map<String, Object> map,RedirectAttributes attributes){
		productSerice.delete(id);
		
		attributes.addFlashAttribute("message", "操作成功!");
		return "redirect:/product/list";
	}
	@RequestMapping(value="/", method=RequestMethod.POST)
	public String save(Product product,Map<String, Object> map,RedirectAttributes attributes){
		productSerice.save(product);
		
		attributes.addFlashAttribute("message", "操作成功!");
		return "redirect:/product/list";
	}
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String input(Map<String, Object> map){
				Product product = new Product();
		
		map.put("product", product);
		return "product/input";
	
	}
	@RequestMapping("/list")
	public String list(@RequestParam(value="pageNo", required=false) String pageNoStr,
			Map<String, Object> map){
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {}
		Page<Product> page = productSerice.getPage(pageNo);
		map.put("page", page);
		
		return "product/list";	
	}
}
