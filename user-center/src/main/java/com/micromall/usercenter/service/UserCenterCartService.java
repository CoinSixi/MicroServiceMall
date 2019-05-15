package com.micromall.usercenter.service;

import java.util.List;

import com.micromall.apiservice.common.ServerResponse;
import com.micromall.apiservice.entities.Cart;
import com.micromall.apiservice.entities.CartDetail;

public interface UserCenterCartService {
	
	ServerResponse<String> addCart(Cart cart);
	ServerResponse<String> deleteById(int id);
	ServerResponse<List<CartDetail>> getCartDetailListByUserId(int userId);

}
