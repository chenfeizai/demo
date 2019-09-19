package com.demo.testxydcallbacknoticy;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;



/**
 *
 * @author H__D
 * @date 2016��10��19�� ����11:27:25
 *
 */
@SuppressWarnings({ "rawtypes", "deprecation" })
public class HttpClientUtil {

	// utf-8�ַ�����
	public static final String CHARSET_UTF_8 = "utf-8";

	// HTTP�������͡�
	public static final String CONTENT_TYPE_TEXT_HTML = "text/xml";

	// HTTP�������͡��൱��form������ʽ���ύ����
	public static final String CONTENT_TYPE_FORM_URL = "application/x-www-form-urlencoded";

	// HTTP�������͡��൱��form������ʽ���ύ����
	public static final String CONTENT_TYPE_JSON_URL = "application/json;charset=utf-8";

	// ���ӹ�����
	private static PoolingHttpClientConnectionManager pool;

	// ��������
	private static RequestConfig requestConfig;

	public static String sendHttpPostJson(String httpUrl, String paramsJson) {
		HttpPost httpPost = new HttpPost(httpUrl);// ����httpPost
		try {
			// ���ò���
			if (paramsJson != null && paramsJson.trim().length() > 0) {
				StringEntity stringEntity = new StringEntity(paramsJson, "UTF-8");
				stringEntity.setContentType(CONTENT_TYPE_JSON_URL);
				httpPost.setEntity(stringEntity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sendHttpPost(httpPost);
	}

	/**
	 * ����Post����
	 *
	 * @param httpPost
	 * @return
	 */
	private static String sendHttpPost(HttpPost httpPost) {

		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		// ��Ӧ����
		String responseContent = null;
		try {
			// ����Ĭ�ϵ�httpClientʵ��.
			httpClient = getHttpClient();
			// ����������Ϣ
			httpPost.setConfig(requestConfig);
			// ִ������
			response = httpClient.execute(httpPost);
			// �õ���Ӧʵ��
			HttpEntity entity = response.getEntity();

			// ���Ի����Ӧͷ
			// Header[] headers = response.getHeaders(HttpHeaders.CONTENT_TYPE);
			// for (Header header : headers) {
			// System.out.println(header.getName());
			// }

			// �õ���Ӧ����
			// System.out.println(ContentType.getOrDefault(response.getEntity()).getMimeType());

			// �ж���Ӧ״̬
			if (response.getStatusLine().getStatusCode() >= 300) {
				throw new Exception(
						"HTTP Request is not success, Response code is " + response.getStatusLine().getStatusCode());
			}

			if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
				responseContent = EntityUtils.toString(entity, CHARSET_UTF_8);
				EntityUtils.consume(entity);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
	
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return responseContent;
	}

	public static CloseableHttpClient getHttpClient() {

		CloseableHttpClient httpClient = HttpClients.custom()
				// �������ӳع���
				.setConnectionManager(pool)
				// ������������
				.setDefaultRequestConfig(requestConfig)
				// �������Դ���
				.setRetryHandler(new DefaultHttpRequestRetryHandler(0, false)).build();

		return httpClient;
	}

}
