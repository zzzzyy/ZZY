package com.atguigu.crm.mappers;

import org.apache.ibatis.annotations.Param;

import com.atguigu.crm.entity.Contact;

public interface ContactMapper {

	void save(Contact contact);

	void update(Contact contact);

	Contact getContact(int contactId);

	void delete(int contactId);

	void customerid(@Param("contactId")int contactId,@Param("customerid")long customerid);

}
