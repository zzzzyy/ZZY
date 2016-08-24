package com.atguigu.crm.handlers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
import com.atguigu.crm.entity.Storage;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.services.StorageService;
import com.sun.xml.internal.ws.api.message.Attachment;

@RequestMapping("/storage")
@Controller
public class StorageHandler {
	@Autowired
	private StorageService storageService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String update(Storage storage, HttpServletRequest request,
			RedirectAttributes attributes) {
		String add =request.getParameter("add");
		int addz=Integer.parseInt(add);
		System.out.println(addz);
		storageService.update(storage,addz);

		attributes.addFlashAttribute("message", "操作成功!");
		return "redirect:/storage/list";
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public String input(@PathVariable("id") Long id, Map<String, Object> map){
		map.put("storage", storageService.get(id));
		List<Product> list=storageService.getProductList();
		map.put("list", list);
		return "storage/input";
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") int id,RedirectAttributes attributes){
		storageService.delete(id);
		attributes.addFlashAttribute("message", "操作成功!");
		return "redirect:storage/list";
	}
	

	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String save(Storage storage,RedirectAttributes attributes) {

		storageService.save(storage);

		attributes.addFlashAttribute("message", "操作成功!");
		return "redirect:/storage/list";
	}
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String input(Map<String, Object> map){
		Storage storage = new Storage();
		List<Product> list=storageService.getProductList();
		map.put("list", list);		
		map.put("storage", storage);
		return "storage/input";
	
	}
	@RequestMapping("/list")
	public String list(
			@RequestParam(value = "pageNo", required = false) String pageNoStr,
			Map<String, Object> map) {
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {}
		
		Page page=storageService.getPage(pageNo);
		
		map.put("page", page);
		return "storage/list";
	}
}
