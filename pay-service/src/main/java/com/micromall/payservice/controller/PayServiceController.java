package com.micromall.payservice.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.micromall.apiservice.common.ServerResponse;
import com.micromall.apiservice.entities.Order;
import com.micromall.apiservice.entities.User;
import com.micromall.payservice.service.PayService;


@RestController
public class PayServiceController {
	@Autowired
	PayService payService;
	
	private static Logger logger = LoggerFactory.getLogger(PayServiceController.class);
	
	@RequestMapping(value="/pay-service/pay")
	public Object Pay(HttpServletResponse response) throws IOException {
    	ServerResponse<Order> response1 = payService.aliPay(1,1,1,1);
		response.setContentType("text/html;charset=" + "UTF-8");
	    response.getWriter().write(response1.getMsg());//直接将完整的表单html输出到页面
	    response.getWriter().flush();
	    response.getWriter().close();
        return null;
    }
	@RequestMapping(value="/pay-service/return")
	public Object returnMsg(@RequestParam String trade_no) {
    	//ServerResponse<User> response = userCenterService.getUserInfo(id);
        return trade_no;
    }
	@RequestMapping(value="/pay-service/notify")
	public Object notify(@RequestParam String out_trade_no, @RequestParam String trade_no) {
        return payService.notify(out_trade_no, trade_no);
		//return out_trade_no+" "+trade_no;
    }
	/*@RequestMapping(value="/pay-service/pay/close/{id}")
	public Object payClose(@PathVariable("id") int id) {
    	//ServerResponse<User> response = userCenterService.getUserInfo(id);
        return payService.closePay(id);
    }*/
}
