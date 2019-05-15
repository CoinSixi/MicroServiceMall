package com.micromall.usercenter.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.micromall.apiservice.common.ServerResponse;
import com.micromall.apiservice.entities.Shipping;
import com.micromall.usercenter.service.UserCenterService;
import com.micromall.usercenter.service.UserCenterShippingService;

@RestController
public class UserCenterShippingController {
	@Autowired
	UserCenterShippingService userCenterService;
	
	private static Logger logger = LoggerFactory.getLogger(UserCenterShippingController.class);
	
	@RequestMapping(value = "/user-center/shipping/insert")
    public ServerResponse<String> insert(Shipping shipping){
         return userCenterService.insertShipping(shipping);
    }
	
    @RequestMapping(value = "/user-center/shipping/update")
    public ServerResponse<String> update(Shipping shipping){
        return userCenterService.updateShipping(shipping);
    }
    
    @RequestMapping(value = "/user-center/shipping/delete/{id}")
    public ServerResponse<String> delete(@PathVariable("id") int id){
        return userCenterService.deleteShipping(id);
    }
    
    @RequestMapping(value = "/user-center/shipping/{id}")
    public ServerResponse<Shipping> getShippingById(@PathVariable("id") int id){
        return userCenterService.getShipping(id);
    }
    
    @RequestMapping(value = "/user-center/shipping/list/{userId}")
    public ServerResponse<List<Shipping>> getShippingsByUserId(@PathVariable("userId") int userId){
        return userCenterService.getShippings(userId);
    }
	
}
