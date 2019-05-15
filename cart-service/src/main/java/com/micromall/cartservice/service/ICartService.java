package com.micromall.cartservice.service;

import java.util.List;

import com.micromall.apiservice.common.ServerResponse;
import com.micromall.apiservice.entities.Cart;
import com.micromall.apiservice.entities.CartDetail;

public interface ICartService {
    //登陆
   
    ServerResponse<String> addCart(Cart cart);
    ServerResponse<String> deleteById(int id);
    ServerResponse<List<CartDetail>>getCartDetailListByUserId(int userId);
    //ServerResponse<String> deleteByUsername(String username);
    
 }