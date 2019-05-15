package com.micromall.orderservice.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.micromall.apiservice.common.ServerResponse;
import com.micromall.apiservice.entities.Order;
import com.micromall.apiservice.entities.OrderDetail;
import com.micromall.orderservice.service.IOrderService;


@RestController
public class OrderController {
    
    @Autowired
    private IOrderService iOrderService;
    private static Logger logger = LoggerFactory.getLogger(OrderController.class);
    
    @RequestMapping(value = "/order/insert", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8")
    public ServerResponse<Order> insert(@RequestBody Order order){
 
         return iOrderService.insert(order);
    }
    @RequestMapping(value = "/order/{id}")
    public Order getOrder(@PathVariable("id")  int id){
         return iOrderService.getOrder(id).getData();
    }
    @RequestMapping(value = "/order/update", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8")
    public ServerResponse<Order> update(@RequestBody Order order){
         return iOrderService.update(order);
    }
    @RequestMapping(value = "/orderList/{userId}")
    public ServerResponse<List<Order>> getOrderList(@PathVariable("userId")  int userId){
         return iOrderService.getOrderList(userId);
    }
    @RequestMapping(value = "/orderList/{userId}/{status}")
    public ServerResponse<List<Order>> getOrderListByStatus(@PathVariable("userId")  int userId, @PathVariable("status")  int status){
         return iOrderService.getOrderListByStatus(userId, status);
    }
    
    @RequestMapping(value = "/order/status/{id}/{status}")
    public ServerResponse<Order> setStatus(@PathVariable("id")  int id, @PathVariable("status")  int status){
         return iOrderService.setStatus(id, status);
    }
    @RequestMapping(value = "/order/pay/{orderNo}/{tradeNo}")
    public ServerResponse<Order> setPaySuccess(@PathVariable("orderNo")  String orderNo, @PathVariable("tradeNo")  String tradeNo){
         return iOrderService.setPaySuccess(orderNo, tradeNo);
    }
    @RequestMapping(value = "/order/paySend/{id}")
    public ServerResponse<Order> setSend(@PathVariable("id")  int id){
         return iOrderService.setSend(id);
    }
    @RequestMapping(value = "/order/payEnd/{id}")
    public ServerResponse<Order> setEnd(@PathVariable("id")  int id){
         return iOrderService.setEnd(id);
    }
    @RequestMapping(value = "/order/payClose/{id}")
    public ServerResponse<Order> setClose(@PathVariable("id")  int id){
         return iOrderService.setClose(id);
    }
    @RequestMapping(value = "/orderList/detail/buyer/{buyerId}")
    public ServerResponse<List<OrderDetail>> getOrderDetailListByBuyerId(@PathVariable("buyerId")  int buyerId){
         return iOrderService.getOrderDetailListByBuyerId(buyerId);
    }
    @RequestMapping(value = "/orderList/detail/buyer/{buyerId}/{status}")
    public ServerResponse<List<OrderDetail>> getOrderDetailListByBuyerIdStatus(@PathVariable("buyerId")  int buyerId, @PathVariable("status")  int status){
         return iOrderService.getOrderDetailListByBuyerIdStatus(buyerId, status);
    }
    @RequestMapping(value = "/orderList/detail/seller/{sellerId}")
    public ServerResponse<List<OrderDetail>> getOrderDetailListBySellerId(@PathVariable("sellerId")  int sellerId){
         return iOrderService.getOrderDetailListBySellerId(sellerId);
    }
    @RequestMapping(value = "/orderList/detail/seller/{sellerId}/{status}")
    public ServerResponse<List<OrderDetail>> getOrderDetailListBysellerIdStatus(@PathVariable("sellerId")  int sellerId, @PathVariable("status")  int status){
         return iOrderService.getOrderDetailListBySellerIdStatus(sellerId, status);
    }
}
