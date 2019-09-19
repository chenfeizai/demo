package com.demo.testxydcallbacknoticy;


import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.tomcat.util.codec.binary.Base64;


/**
 * base64������빤�� Created by liuyuan on 2017/12/12.
 */
public class Base64Util {

	private static String charset = "UTF-8";

	/**
	 * ����
	 * 	
	 * @param content
	 * @return
	 */
	public static String encodeByBase64(String content) {
		byte[] bytes = content.getBytes(Charset.forName(charset));
//		BASE64Encoder base64Encoder = new BASE64Encoder();
		Base64 base64Encoder = new Base64();
		String encodeStr = base64Encoder.encodeAsString(bytes);
		return encodeStr;
	}

	/**
	 * ����
	 * 
	 * @param content
	 * @return
	 * @throws IOException
	 */
	public static String decodeByBase64(String content) throws IOException {
		Base64 base64Decoder = new Base64();
		byte[] bytes = base64Decoder.decode(content);
		String decodeStr = new String(bytes, charset);
		return decodeStr;
	}
}
