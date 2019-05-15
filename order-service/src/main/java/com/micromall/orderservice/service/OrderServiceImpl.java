package com.micromall.orderservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micromall.apiservice.common.ServerResponse;
import com.micromall.apiservice.entities.Order;
import com.micromall.apiservice.entities.OrderDetail;
import com.micromall.orderservice.dao.OrderMapper;



@Service("iOrderService")
public class OrderServiceImpl implements IOrderService {
    //注入userMapper
    @Autowired
    private OrderMapper orderMapper;
   
    
    @Override
    public ServerResponse<Order> insert(Order order) {
        
        //插入用户
        int resultCount = orderMapper.insert(order);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("注册失败");
        }
        return ServerResponse.createBySuccessMessage("注册成功");
    }

	@Override
	public ServerResponse<Order> update(Order order) {
		// TODO 更新订单信息
		int resultCount = orderMapper.checkOrderId(order.getId());
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("订单不存在");
        }
        orderMapper.updateByPrimaryKeySelective(order);
        orderMapper.setUpdateTime(order.getId());
        Order o = orderMapper.getOrderById(order.getId());
        return ServerResponse.createBySuccess("更新成功", o);
	}
	
	@Override
	public ServerResponse<List<Order>> getOrderList(int userId) {
		// 获得用户订购记录
		List<Order> orderList = new ArrayList<Order>();
		orderList = orderMapper.getOrderListByUserId(new Integer(userId));
		return ServerResponse.createBySuccess("获取成功", orderList);
	}


	@Override
	public ServerResponse<Order> getOrder(int id) {
		// TODO Auto-generated method stub
		Order order = orderMapper.getOrderById(new Integer(id));
		if(order==null){
			return ServerResponse.createByErrorMessage("订单不存在!");
		}
		return ServerResponse.createBySuccess("success", order);
	}

	@Override
	public ServerResponse<List<Order>> getOrderListByStatus(int userId, int status) {
		// TODO Auto-generated method stub
		List<Order> orderList = new ArrayList<Order>();
		orderList = orderMapper.getOrderListByStatus(new Integer(userId), new Integer(status));
		return ServerResponse.createBySuccess("获取成功", orderList);
	}

	@Override
	public ServerResponse<Order> setStatus(int id, int status) {
		// TODO Auto-generated method stub
		int response = orderMapper.setStatus(id, status);
		if(response==0){
			return ServerResponse.createByErrorMessage("failer");
		}
		return ServerResponse.createBySuccessMessage("success");
	}

	@Override
	public ServerResponse<Order> setPaySuccess(String orderNo, String tradeNo) {
		Order order = orderMapper.getOrderByOrderNo(orderNo);
		// TODO Auto-generated method stub
		Integer orderId = order.getId();
		orderMapper.setTradeNo(orderId, tradeNo);
		orderMapper.setPaymentTime(orderId);
		orderMapper.setStatus(orderId, new Integer(1));
		orderMapper.setUpdateTime(orderId);
		return ServerResponse.createBySuccessMessage("支付成功");
	}

	@Override
	public ServerResponse<Order> setClose(int id) {
		// TODO Auto-generated method stub
		Integer orderId = new Integer(id);
		orderMapper.setCloseTime(id);
		orderMapper.setStatus(orderId, new Integer(4));
		orderMapper.setUpdateTime(id);
		return ServerResponse.createBySuccessMessage("交易关闭");
	}

	@Override
	public ServerResponse<Order> setSend(int id) {
		// TODO Auto-generated method stub
		Integer orderId = new Integer(id);
		orderMapper.setSendTime(id);
		orderMapper.setStatus(orderId, new Integer(2));
		orderMapper.setUpdateTime(id);
		return ServerResponse.createBySuccessMessage("已发货");
	}

	@Override
	public ServerResponse<Order> setEnd(int id) {
		// TODO Auto-generated method stub
		Integer orderId = new Integer(id);
		orderMapper.setEndTime(id);
		orderMapper.setStatus(orderId, new Integer(3));
		orderMapper.setUpdateTime(id);
		return ServerResponse.createBySuccessMessage("已收货");
	}

	@Override
	public ServerResponse<List<OrderDetail>> getOrderDetailListByBuyerId(int buyerId) {
		// TODO Auto-generated method stub
		List<OrderDetail> list = orderMapper.getOrderDetailListByBuyerId(new Integer(buyerId));
		return ServerResponse.createBySuccess("product+order(buyer)", list);
	}

	@Override
	public ServerResponse<List<OrderDetail>> getOrderDetailListByBuyerIdStatus(int buyerId, int status) {
		// TODO Auto-generated method stub
		List<OrderDetail> list = orderMapper.getOrderDetailListByBuyerIdStatus(new Integer(buyerId), new Integer(status));
		return ServerResponse.createBySuccess("product+order(buyer+status)", list);
	}

	@Override
	public ServerResponse<List<OrderDetail>> getOrderDetailListBySellerId(int sellerId) {
		// TODO Auto-generated method stub
		List<OrderDetail> list = orderMapper.getOrderDetailListBySellerId(new Integer(sellerId));
		return ServerResponse.createBySuccess("product+order(seller)", list);
	}

	@Override
	public ServerResponse<List<OrderDetail>> getOrderDetailListBySellerIdStatus(int sellerId, int status) {
		// TODO Auto-generated method stub
		List<OrderDetail> list = orderMapper.getOrderDetailListBySellerIdStatus(new Integer(sellerId), new Integer(status));
		return ServerResponse.createBySuccess("product+order(seller+status)", list);
	}
 }
