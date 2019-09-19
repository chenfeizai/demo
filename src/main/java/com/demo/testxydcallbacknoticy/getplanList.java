package com.demo.testxydcallbacknoticy;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.alibaba.fastjson.JSONObject;

public class getplanList {

	public ArrayList<JSONObject> setplanList(String order_id) throws ParseException {

		sql_mysql sql_mysql = new sql_mysql();
		sql sql = new sql();

		// ��ȡ����
		sql.setselLoanOrder(order_id);
		sql_mysql.MySqlsel(sql.getsql(), "actual_periods");
		int Period = Integer.parseInt(sql_mysql.MySqlsel());

		String rawAddTime = System.currentTimeMillis() + "";

		ArrayList<JSONObject> plst = new ArrayList<JSONObject>();
		for (int i = 1; i <= Period; i++) {

			JSONObject mkplanlist = new JSONObject();

			mkplanlist.put("rawAddTime", Long.valueOf(rawAddTime));

			mkplanlist.put("installmentNumber", i);

			sql.setselRepayPlan(order_id, i + "");
			sql_mysql.MySqlsel(sql.getsql(), "amount");
			putzero putzero = new putzero();
			String amount = putzero.setputzero(sql_mysql.MySqlsel());
			mkplanlist.put("amount", Double.valueOf(amount));

			sql.setselRepayPlan(order_id, i + "");
			sql_mysql.MySqlsel(sql.getsql(), "hanyin_profit");
			putzero putzero1 = new putzero();
			String hanyin_profit = putzero1.setputzero(sql_mysql.MySqlsel());
			mkplanlist.put("hanyinProfit", Double.valueOf(hanyin_profit));

			sql.setselRepayPlan(order_id, i + "");
			sql_mysql.MySqlsel(sql.getsql(), "principal");
			putzero putzero2 = new putzero();
			String principal = putzero2.setputzero(sql_mysql.MySqlsel());
			mkplanlist.put("principal", Double.valueOf(principal));

			sql.setselRepayPlan(order_id, i + "");
			sql_mysql.MySqlsel(sql.getsql(), "status");
			String status = sql_mysql.MySqlsel();
			mkplanlist.put("status", Double.valueOf(status));

			sql.setselRepayPlan(order_id, i + "");
			sql_mysql.MySqlsel(sql.getsql(), "interest");
			putzero putzero4 = new putzero();
			String interest = putzero4.setputzero(sql_mysql.MySqlsel());
			mkplanlist.put("interest", Double.valueOf(interest));

			sql.setselRepayPlan(order_id, i + "");
			sql_mysql.MySqlsel(sql.getsql(), "installment_not_paid");
			putzero putzero5 = new putzero();
			String installmentNotPaid = putzero5.setputzero(sql_mysql.MySqlsel());
			mkplanlist.put("installmentNotPaid", Double.valueOf(installmentNotPaid));

			sql.setselRepayPlan(order_id, i + "");
			sql_mysql.MySqlsel(
					"select date_add((date_add(now(), interval 1*installment_num MONTH)), INTERVAL 1 day) as ac_date from t_dpay_loan_repay_plan where order_id ='"
							+ order_id + "' and installment_num = '" + i + "'",
					"ac_date");
			String originalDueDate = sql_mysql.MySqlsel().substring(0, 10);
			mkplanlist.put("originalDueDate", originalDueDate);
			
			sql.setselRepayPlan(order_id, i + "");
			sql_mysql.MySqlsel(sql.getsql(), "installment_late_fee");
			mkplanlist.put("installmentLateFee", Double.valueOf("0"));

			// String key = mkplanlist + "";
			plst.add(mkplanlist);
		}

		return plst;
	}

	public static Date addMonths(Date curDateType, int expTime) {
		Calendar expiration = Calendar.getInstance();
		expiration.setTime(curDateType);
		expiration.add(Calendar.MONTH, expTime);

		return expiration.getTime();
	}

}
