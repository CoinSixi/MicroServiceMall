package com.micromall.usercenter.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micromall.apiservice.common.ServerResponse;
import com.micromall.apiservice.entities.Order;
import com.micromall.apiservice.entities.OrderDetail;
import com.micromall.usercenter.service.UserCenterOrderService;
import com.micromall.usercenter.service.UserCenterService;

@RestController
public class UserCenterOrderController {
	@Autowired
	UserCenterOrderService userCenterService;
	
	private static Logger logger = LoggerFactory.getLogger(UserCenterOrderController.class);
	
	@RequestMapping(value="/user-center/order/{id}")
	public ServerResponse<Order> getOrderById(@PathVariable("id")  int id) {
    	ServerResponse<Order> response = userCenterService.getOrderById(id);
        return response;
    }
	
	@RequestMapping(value="/user-center/orderList/{userId}/{status}")
	public ServerResponse<List<Order>> getOrders(@PathVariable("userId")  int userId, @PathVariable("status")  int status) {
    	ServerResponse<List<Order>> response = userCenterService.getOrderList(userId, status);
        return response;
    }
    @RequestMapping(value = "/user-center/orderList/detail/buyer/{buyerId}")
    public ServerResponse<List<OrderDetail>> getOrderDetailListByBuyerId(@PathVariable("buyerId")  int buyerId){
         return userCenterService.getOrderDetailListByBuyerId(buyerId);
    }
    @RequestMapping(value = "/user-center/orderList/detail/buyer/{buyerId}/{status}")
    public ServerResponse<List<OrderDetail>> getOrderDetailListByBuyerIdStatus(@PathVariable("buyerId")  int buyerId, @PathVariable("status")  int status){
         return userCenterService.getOrderDetailListByBuyerIdStatus(buyerId, status);
    }
    @RequestMapping(value = "/user-center/orderList/detail/seller/{sellerId}")
    public ServerResponse<List<OrderDetail>> getOrderDetailListBySellerId(@PathVariable("sellerId")  int sellerId){
         return userCenterService.getOrderDetailListBySellerId(sellerId);
    }
    @RequestMapping(value = "/user-center/orderList/detail/seller/{sellerId}/{status}")
    public ServerResponse<List<OrderDetail>> getOrderDetailListBysellerIdStatus(@PathVariable("sellerId")  int sellerId, @PathVariable("status")  int status){
         return userCenterService.getOrderDetailListBySellerIdStatus(sellerId, status);
    }
    
    @RequestMapping(value = "/user-center/order/paySend/{id}")
    public ServerResponse<Order> setSend(@PathVariable("id")  int id){
         return userCenterService.setSend(id);
    }
    @RequestMapping(value = "/user-center/order/payEnd/{id}")
    public ServerResponse<Order> setEnd(@PathVariable("id")  int id){
         return userCenterService.setEnd(id);
    }
    @RequestMapping(value = "/user-center/order/payClose/{id}")
    public ServerResponse<Order> setClose(@PathVariable("id")  int id){
         return userCenterService.setClose(id);
    }
	
	

}
