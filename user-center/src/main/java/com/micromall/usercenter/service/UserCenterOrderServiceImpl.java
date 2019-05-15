package com.micromall.usercenter.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.micromall.apiservice.common.ServerResponse;
import com.micromall.apiservice.entities.Order;
import com.micromall.apiservice.entities.OrderDetail;

@Service("UserCenterOrderService")
public class UserCenterOrderServiceImpl implements UserCenterOrderService {

	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public ServerResponse<List<Order>> getOrderList(int userId, int status) {
		// TODO Auto-generated method stub
		Map<String, Integer> map = new HashMap();
		map.put("userId", userId);
		map.put("status", status);
		ServerResponse<List<Order>> reponse = restTemplate.getForObject("http://localhost:8087/orderList/{userId}/{status}", ServerResponse.class, map);
	    return reponse;
	}
	@Override
	public ServerResponse<Order> getOrderById(int id) {
		// TODO Auto-generated method stub
		 Order order = restTemplate.getForObject("http://localhost:8087/order/{1}", Order.class, id);
	    return ServerResponse.createBySuccess("success", order);
	}
	@Override
	public ServerResponse<List<Order>> deleteOder(int orderId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ServerResponse<List<OrderDetail>> getOrderDetailListByBuyerId(int buyerId) {
		// TODO Auto-generated method stub
		ServerResponse<List<OrderDetail>> response = restTemplate.getForObject("http://localhost:8087/orderList/detail/buyer/{1}", ServerResponse.class, buyerId);
	    return response;
	}
	@Override
	public ServerResponse<List<OrderDetail>> getOrderDetailListByBuyerIdStatus(int buyerId, int status) {
		// TODO Auto-generated method stub
		Map<String, Integer> map = new HashMap();
		map.put("buyerId", buyerId);
		map.put("status", status);
		ServerResponse<List<OrderDetail>> response = restTemplate.getForObject("http://localhost:8087/orderList/detail/buyer/{buyerId}/{status}", ServerResponse.class, map);
	    return response;
	}
	@Override
	public ServerResponse<List<OrderDetail>> getOrderDetailListBySellerId(int sellerId) {
		// TODO Auto-generated method stub
		ServerResponse<List<OrderDetail>> response = restTemplate.getForObject("http://localhost:8087/orderList/detail/seller/{1}", ServerResponse.class, sellerId);
	    return response;
	}
	@Override
	public ServerResponse<List<OrderDetail>> getOrderDetailListBySellerIdStatus(int sellerId, int status) {
		// TODO Auto-generated method stub
		Map<String, Integer> map = new HashMap();
		map.put("sellerId", sellerId);
		map.put("status", status);
		ServerResponse<List<OrderDetail>> response = restTemplate.getForObject("http://localhost:8087/orderList/detail/seller/{sellerId}/{status}", ServerResponse.class, map);
	    return response;
	}
	@Override
	public ServerResponse<Order> setClose(int id) {
		// TODO Auto-generated method stub
		ServerResponse<Order> response = restTemplate.getForObject("http://localhost:8087/order/payClose/{1}", ServerResponse.class, id);
	    return response;
	}
	@Override
	public ServerResponse<Order> setSend(int id) {
		// TODO Auto-generated method stub
		ServerResponse<Order> response = restTemplate.getForObject("http://localhost:8087/order/paySend/{1}", ServerResponse.class, id);
	    return response;
	}
	@Override
	public ServerResponse<Order> setEnd(int id) {
		// TODO Auto-generated method stub
		ServerResponse<Order> response = restTemplate.getForObject("http://localhost:8087/order/payEnd/{1}", ServerResponse.class, id);
	    return response;
	}
	
}
