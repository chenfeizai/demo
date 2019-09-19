package com.demo.testxydcallbacknoticy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

public class collect_return {
	private String order_id = "1908281645308352472";// 订单号
	private int debitnum = 3; // 代扣期数
	private String debitstatus = "SUCCESS+or+FAIL";
	private final static LocalDateTime nowtime = LocalDateTime.now();
	private final static String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	private String url = "http://10.148.181.129/hpayDFPaySupport/api/axd/callBack/debitLoanNotify"; // 债转通知地址
	private sql_mysql sql_mysql;
	private sql sql;
	private Map<String, String> parameter;
	private Logger log;
	private String uptrepayplan;
	private int count;

	public String geturl() {
		return this.url;
	}

	public collect_return() {
		sql_mysql = new sql_mysql();
		sql = new sql();
		parameter = new HashMap<String, String>();
		log = LoggerFactory.getLogger(collect_return.class);
	}

	public void pushcollect(String order_id, String debitnum, String debitstatus) throws Exception {
		sql.setselRepayPlan(order_id, debitnum);
		parameter.put("orderNumber", order_id);
		parameter.put("loanNumber", "20190820HY1566264848821SORK28A");
		parameter.put("installmentNumber", debitnum);
		parameter.put("debitStatus", debitstatus);
		parameter.put("debitAmount", sql_mysql.MySqlsel(sql.getsql(), "amount")/* amount */);
		parameter.put("debitCompleteTime", time);
		parameter.put("principal", sql_mysql.MySqlsel(sql.getsql(), "principal") /* principal */);
		parameter.put("interest", sql_mysql.MySqlsel(sql.getsql(), "interest")/* interest */);
		if (debitstatus.equals("SUCESS")) {
			parameter.put("message", "扣款成功");
		} else {
			parameter.put("message", "扣款失败");
		}
		parameter.put("message", "扣款失败");
		parameter.put("penalty", "0");
		parameter.put("lateFee", "0");
		parameter.put("sign", XYDSignByAesUtil.sign(parameter));
		System.out.println(HttpClientUtil.sendHttpPostJson(geturl(), JSONObject.toJSONString(parameter)));
		if (time.compareTo(sql_mysql.MySqlsel(sql.getsql(), "ac_date")) > 0) {
			uptrepayplan = "update t_dpay_loan_repay_plan t set t.paid_off_time ='" + time
					+ "',t.status ='2',t.update_time ='" + time
					+ "',t.installment_not_paid='0.0000',t.type='10' where t.order_id ='" + order_id
					+ "' and t.installment_num ='" + debitnum + "' ;";
             log.info("逾期代扣");
		} else {
			uptrepayplan = "update t_dpay_loan_repay_plan t set t.paid_off_time ='" + time
					+ "',t.status ='1',t.update_time ='" + time
					+ "',t.installment_not_paid='0.0000',t.type='10' where t.order_id ='" + order_id
					+ "' and t.installment_num ='" + debitnum + "' ;";
			log.info("正常代扣");
		}

		int count = sql_mysql.MySqlupt(uptrepayplan);

		if (count == 1) {
			System.out.println("repay_plan ====> update，success!");

			log.info("repay_plan ====> update，success!");
		} else {
			System.out.println("repay_plan ====> update，，fail!");
		}
	}

	public static void main(String[] args) throws Exception {

		String order_id = "1908281645308352472";// 订单号
		String ip = "10.148.181.129";// 环境
		String url = "http://10.148.181.129/hpayDFPaySupport/api/axd/callBack/debitLoanNotify"; // 债转通知地址
		String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		int debitnum = 3; // 代扣期数

		sql_mysql sql_mysql = new sql_mysql();
		sql sql = new sql();

		JSONObject params = new JSONObject();
		Map<String, String> parameter = new HashMap<>();

		params.put("orderNumber", order_id);
		parameter.put("orderNumber", order_id);

		params.put("loanNumber", "20190820HY1566264848821SORK28A");
		parameter.put("loanNumber", "20190820HY1566264848821SORK28A");

		params.put("installmentNumber", debitnum);
		parameter.put("installmentNumber", debitnum + "");

		params.put("debitStatus", "SUCCESS");
		parameter.put("debitStatus", "SUCCESS");

		sql.setselRepayPlan(order_id, debitnum + "");
		sql_mysql.MySqlsel(sql.getsql(), "amount");
		putzero putzero = new putzero();
		String amount = putzero.setputzero(sql_mysql.MySqlsel());
		params.put("debitAmount", amount);
		parameter.put("debitAmount", amount);

		params.put("debitCompleteTime", time);
		parameter.put("debitCompleteTime", time);

		sql.setselRepayPlan(order_id, debitnum + "");
		sql_mysql.MySqlsel(sql.getsql(), "principal");
		putzero putzero1 = new putzero();
		String principal = putzero1.setputzero(sql_mysql.MySqlsel());
		params.put("principal", principal);
		parameter.put("principal", principal);

		sql.setselRepayPlan(order_id, debitnum + "");
		sql_mysql.MySqlsel(sql.getsql(), "interest");
		putzero putzero2 = new putzero();
		String interest = putzero2.setputzero(sql_mysql.MySqlsel());
		params.put("interest", interest);
		parameter.put("interest", interest);

		params.put("message", "扣款成功");
		parameter.put("message", "扣款成功");

		params.put("penalty", "0");
		parameter.put("penalty", "0");

		params.put("lateFee", "0");
		parameter.put("lateFee", "0");

		String sign = XYDSignByAesUtil.sign(parameter);
		params.put("sign", sign);

		System.out.println(HttpClientUtil.sendHttpPostJson(url, params.toJSONString()));

		String uptrepayplan = "update t_dpay_loan_repay_plan t set t.paid_off_time ='" + time
				+ "',t.status ='1',t.update_time ='" + time
				+ "',t.installment_not_paid='0.0000',t.type='10' where t.order_id ='" + order_id
				+ "' and t.installment_num ='" + debitnum + "' ;";

		int count = sql_mysql.MySqlupt(uptrepayplan);

		if (count == 1) {
			System.out.println("repay_plan ====> update，success!");
		} else {
			System.out.println("repay_plan ====> update，，fail!");
		}
	}
}
