package com.micromall.orderservice.service;

import java.util.List;

import com.micromall.apiservice.common.ServerResponse;
import com.micromall.apiservice.entities.Order;
import com.micromall.apiservice.entities.OrderDetail;


public interface IOrderService {
    //登陆
   
    ServerResponse<Order> insert(Order order);
    ServerResponse<Order> update(Order order);
    //ServerResponse<String> deleteByUsername(String username);
    ServerResponse<Order> getOrder(int id);
    ServerResponse<List<Order>> getOrderList(int userId);
    ServerResponse<List<Order>> getOrderListByStatus(int userId, int status);
    ServerResponse<Order> setStatus(int id, int status);
    ServerResponse<Order> setPaySuccess(String orderNo, String tradeNo);
    ServerResponse<Order> setClose(int id);
    ServerResponse<Order> setSend(int id);
    ServerResponse<Order> setEnd(int id);
    ServerResponse<List<OrderDetail>> getOrderDetailListByBuyerId(int buyerId);
    ServerResponse<List<OrderDetail>> getOrderDetailListByBuyerIdStatus(int buyerId, int status);
    ServerResponse<List<OrderDetail>> getOrderDetailListBySellerId(int sellerId);
    ServerResponse<List<OrderDetail>> getOrderDetailListBySellerIdStatus(int sellerId, int status);
 }