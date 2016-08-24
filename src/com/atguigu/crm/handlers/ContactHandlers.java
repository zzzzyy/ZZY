package com.atguigu.crm.handlers;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.atguigu.crm.entity.Contact;
import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.services.ContactServices;
import com.atguigu.crm.services.CustomerServices;
import com.sun.org.glassfish.external.probe.provider.annotations.ProbeParam;
@RequestMapping("/contact")
@Controller
public class ContactHandlers {
	@Autowired
	public ContactServices contactServices;
	@Autowired
	private CustomerServices customerServices;
	
	@RequestMapping("/delete/{customerid}")
	public String  delete(@RequestParam(value="id",required=false) int contactId,@PathVariable("customerid") long customerid,RedirectAttributes attributes){
		Set<Contact> contacts=customerServices.getContacts(customerid);
		int size = contacts.size();
		if(size==1){
			attributes.addFlashAttribute("message", "删除失败,只有一个联系人!");
		}else{
			contactServices.delete(contactId,customerid);
			attributes.addFlashAttribute("message", "删除成功!");
		}
		
		
		
		
		
		return "redirect:/contact/list/"+customerid;
	}	
	
	
	
	
	@RequestMapping("/create/updateSave")
	public String updateSave (Contact contact,Map<String, Object> map,RedirectAttributes attributes){
		Long id = contact.getId();
		long Customerid=contact.getCustomer().getId();
		if(id==null){
			contactServices.save(contact);
			attributes.addFlashAttribute("message", "保存成功!");
		}else {
			contactServices.update(contact);
			attributes.addFlashAttribute("message", "更新成功!");
		}
		
		return "redirect:/contact/list/"+Customerid;
	
	}	
	@RequestMapping("/create/{customerid}")
	public String  compile(@RequestParam(value="id",required=false) int contactId,@PathVariable("customerid") long customerid,Map<String, Object> map){
		
		Contact contact = new Contact();
		if(contactId==0){
			Customer customer = new Customer();
			customer.setId(customerid);
			contact.setCustomer(customer);
		}else{
			contact=contactServices.getContact(contactId);
		}
		
		map.put("contact", contact);
		return "contact/input";
	
	}	
	
	
	


	
	@RequestMapping("/list/{id}")
	public String toList(@PathVariable("id") long id,Map<String, Object> map){
		Customer customer = customerServices.getCustomer(id);
		
		Set<Contact> contacts=customerServices.getContacts(id);
		customer.setContacts(contacts);
		map.put("customer", customer);
		map.put("contacts",contacts);							
		return "contact/list";
	
	}
}
