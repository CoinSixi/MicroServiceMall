package com.micromall.orderservice.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.micromall.apiservice.entities.Order;
import com.micromall.apiservice.entities.OrderDetail;


@Mapper
public interface OrderMapper {
	int insert(Order record);
    int insertSelective(Order record);
    
    int updateByPrimaryKeySelective(Order record);
    int setUpdateTime(Integer id);
    int setTradeNo(@Param("id") Integer id, @Param("tradeNo") String tradeNo);
    int setPaymentTime(Integer id);
    int setSendTime(Integer id);
    int setEndTime(Integer id);
    int setCloseTime(Integer id);
    int setStatus(@Param("id") Integer id, @Param("status") Integer status);
    Order getOrderById(Integer id);
    Order getOrderByOrderNo(String orderNo);
    
    List<Order> getOrderListByUserId(Integer buyer_id);
    List<Order> getOrderListByStatus(@Param("buyerId") Integer buyerId, @Param("status") Integer status);
    
    List<OrderDetail> getOrderDetailListByBuyerId(Integer buyerId);
    List<OrderDetail> getOrderDetailListByBuyerIdStatus(@Param("buyerId") Integer buyerId, @Param("status") Integer status);
    List<OrderDetail> getOrderDetailListBySellerId(Integer sellerId);
    List<OrderDetail> getOrderDetailListBySellerIdStatus(@Param("sellerId") Integer sellerId, @Param("status") Integer status);
    int checkOrderId(Integer id);
}