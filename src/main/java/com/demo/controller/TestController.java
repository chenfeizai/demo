package com.demo.controller;

import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.model.simple.Testtable;
import com.demo.service.simple.Testservice;

@Controller
public class TestController {
       @Autowired
       Testservice Testservice;
	    @RequestMapping(value= "/Test") //映射页面的action,意思就是展示的URL连接
	    public String querytt(ModelMap map){
    	   map.put("name","测试员");
    	 return  "Test"; //内容返回到的页面  
    	   
       }
	    @RequestMapping(value= "/requ", method = RequestMethod.POST) //映射页面的action,意思就是展示的URL连接
	    public String requestt(ModelMap map,@RequestParam(name="username") String name){
    	   map.put("username",name);
    	 return  "view/Test2"; //内容返回到的页面 
    	   
       }
	    @RequestMapping(value= "/selbyid", method = RequestMethod.POST) //映射页面的action,意思就是展示的URL连接
	    public String querytest(ModelMap map,@RequestParam(name="id") String id){
    	   Testtable testtable = Testservice.selectbyprimay(id);
	    	map.put("Testtable",testtable);
    	 return  "view/Test2"; //内容返回到的页面 
    	   
       }
}
