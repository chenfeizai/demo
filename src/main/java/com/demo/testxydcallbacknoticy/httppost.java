package com.demo.testxydcallbacknoticy;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class httppost {

	/**
	 * ����HttpPost����
	 * 
	 * @param strURL
	 *            �����ַ
	 * @param params
	 *            json�ַ���,����: "{ \"id\":\"12345\" }" ;���������������˫����<br/>
	 * @return �ɹ�:����json�ַ���<br/>
	 */
	public static String post(String strURL, String params) {

		BufferedReader reader = null;
		try {
			URL url = new URL(strURL);// ��������
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestMethod("POST"); // ��������ʽ
			// connection.setRequestProperty("Accept", "application/json"); //
			// ���ý������ݵĸ�ʽ
			connection.setRequestProperty("Content-Type", "application/json"); // ���÷������ݵĸ�ʽ
			connection.connect();
			// һ��Ҫ��BufferedReader ��������Ӧ�� ʹ���ֽ���������Ӧ�ķ����ǽ��ղ������ݵ�
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8����
			out.append(params);
			out.flush();
			out.close();
			// ��ȡ��Ӧ
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			String line;
			String res = "";
			while ((line = reader.readLine()) != null) {
				res += line;
			}
			reader.close();

			// ���һ��Ҫʹ�����·�ʽ������Ӧ���ݣ� ����Ӧ����Ϊ:
			// response.getWriter().print(StringUtils.join("{\"errCode\":\"1\",\"errMsg\":\"",
			// message, "\"}")); ������
			// int length = (int) connection.getContentLength();// ��ȡ����
			// if (length != -1) {
			// byte[] data = new byte[length];
			// byte[] temp = new byte[512];
			// int readLen = 0;
			// int destPos = 0;
			// while ((readLen = is.read(temp)) > 0) {
			// System.arraycopy(temp, 0, data, destPos, readLen);
			// destPos += readLen;
			// }
			// String result = new String(data, "UTF-8"); // utf-8����
			// System.out.println(result);
			// return result;
			// }

			return res;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "error"; // �Զ��������Ϣ
	}

}