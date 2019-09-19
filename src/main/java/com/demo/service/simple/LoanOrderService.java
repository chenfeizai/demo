package com.demo.service.simple;

import java.util.List;

import com.demo.mapper.simple.LoanOrderMapper;
import com.demo.model.LoanOrder;

public interface LoanOrderService {
	
	public List<LoanOrder> findLoanOrderById(String id);
	public LoanOrder findLoanOrderByName(String id,String name);
}
