<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.mysql.cj.jdbc.*,java.sql.*,com.demo.Testxydcallbacknoticy.*" %>

<%  
    String orderid=request.getParameter("order_id");
    String debitnum=request.getParameter("debit_num");
    String debitstatus=request.getParameter("debitstatus");
    String startnum=request.getParameter("start_num");  
/*     if (orderid!="" && debitnum!="" && debitstatus!="" && startnum==""){
        collect_return collect = new collect_return();
	    collect.pushcollect(orderid, debitnum, debitstatus);
	    out.print("恭喜你小样，代扣成功");}
    if(orderid!="" && debitnum=="" && debitstatus=="" && startnum!=""){
    	Debt_return debt = new Debt_return();
		debt.Debt(orderid, Integer.parseInt(startnum));
        out.print("恭喜你小样，债转成功");
    }
    if(orderid!="" && debitnum=="" && debitstatus=="" && startnum==""){
    	Loan_return loan = new Loan_return();
        loan.loans(orderid);
        out.print("恭喜你小样，放款成功");
        } */
    
%>     		


