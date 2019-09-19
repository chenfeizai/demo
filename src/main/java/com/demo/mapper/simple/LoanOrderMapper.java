package com.demo.mapper.simple;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.demo.mapper.BaseMapper;
import com.demo.model.LoanOrder;

@Mapper
public interface LoanOrderMapper extends BaseMapper<String, LoanOrder>{

	
	   List<LoanOrder> findprimaryId(@Param("orderId") String orderid);






}
	