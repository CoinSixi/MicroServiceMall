package com.micromall.shippingservice.service;

import java.util.List;

import com.micromall.apiservice.entities.Shipping;
import com.micromall.apiservice.common.ServerResponse;



public interface IShippingService {
    //登陆
   
    ServerResponse<String> insert(Shipping shipping);
    //ServerResponse<String> deleteByUsername(String username);
    ServerResponse<String> update(Shipping shipping);
    ServerResponse<Shipping> getShippingById(int id);
    ServerResponse<List<Shipping>> getShippingsByUserId(int userId);
    ServerResponse<String> deleteShippingById(int id);
    
 }