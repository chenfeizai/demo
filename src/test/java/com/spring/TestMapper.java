package com.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.mapper.simple.LoanOrderMapper;
import com.demo.model.LoanOrder;

public class TestMapper {
	@Autowired
	private static	LoanOrderMapper loanOrderMapper;
	public static void main(String[] args) {
		
		
		List<LoanOrder> loanOrder = loanOrderMapper.findprimaryId("1811081124197980617");
		System.out.println(loanOrder);
		
		
	}

}
