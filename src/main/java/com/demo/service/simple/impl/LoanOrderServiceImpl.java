package com.demo.service.simple.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.mapper.simple.LoanOrderMapper;
import com.demo.model.LoanOrder;
import com.demo.service.simple.LoanOrderService;

@Service("loanOrderService")
public class LoanOrderServiceImpl implements LoanOrderService {
    
	@Autowired
	private LoanOrderMapper LoanOrderMapper;
	
	@Override
	public List<LoanOrder> findLoanOrderById(String id) {
		List<LoanOrder> order = LoanOrderMapper.findprimaryId(id);
//		order.setId(id);
//		order.setLoanMerName("飞哥");
		System.out.println(order);
		return order;
	}	
	@Override
	public LoanOrder findLoanOrderByName(String id,String name) {
		LoanOrder order = new LoanOrder();
		order.setId(id);
		order.setLoanMerName(name);
		System.out.println(order);
		return order;
	}

}
