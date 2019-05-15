package com.micromall.shippingservice.controller;

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
import com.micromall.apiservice.entities.Shipping;
import com.micromall.shippingservice.service.IShippingService;


@RestController
public class ShippingController {
    
    @Autowired
    private IShippingService iShippingService;
    private static Logger logger = LoggerFactory.getLogger(ShippingController.class);
    
    @RequestMapping(value = "/shipping/insert", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8")
    public ServerResponse<String> insert(@RequestBody Shipping shipping){
         return iShippingService.insert(shipping);
    }
    @RequestMapping(value = "/shipping/update", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8")
    public ServerResponse<String> update(@RequestBody Shipping shipping){
        return iShippingService.update(shipping);
    }
    @RequestMapping(value = "/shipping/delete/{id}")
    public ServerResponse<String> delete(@PathVariable("id") int id){
        return iShippingService.deleteShippingById(id);
    }
    @RequestMapping(value = "/shipping/{id}")
    public ServerResponse<Shipping> getShippingById(@PathVariable("id") int id){
        return iShippingService.getShippingById(id);
    }
    @RequestMapping(value = "/shipping/list/{userId}")
    public ServerResponse<List<Shipping>> getShippingsByUserId(@PathVariable("userId") int userId){
        return iShippingService.getShippingsByUserId(userId);
    }
}
