package com.demo.service.simple.impl;

import javax.xml.ws.WebServiceRefs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.mapper.simple.Testtablemapper;
import com.demo.model.simple.Testtable;
import com.demo.service.simple.Testservice;
@Service("testservice")
public class Testserviceimpl implements Testservice {
   @Autowired
	Testtablemapper testtablemapper;
	public Testtable selectbyprimay(String id) {
		Testtable testtable = testtablemapper.selectbyid(id);
		return testtable;
	}

}
