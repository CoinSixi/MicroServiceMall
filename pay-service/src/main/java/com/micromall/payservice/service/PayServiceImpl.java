package com.micromall.payservice.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.*;
import com.alipay.api.request.AlipayTradeCloseRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.micromall.apiservice.common.ServerResponse;
import com.micromall.apiservice.entities.Order;
import com.micromall.apiservice.entities.Product;
import com.micromall.apiservice.entities.Shipping;

@Service("PayService")
public class PayServiceImpl implements PayService {

	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public ServerResponse<Order> aliPay(int productId, int userId, int count, int shippingId) {
		// TODO Auto-generated method stub
		Product product = restTemplate.getForObject("http://localhost:8085/product/{1}", Product.class, productId);
		Long timestamp = System.currentTimeMillis();
		Order order = new Order();
		order.setOrderNo(timestamp.toString());
		order.setSellerId(product.getUserId());
		order.setBuyerId(userId);
		order.setProductId(productId);
		order.setShippingId(shippingId);
		order.setCount(count);
		order.setPayment(new BigDecimal(1));
		order.setPaymentType(1);
		order.setStatus(1);
		
		//获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
		
		//设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(AlipayConfig.return_url);
		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
		
		//商户订单号，商户网站订单系统中唯一订单号，必填
		String out_trade_no = new String(Long.toString(timestamp));
		//付款金额，必填
		BigDecimal countBD = new BigDecimal(count);
		String total_amount = new String(product.getPrice().multiply(countBD).toString());
		//订单名称，必填
		String subject = new String(product.getSubject());
		//商品描述，可空
		String body = new String(product.getDetail());
		
		alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
				+ "\"total_amount\":\""+ total_amount +"\"," 
				+ "\"subject\":\""+ subject +"\"," 
				+ "\"body\":\""+ body +"\"," 
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		
		//若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
		//alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
		//		+ "\"total_amount\":\""+ total_amount +"\"," 
		//		+ "\"subject\":\""+ subject +"\"," 
		//		+ "\"body\":\""+ body +"\"," 
		//		+ "\"timeout_express\":\"10m\"," 
		//		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		//请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节
		
		//请求
		String result=null;
		try {
			//result = alipayClient.execute(alipayRequest).getBody();
			result = alipayClient.pageExecute(alipayRequest).getBody();
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ServerResponse.createBySuccessMessage("支付失败！");
		}
		//输出
		ServerResponse<String> response2 = restTemplate.postForObject("http://localhost:8087/order/insert", order, ServerResponse.class);
		
		return ServerResponse.createBySuccessMessage(result);
	}

	
	@Override
	public ServerResponse<Order> notify(String orderNo, String tradeNo) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap();
		map.put("orderNo", orderNo);
		map.put("tradeNo", tradeNo);
		ServerResponse<Order> response = restTemplate.getForObject("http://localhost:8087/order/pay/{orderNo}/{tradeNo}", ServerResponse.class, map);
		return response;
	}

	/*@Override
	public ServerResponse<Order> closePay(int orderId) {
		// TODO Auto-generated method stub
		//获得初始化的AlipayClient
		Order order = restTemplate.getForObject("http://localhost:8087/order/{1}", Order.class, orderId);
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
		
		//设置请求参数
		AlipayTradeCloseRequest alipayRequest = new AlipayTradeCloseRequest();
		//商户订单号，商户网站订单系统中唯一订单号
		String out_trade_no = new String(order.getOrderNo());
		//支付宝交易号
		String trade_no = new String(order.getTradeNo());
		//请二选一设置
		
		alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," +"\"trade_no\":\""+ trade_no +"\"}");
		String result = null;
		//请求
		try {
			result = alipayClient.execute(alipayRequest).getBody();
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ServerResponse.createBySuccessMessage("交易关闭失败！");
		}
		ServerResponse<Order> response = restTemplate.getForObject("http://localhost:8087/order/payClose/{1}", ServerResponse.class, order.getId());
		return ServerResponse.createBySuccessMessage("交易关闭！");
	}*/


}
