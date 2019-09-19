package com.demo.testxydcallbacknoticy;

import static com.demo.testxydcallbacknoticy.Constant.cooperation_privateKey;
import static com.demo.testxydcallbacknoticy.Constant.privateKey;

import java.util.Map;
import java.util.TreeMap;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;

/**
 * С���ǩ������sdk Created by liuyuan on 2018/4/27.
 */
public class XYDSignByAesUtil {

	public final static String encryptType = "AES";
	/**
	 * ���ܷ�ʽ
	 */
	public final static String aesType = "AES/CBC/PKCS5Padding";
	/**
	 * 16λ�̶�ƫ������Ĭ��ֵ�����ܸĶ���
	 */
	public final static String offset = "1234567812345678";

	private static IvParameterSpec ivParameterSpec = null;

	static {
		ivParameterSpec = new IvParameterSpec(offset.getBytes());
	}

	/**
	 * ��ȡǩ���ַ���
	 *
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public static String sign(Map<String, String> paramMap) throws Exception {
		// 1 ���ݲ�������
		String plainText = getPlainText(paramMap);
		// 2 ��������б�md5����
		String md5Str = MD5Utils.md5(plainText);

		// 3 ��ȡԭ��signֵ
		String encrypt;
		if (Constant.appId.equals("1")) {
			encrypt = encryptByAes(md5Str, privateKey);
		} else if (Constant.appId.equals("2")) {
			encrypt = encryptByAes(md5Str, cooperation_privateKey);
		} else {
			throw new Exception("����app����");
		}

		return encrypt;
	}

	/**
	 * �Բ���������ǩ �ò����б��������sign��Ϣ
	 *
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public static String verifySign(Map<String, String> paramMap) throws Exception {
		// 1 ���ݲ�������
		String plainText = getPlainText(paramMap);
		// 2 ��������б�md5����
		String md5Str = MD5Utils.md5(plainText);
		String encrypt = encryptByAes(md5Str, privateKey);
		String sign = paramMap.get("sign");
		if (StringUtils.isBlank(sign)) {
			throw new Exception("������������ǩ���ַ���");
		}

		String decrypt = decryptByAes(sign, privateKey);
		if (decrypt.equals(decryptByAes(encrypt, privateKey))) {
		}
		return decrypt;
	}
	
    public static boolean verifySign(Map<String, String> paramMap, String signKey) throws Exception {
        String sign = paramMap.get("sign");
        if (StringUtils.isBlank(sign)) {
            throw new Exception("������������ǩ���ַ���");
        }
        System.out.println("����sign��");
        System.out.println(sign);
        // 1 ���ݲ�������
        String plainText = getPlainText(paramMap);
        // 2 ��������б�md5����
        String md5Str = MD5Utils.md5(plainText);
        String decrypt = decryptByAes(sign, signKey);
        if (md5Str.equals(decrypt)) {
            return true;
        }
        return false;
    }
    

	/**
	 * ��ȡ����ɿ��ַ��� ע�⣺�����б���ܺ���info.xxx�ֶΣ���������ʱ��ֻ��ȡxxx����
	 *
	 * @param paramMap
	 * @return
	 */
	public static String getPlainText(Map<String, String> paramMap) {
		TreeMap<String, String> map = new TreeMap<>();
		for (Map.Entry<String, String> entry : paramMap.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			if (StringUtils.isBlank(key) || StringUtils.isBlank(value)) {
				continue;
			}
			if ("sign".equals(key)) {
				continue;
			}
			if (key.contains(".")) {
				key = key.split("\\.")[1];
			}
			if ("phone".equals(key)) {
				key = "mobile";
			}
			map.put(key, value);
		}
		// ����С�����ǩ�����ֶ�����
		StringBuilder sb = new StringBuilder();
		for (Map.Entry entry : map.entrySet()) {
			sb.append(entry.getKey() + "=" + entry.getValue() + "&");
		}
		sb.delete(sb.length() - 1, sb.length());
		String plaintext = sb.toString();
		return plaintext;
	}

	// aes����
	public static String encryptByAes(String src, String key) throws Exception {
		if (StringUtils.isBlank(key)) {
			return null;
		}
		// �ж�Key�Ƿ�Ϊ16λ
		if (key.length() != 16) {
			return null;
		}
		byte[] raw = key.getBytes();
		SecretKeySpec skeySpec = new SecretKeySpec(raw, encryptType);
		Cipher cipher = Cipher.getInstance(aesType);
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivParameterSpec);
		byte[] encrypted = cipher.doFinal(src.getBytes());
//		BASE64Encoder encoder = new BASE64Encoder();
		Base64 encoder = new Base64();
//		String encode = encoder.encode(encrypted);
		String encode = encoder.encodeAsString(encrypted);
		return encode;
	}

	// aes����
	public static String decryptByAes(String src, String privateKey) throws Exception {
		try {
			// �ж�Key�Ƿ���ȷ
			if (StringUtils.isBlank(privateKey)) {
				return null;
			}
			// �ж�Key�Ƿ�Ϊ16λ
			if (privateKey.length() != 16) {
				return null;
			}
			byte[] key = privateKey.getBytes(Constant.ENCODE);
			SecretKeySpec skeySpec = new SecretKeySpec(key, encryptType);
			Cipher cipher = Cipher.getInstance(aesType);
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivParameterSpec);
//			BASE64Decoder decoder = new BASE64Decoder();
			Base64 decoder = new Base64();
//			byte[] base64 = decoder.decodeBuffer(src);
			byte[] base64 = decoder.decode(src);
			try {
				byte[] original = cipher.doFinal(base64);
				return new String(original);
			} catch (Exception e) {
				return null;
			}
		} catch (Exception ex) {
			return null;
		}
	}

}
