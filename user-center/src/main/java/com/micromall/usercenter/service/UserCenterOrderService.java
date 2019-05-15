package com.micromall.usercenter.service;

import java.util.List;

import com.micromall.apiservice.common.ServerResponse;
import com.micromall.apiservice.entities.Order;
import com.micromall.apiservice.entities.OrderDetail;

public interface UserCenterOrderService {

	ServerResponse<List<Order>> getOrderList(int userId, int status);
	ServerResponse<Order> getOrderById(int id);
	ServerResponse<List<Order>> deleteOder(int orderId);
	ServerResponse<List<OrderDetail>> getOrderDetailListByBuyerId(int buyerId);
    ServerResponse<List<OrderDetail>> getOrderDetailListByBuyerIdStatus(int buyerId, int status);
    ServerResponse<List<OrderDetail>> getOrderDetailListBySellerId(int sellerId);
    ServerResponse<List<OrderDetail>> getOrderDetailListBySellerIdStatus(int sellerId, int status);
    
    ServerResponse<Order> setClose(int id);
    ServerResponse<Order> setSend(int id);
    ServerResponse<Order> setEnd(int id);
}
