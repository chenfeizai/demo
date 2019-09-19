package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.model.LoanOrder;
import com.demo.service.simple.LoanOrderService;

@Controller
public class DemoController {
	
	@Autowired
	private LoanOrderService loanOrderService;

	@RequestMapping(value = "/demo")
	public String demo(ModelMap map) {
		System.out.println(" go to demo jsp");

		map.put("name", "飞哥");
		return "demo";
	}

	@RequestMapping(value = "/userForm", method = RequestMethod.POST)
	public String userForm(ModelMap map, @RequestParam(name = "username") String name) {
		System.out.println(" from demo submit , username=" + name);

		map.put("name", "飞哥");
		map.put("username", name);
		return "view/demo2";
	}
	
	@RequestMapping(value = "/query", method = RequestMethod.POST)
	public String query(ModelMap map, @RequestParam String orderId) {
		System.out.println(" query , orderId=" + orderId);
		List<LoanOrder> order = loanOrderService.findLoanOrderById(orderId);
		map.put("order", order);
		return "view/demo2";
	}
	@RequestMapping(value = "/find", method = RequestMethod.POST)
	public String find(ModelMap map, @RequestParam String mername,String orderId) {
		System.out.println(" find , mername=" + mername);
		LoanOrder order = loanOrderService.findLoanOrderByName(orderId,mername);
		map.put("order", order);
		return "view/demo2";
	}

}
