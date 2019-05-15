package com.micromall.payservice.service;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016092800617415";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCBHafX4D2xmwcspJ6cBf+D9Dswgo7Z96Yq3i6LNj4ap9w6g3Qu2PutYemO+OaySMbskBkZ2BQkg0+1OsL7Ja/XBDoPPqrZaouAWvBm70o5wiPCwA67AklX9k4HrQxLLhLcj25yveiTEbASon/iv4e3tvNta1OHP6sBCzU0ZTmcY+coGUOwCeVZQZ2E3dQm+YPEF+HPdPew3PUxc0FyCyOV0au7foMN/LcvVn7wh8moLjqF0moE0LFX8QMIPfz8VsjmEC0XaVOdqOB1eEkq/h0AXV4wFjAe35yUZTg4ZTEn4WgYIDEyIH8DO+/jXkz8mWvByTQHaDbxSD/OGMOEXq1pAgMBAAECggEAGmVLqPBo7GQ7JT9KRa9cO0e0yggG2DyutdjSPxeJvr1OYo/5MpKdcSaxLs9nqk8Ci94xo3zvMT+/DdfDgMCFoDl2/SLcnrIPbuAogYKt8/zifq/w0v52rArojN0txDjZFkokVETKxTY6frTF3D83qbE3NslYPDAh74axC9Md4gCmYuu952+zRfugjaMbkL9Bwn32XgIo0bsSO2TXgH+UYj2zvaseW2t7/mjyU8LH/EsImADJwlssmyEl/AsI0a1wOnUu15vWguhcjLyzxUm3WmvL4RbZLCEvVRLiXHIoho670rq3EmVcrMvtRB81TUAjcmQIuxkOkqADUuy7YmLq2QKBgQDiUbCmzb/zjFO0SCXss0apeqNZfjNljbTpRK9OC1bcRljD04JJ6oYsEIry5r3ba+kHEJPSZ8a0EJuBwLpJZhDU40V59YtWLmBB/g2vMaP12oGwcIAFTrbu5A0wjcr/9rNalpktlhBByk8oGdtwaDHGVnm/jNtfMmPWuX4GpLe4QwKBgQCSDISliUGZpTUEsHogUXFOCz8+raYsmmNlCFX9dtktU/EXYX3PAZHXBEGT+S61H0i87zzM+joLhuPBw0972z7saH4EK6MZUTEqYzcUEe8Z9hFdU+wH0/k55LtmG44PPmqT1zuLQBxVLg96ZsssvsutxzA2Ue6aJlP/jUkHaLju4wKBgB+XP41JEw4v/E8h8wlDTQ3DLKIFV69JRIN7Lu057/n3Z9SN5ZXjwq7L8FlPlBjYy0JNMhUgnz/rQYmvGVG9iYPt5XjMqZJyDsWnbMmTqEtdINU8HuKexjI6awzn/foUuZxfgSo31DUaxEnYsiTSpoAE+iGey9BR/FWwtsK+o1Z5AoGARSAtrFgBt6ItLPIGa8OAeGr0XUs9asEMKGbApH4bHswrQWDAdjkM+RMnosz/IXUULXCie3Y4APqwVCaIOVw1NRnkAz9QLAx009Yv3Iz8eXIxF9Y/0hivToqHdDW4kFh/g0gNA8StZFevtcRvh9cqei7cD+nJoCZh/YO2vA4B8fcCgYBsJy3AuqI5pRlRN/vaXPRiWJUvrobnDPALBaHJgR1r73o01DO757LBim9VIhnky2KS6hqL3+dgcCMbEbx/0bT94YvM2qwDnsZMwbfBjKJZcKwtmPKCg/o5vjC5LphmRgwhBGZkJc29n13tIvc2eJKg12MmSrvNYSiM06fcPhYRYA==";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzvlHQZzToHxzqXzMCa35GdKgSHBAr3y33/JUy4q7bI3es8l82vwOQGX8AD+AMrgkl186ZgMgg/gzoo2Otd7Nc2j0kLhYQiA+p92JT9jiiZXjA4hmZY8RymyH8P8utXpdqorjhUFOcCq9Nu9KBDE46CMFojUUIUCa2JxavosHOEh2e4GSPtzETIy7IJ6cC3gyVGJ+sYF75yZUjRMGW6d+Ex4o21UAWl72UAP43w2unOSWrPxMpB9jrM91+Bn97gpSYVsrvPEfPWEEuqZGFP3dtiZd/yCYfHJu99nHuViSzoqEvVDbbepiKmifnH+XlfCNIxOKQW5xCM5B3k3A1Md38QIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	//public static String notify_url = "http://micromall.free.idcfengye.com/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";
    public static String notify_url = "http://micromall.free.idcfengye.com/pay-service/notify";
	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	//public static String return_url = "http://micromall.free.idcfengye.com/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";
    public static String return_url = "http://micromall.free.idcfengye.com/pay-service/return";
	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

