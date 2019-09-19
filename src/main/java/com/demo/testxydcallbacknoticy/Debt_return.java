package com.demo.testxydcallbacknoticy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

public class Debt_return {
	private String url = "http://10.148.181.129/hpayDFPaySupport/api/axd/callBack/loanDebtNotify"; // 债转通知地址
	private final static String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	private sql_mysql sql_mysql;
	private sql getsql;
	private Map<String, String> parameter;
    private Logger log;
	public Debt_return() {
		parameter = new HashMap<String,String>();
		sql_mysql = new sql_mysql();
		getsql = new sql();
		log=LoggerFactory.getLogger(Debt_return.class);
	}

	public void Debt(String order_id, int startnum) {

		getsql.setselLoanOrder(order_id);
		int Period = Integer.parseInt(sql_mysql.MySqlsel(getsql.getsql(), "actual_periods"));

		for (int i = startnum; i <= Period; i++) {
			getsql.setselRepayPlan(order_id, i + "");
			parameter.put("productNo", "28A");
			parameter.put("defaultInterest", "0");
			parameter.put("daysInDefault", "30");
			parameter.put("transactionDate", time);
			parameter.put("amount", sql_mysql.MySqlsel(getsql.getsql(), "amount"));
			parameter.put("installmentNumber", i + "");
			parameter.put("loanNumber", "20190820HY1566264848821SORK28A");
			parameter.put("principal", sql_mysql.MySqlsel(getsql.getsql(), "principal"));
			parameter.put("interest", sql_mysql.MySqlsel(getsql.getsql(), "interest"));
			parameter.put("orderNumber", order_id);
			parameter.put("lateFee", "0");
			parameter.put("penalty", "0");
			parameter.put("serviceCharge", "0");
			getsql.setselLoanOrder(order_id);
			parameter.put("fundingAmount", sql_mysql.MySqlsel(getsql.getsql(), "actual_amount"));
			getsql.setselRepayPlanacdate(order_id, i);
			String originalDueDate = sql_mysql.MySqlsel(getsql.getsql(), "ac_date").substring(0, 10);
//			String originalDueDate = sql_mysql.MySqlsel(
//					"select IF(installment_num=1,DATE_SUB(now(),interval 2 MONTH) ,IF(installment_num=2,DATE_SUB(now(),interval 1 MONTH),ac_date)) as ac_date from t_dpay_loan_repay_plan where order_id ='"
//							+ order_id + "' and installment_num = '" + i + "'",
//					"ac_date").substring(0, 10);
			parameter.put("originalDueDate", originalDueDate);
			parameter.put("transactionType", "payForMore");
			try {
				parameter.put("sign", XYDSignByAesUtil.sign(parameter));
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(HttpClientUtil.sendHttpPostJson(url, JSONObject.toJSONString(parameter)));
			log.info("第"+i+"期还款计划债转成功");
		}

		int count = sql_mysql.MySqlupt("update t_dpay_loan_order set status = '150' where id = '" + order_id + "'");
		if (count == 1) {
			
			System.out.println("loan_order ====> status=150，success!");
		} else {
			System.out.println("loan_order ====> status=150，failed!");
		}
		;

	}

	public static void main(String[] args) throws Exception {

		String order_id = "1909021414422922520"; // 订单号
		// String ip = "10.148.181.129";// 环境
		String url = "http://10.148.181.129/hpayDFPaySupport/api/axd/callBack/loanDebtNotify"; // 债转通知地址
		String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		int startnum = 1; // 起始期数

		sql_mysql sql_mysql = new sql_mysql();
		sql sql = new sql();
		// 获取订单期数
		sql.setselLoanOrder(order_id);
		sql_mysql.MySqlsel(sql.getsql(), "actual_periods");
		int Period = Integer.parseInt(sql_mysql.MySqlsel());

		for (int i = startnum; i <= Period; i++) {

			JSONObject params = new JSONObject();
			Map<String, String> parameter = new HashMap<>();

			params.put("productNo", "28A");
			parameter.put("productNo", "28A");

			params.put("defaultInterest", Double.valueOf("0.00"));
			parameter.put("defaultInterest", "0");

			params.put("daysInDefault", Double.valueOf("30"));
			parameter.put("daysInDefault", "30");

			params.put("transactionDate", time);
			parameter.put("transactionDate", time);

			sql.setselRepayPlan(order_id, i + "");
			sql_mysql.MySqlsel(sql.getsql(), "amount");
			putzero putzero = new putzero();
			String amount = putzero.setputzero(sql_mysql.MySqlsel());
			params.put("amount", amount);
			parameter.put("amount", amount);

			params.put("installmentNumber", i);
			parameter.put("installmentNumber", i + "");

			params.put("loanNumber", "20190820HY1566264848821SORK28A");
			parameter.put("loanNumber", "20190820HY1566264848821SORK28A");

			sql.setselRepayPlan(order_id, i + "");
			sql_mysql.MySqlsel(sql.getsql(), "principal");
			putzero putzero1 = new putzero();
			String principal = putzero1.setputzero(sql_mysql.MySqlsel());
			params.put("principal", principal);
			parameter.put("principal", principal);

			sql.setselRepayPlan(order_id, i + "");
			sql_mysql.MySqlsel(sql.getsql(), "interest");
			putzero putzero2 = new putzero();
			String interest = putzero2.setputzero(sql_mysql.MySqlsel());
			params.put("interest", interest);
			parameter.put("interest", interest);

			params.put("orderNumber", order_id);
			parameter.put("orderNumber", order_id);

			// 第一笔减2个月，第二笔减2个月，其他不变

			String originalDueDate = sql_mysql.MySqlsel(
					"select IF(installment_num=1,DATE_SUB(now(),interval 2 MONTH) ,IF(installment_num=2,DATE_SUB(now(),interval 1 MONTH),ac_date)) as ac_date from t_dpay_loan_repay_plan where order_id ='"
							+ order_id + "' and installment_num = '" + i + "'",
					"ac_date").substring(0, 10);
			params.put("originalDueDate", originalDueDate);
			parameter.put("originalDueDate", originalDueDate);

			params.put("lateFee", Double.valueOf("0.00"));
			parameter.put("lateFee", "0");

			params.put("penalty", Double.valueOf("0.00"));
			parameter.put("penalty", "0");

			params.put("serviceCharge", Double.valueOf("0.00"));
			parameter.put("serviceCharge", "0");

			sql.setselLoanOrder(order_id);
			sql_mysql.MySqlsel(sql.getsql(), "actual_amount");
			String fundingAmount = sql_mysql.MySqlsel();
			params.put("fundingAmount", fundingAmount);
			parameter.put("fundingAmount", fundingAmount + "");

			String sign = XYDSignByAesUtil.sign(parameter);
			params.put("sign", sign);
			System.out.println(HttpClientUtil.sendHttpPostJson(url, params.toJSONString()));

		}

		int count = sql_mysql.MySqlupt("update t_dpay_loan_order set status = '150' where id = '" + order_id + "'");
		if (count == 1) {
			System.out.println("loan_order ====> status=150，success!");
		} else {
			System.out.println("loan_order ====> status=150，failed!");
		}
		;
	}
}
