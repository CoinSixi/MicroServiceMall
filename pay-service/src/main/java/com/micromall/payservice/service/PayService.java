package com.micromall.payservice.service;

import java.util.List;

import com.micromall.apiservice.common.ServerResponse;
import com.micromall.apiservice.entities.Order;
import com.micromall.apiservice.entities.Product;
import com.micromall.apiservice.entities.Shipping;

public interface PayService {
	ServerResponse<Order> aliPay(int productId, int userId, int count, int shippingId);
	ServerResponse<Order> notify(String orderNo, String tradeNo);
	//ServerResponse<Order> closePay(int orderId);
}
