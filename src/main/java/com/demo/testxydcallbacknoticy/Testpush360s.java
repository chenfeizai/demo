package com.demo.testxydcallbacknoticy;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

public class Testpush360s {
	private final static String sigh="MDk2MTY2MjA5YTIzNGQ1NTIzYThhZTQyOTgwMDA3YWUwYzY3Y2IyNQ";
    private final static String merchant_id="qh360_api";
    private final static String timestamp="1563963298";
	Map<String, String> parameter = new HashMap<String, String>();
    private String orderno="1908211753188772361";
    
    @Before
    public void Before(){
    	System.out.println("开始推送状态给360");
    	
    }
	@Test
	public void testpushorderstatus() {
		String requestUrl = "http://10.148.181.129/hpayDFPaySupport/api/qihu/push/collPushRiskOrderStatus";
		String js= "{\"md5\":\"831993c61bcb5b68c74971b22a7a90b3\",\"order_no\":\""+orderno+"\"}";
	    parameter.put("biz_data", js);
	    parameter.put("merchant_id", merchant_id);
	    parameter.put("timestamp", timestamp);
	    parameter.put("sigh", sigh);
	    String result = HttpClientUtil.sendHttpPostJson(requestUrl, JSONObject.toJSONString(parameter));
	    System.out.println(result);
	}
	@Test
	public void testpushRiskApprova() {
		String requestUrl = "http://10.148.181.129/hpayDFPaySupport/api/qihu/push/pushRiskApprova";
		String js= "{\"md5\":\"831993c61bcb5b68c74971b22a7a90b3\",\"order_no\":\""+orderno+"\"}";
	    parameter.put("biz_data", js);
	    parameter.put("merchant_id", merchant_id);
	    parameter.put("timestamp", timestamp);
	    parameter.put("sigh", sigh);
	    String result = HttpClientUtil.sendHttpPostJson(requestUrl, JSONObject.toJSONString(parameter));
	    System.out.println(result);
	}

}
