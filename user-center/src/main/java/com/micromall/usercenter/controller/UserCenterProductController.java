package com.micromall.usercenter.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micromall.apiservice.common.ServerResponse;
import com.micromall.apiservice.entities.Product;
import com.micromall.apiservice.entities.ProductDetail;
import com.micromall.usercenter.service.UserCenterProductService;
import com.micromall.usercenter.service.UserCenterService;

@RestController
public class UserCenterProductController {
	@Autowired
	UserCenterProductService userCenterService;
	
	private static Logger logger = LoggerFactory.getLogger(UserCenterProductController.class);
	

	@RequestMapping(value="/user-center/productList/{userId}")
	public ServerResponse<List<Product>> getProductList(@PathVariable("userId")  int userId) {
    	ServerResponse<List<Product>> response = userCenterService.getProductList(userId);
        return response;
    }
	
	@RequestMapping(value="/user-center/productList/{userId}/{status}")
	public ServerResponse<List<Product>> getProductStatus(@PathVariable("userId")  int userId, @PathVariable("status")  int status) {
    	ServerResponse<List<Product>> response = userCenterService.getProductStatus(userId, status);
        return response;
    }
	/*
	@RequestMapping(value="/user-center/productList/sold/{userId}")
	public ServerResponse<List<Product>> getProductSold(@PathVariable("userId")  int userId) {
    	ServerResponse<List<Product>> response = userCenterService.getProductSold(userId);
        return response;
    }*/
	@RequestMapping(value="/user-center/product/add")
	public ServerResponse<String> addProduct(Product product) {
    	ServerResponse<String> response = userCenterService.addProduct(product);
        return response;
    }
	
	@RequestMapping(value="/user-center/product/delete/{id}")
	public ServerResponse<String> deleteProduct(@PathVariable("id") int id){
    	ServerResponse<String> response = userCenterService.deleteProduct(id);
        return response;
    }
	
	@RequestMapping(value="/user-center/product/update")
	public ServerResponse<String> updateProduct(Product product) {
    	ServerResponse<String> response = userCenterService.updateProduct(product);
        return response;
    }
}
