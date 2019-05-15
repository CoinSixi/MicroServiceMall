package com.micromall.usercenter.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.micromall.apiservice.common.ServerResponse;
import com.micromall.apiservice.entities.Cart;
import com.micromall.apiservice.entities.CartDetail;
import com.micromall.apiservice.entities.Order;
import com.micromall.apiservice.entities.Product;
import com.micromall.apiservice.entities.User;
import com.micromall.usercenter.service.FtpUtil;
import com.micromall.usercenter.service.UserCenterCartService;

@RestController
public class UserCenterCartController {

	@Autowired
	UserCenterCartService userCenterService;
	
	private static Logger logger = LoggerFactory.getLogger(UserCenterCartController.class);
	
	@RequestMapping(value = "/user-center/cart/add")
    public ServerResponse<String> register(Cart cart){
         return userCenterService.addCart(cart);
    }
    
    @RequestMapping(value = "/user-center/cart/delete/{id}")
    public ServerResponse<String> deleteById(@PathVariable("id") int id){
         return userCenterService.deleteById(id);
    }
    
    @RequestMapping(value = "/user-center/cart/detailList/{userId}")
    public ServerResponse<List<CartDetail>> getCartDetailListByUserId(@PathVariable("userId") int userId){
         return userCenterService.getCartDetailListByUserId(userId);
    }
}
