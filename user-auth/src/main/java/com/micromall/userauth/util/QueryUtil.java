package com.micromall.userauth.util;


import java.util.Random;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;



public class QueryUtil {
	/**
	 * url前半部分
	 */
	public static final String BASE_URL = "https://open.ucpaas.com/ol/sms/sendsms";

	/**
	 * 开发者注册后系统自动生成的账号，可在官网登录后查看
	 */
	public static final String ACCOUNT_SID = "060fcf15c7a7fd47f5f262ab78b945ff";

	/**
	 * 开发者注册后系统自动生成的TOKEN，可在官网登录后查看
	 */
	public static final String AUTH_TOKEN = "ea25dc923654ca47d1f4612767fa61bc";
	/**
	 * 开发者注册后系统自动生成的APP_ID，可在官网登录后查看
	 */
	public static final String APP_ID = "c7fd33f193d74344a56b3972973ee38b";

	
	
	/**
     * 
     * 
     * @return
     */
	public static String sendSms(String templateid, String param, String mobile, String uid) {
		
		String result = "";
		
		try {
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("sid", ACCOUNT_SID);
			jsonObject.put("token", AUTH_TOKEN);
			jsonObject.put("appid", APP_ID);
			jsonObject.put("templateid", templateid);
			jsonObject.put("param", param);
			jsonObject.put("mobile", mobile);
			jsonObject.put("uid", uid);
			
			String body = jsonObject.toJSONString();
			
			System.out.println("body = " + body);
			
			result = postJson(BASE_URL, body, null);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public static String postJson(String url, String body, String charset) {
		
		String result = null;
		
		if (null == charset) {
			charset = "UTF-8";
		}
		CloseableHttpClient httpClient = null;
		HttpPost httpPost = null;
		try {
			httpClient = HttpConnectionManager.getInstance().getHttpClient();
			httpPost = new HttpPost(url);
			
			// 设置连接超时,设置读取超时
			RequestConfig requestConfig = RequestConfig.custom()
					.setConnectTimeout(10000)	
	                .setSocketTimeout(10000)	
	                .build();
			httpPost.setConfig(requestConfig);
			
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-Type", "application/json;charset=utf-8");
			
			// 设置参数
			StringEntity se = new StringEntity(body, "UTF-8");
			httpPost.setEntity(se);
			HttpResponse response = httpClient.execute(httpPost);
			if (response != null) {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					result = EntityUtils.toString(resEntity, charset);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return result;
	}
	public static String getRandNum() {
        String randNum = new Random().nextInt(1000000)+"";
        if (randNum.length()!=6) {   //如果生成的不是6位数随机数则返回该方法继续生成
            return getRandNum();
        }
        return randNum;
    }
}
