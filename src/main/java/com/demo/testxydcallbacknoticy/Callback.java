package com.demo.testxydcallbacknoticy;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class Callback {
	  
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	 System.out.println("开始进行小雨点模拟回调");
	 }
	@Test
	public void loan() throws Exception {
		System.out.println("进行放款通知");
		Loan_return loan = new Loan_return();
		loan.loans("1909031821508872574");
	}
	@Test
	public void collect() throws Exception {
		System.out.println("进行模拟代扣");
		collect_return collect = new collect_return();
		collect.pushcollect("1908291044356462477", "1", "SUCESS");
	}
	
	@Test
	public void debit() throws Exception {
		System.out.println("进行债转通知");
		Debt_return debt = new Debt_return();
		debt.Debt("1909171535510512787", 6);
	}

}
