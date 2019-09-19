package com.demo.testxydcallbacknoticy;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;


public class Loan_return {
	private String url = "http://10.148.181.129/hpayDFPaySupport/api/axd/callBack/notify";
	private final static String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	private Map<String, String> parameter;
	private JSONObject json;
	private JSONObject planInfo;
	private getplanInfo getplanInfo;
	private getplanList getplanList;
	private ArrayList<JSONObject> planList;
    private Logger log;
	public Loan_return() {
		parameter = new HashMap<String, String>();
		json = new JSONObject();
		getplanInfo = new getplanInfo();
		getplanList = new getplanList();
		log=LoggerFactory.getLogger(Loan_return.class);
	}

	public void loans(String order_id) {
		planInfo = getplanInfo.setplanInfo(order_id);
		try {
			planList = getplanList.setplanList(order_id);

			json.put("planInfo", planInfo);
			json.put("planList", planList);

//			JSONObject params = new JSONObject();

			// params.put("loanNumber", "20190820HY1566264848821SORK28A");
			// params.put("time", time);
			// params.put("status", "LOAN_SUCCESS");
			// params.put("orderNumber", order_id);
			// String sign = XYDSignByAesUtil.sign(parameter);
			// params.put("sign", XYDSignByAesUtil.sign(parameter));
			// params.put("repayPlan", json.toJSONString());

			parameter.put("loanNumber", "20190820HY1566264848821SORK28A");
			parameter.put("time", time);
			parameter.put("status", "LOAN_SUCCESS");
			parameter.put("orderNumber", order_id);
			parameter.put("sign", XYDSignByAesUtil.sign(parameter));
			parameter.put("repayPlan", json.toJSONString());
			System.out.println(parameter);
			String result=HttpClientUtil.sendHttpPostJson(url, JSONObject.toJSONString(parameter));
			System.out.println(result);
			if (result.equals("null")) {
				log.info("订单号"+order_id+"放款失败，密钥错误");
			}
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws Exception {
		String url = "http://10.148.181.129/hpayDFPaySupport/api/axd/callBack/notify";
		String order_id = "1908221541569802408"; // ������

		String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

		getplanInfo getplanInfo = new getplanInfo();
		JSONObject planInfo = getplanInfo.setplanInfo(order_id);

		getplanList getplanList = new getplanList();
		ArrayList<JSONObject> planList = getplanList.setplanList(order_id);
		JSONObject json = new JSONObject();
		json.put("planInfo", planInfo);
		json.put("planList", planList);

		JSONObject params = new JSONObject();

		params.put("loanNumber", "20190820HY1566264848821SORK28A");
		params.put("time", time);
		params.put("status", "LOAN_SUCCESS");
		params.put("orderNumber", order_id);

		Map<String, String> parameter = new HashMap<>();

		parameter.put("loanNumber", "20190820HY1566264848821SORK28A");
		parameter.put("time", time);
		parameter.put("status", "LOAN_SUCCESS");
		parameter.put("orderNumber", order_id);

		String sign = XYDSignByAesUtil.sign(parameter);

		params.put("repayPlan", json.toJSONString());
		params.put("sign", sign);
		System.out.println(params);
		System.out.println(HttpClientUtil.sendHttpPostJson(url, params.toJSONString()));

	}

}
