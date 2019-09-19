package com.demo.testxydcallbacknoticy;

import com.alibaba.fastjson.JSONObject;


public class getplanInfo {
	JSONObject setplanInfo = new JSONObject();

	public JSONObject setplanInfo(String order_id) {

		sql_mysql sql_mysql = new sql_mysql();
		sql sql = new sql();
		putzero putzero = new putzero();
		// planInfo

		setplanInfo.put("totalLateFee", Double.valueOf("0.00"));

	
		sql.setselLoanOrder(order_id);
		sql_mysql.MySqlsel(sql.getsql(), "actual_amount");
		String totalPrincipal = putzero.setputzero(sql_mysql.MySqlsel(sql.getsql(), "actual_amount"));
		setplanInfo.put("totalPrincipal", totalPrincipal);

		setplanInfo.put("orderNumber", order_id);

		setplanInfo.put("aprInYear", "23.99");

		putzero putzero1 = new putzero();
		sql.setselSUMRepayPlan(order_id, "principal");
		sql_mysql.MySqlsel(sql.getsql(), "sum");
		String totalAmount = putzero1.setputzero(sql_mysql.MySqlsel());
		setplanInfo.put("totalAmount", totalAmount);

		putzero putzero2 = new putzero();
		sql.setselSUMRepayPlan(order_id, "interest");
		sql_mysql.MySqlsel(sql.getsql(), "sum");
		String totalInterest = putzero2.setputzero(sql_mysql.MySqlsel());
		setplanInfo.put("totalInterest", totalInterest);

		sql.setselLoanOrder(order_id);
		sql_mysql.MySqlsel(sql.getsql(), "actual_periods");
		String totalPeriod = sql_mysql.MySqlsel();
		setplanInfo.put("totalPeriod", totalPeriod);

		putzero putzero3 = new putzero();
		sql.setselSUMRepayPlan(order_id, "hanyin_profit");
		sql_mysql.MySqlsel(sql.getsql(), "sum");
		String hanyinInterestProfit = putzero3.setputzero(sql_mysql.MySqlsel());
		setplanInfo.put("hanyinInterestProfit", hanyinInterestProfit);
       return setplanInfo;
	}

	public JSONObject getplanInfo() {
		return setplanInfo;
	}
}
