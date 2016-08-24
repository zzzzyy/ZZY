package com.atguigu.crm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.Contact;
import com.atguigu.crm.mappers.ContactMapper;

@Service
public class ContactServices {
	@Autowired
	public ContactMapper contactMapper;
	@Transactional
	public void save(Contact contact) {
		contactMapper.save(contact);
		
	}
	@Transactional
	public void update(Contact contact) {
		contactMapper.update(contact);
		
	}
	public Contact getContact(int contactId) {
		// TODO Auto-generated method stub
		return contactMapper.getContact(contactId);
	}
	public void delete(int contactId, long customerid) {
		contactMapper.customerid(contactId,customerid);
		contactMapper.delete(contactId);		
	}
	
}
