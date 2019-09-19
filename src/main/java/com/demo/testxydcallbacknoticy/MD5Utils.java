package com.demo.testxydcallbacknoticy;

import java.security.MessageDigest;

/**
 * md5���ܹ�����
 */
public class MD5Utils {

	private static final String ALGORITHM_MD5 = "MD5";
	private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
			"e", "f" };

	public static String md5(String origin) {
		return encode(origin, Constant.ENCODE, ALGORITHM_MD5);
	}

	private static String encode(String origin, String charsetname, String algorithm) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance(algorithm);
			if (charsetname == null || "".equals(charsetname))
				resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
			else
				resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
		} catch (Exception exception) {
		}
		return resultString;
	}

	private static String byteArrayToHexString(byte b[]) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++)
			resultSb.append(byteToHexString(b[i]));

		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		return hexDigits[(b >> 4) & 0x0f] + hexDigits[b & 0x0f];
	}

}
