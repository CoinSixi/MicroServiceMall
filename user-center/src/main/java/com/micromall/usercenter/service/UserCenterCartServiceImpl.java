package com.micromall.usercenter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.micromall.apiservice.common.ServerResponse;
import com.micromall.apiservice.entities.Cart;
import com.micromall.apiservice.entities.CartDetail;

@Service("UserCenterCartService")
public class UserCenterCartServiceImpl implements UserCenterCartService {

	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public ServerResponse<String> addCart(Cart cart) {
		// TODO Auto-generated method stub
		return restTemplate.postForObject("http://localhost:8086/cart/add", cart, ServerResponse.class);
	}

	@Override
	public ServerResponse<String> deleteById(int id) {
		// TODO Auto-generated method stub
		return restTemplate.getForObject("http://localhost:8086/cart/delete/{1}", ServerResponse.class, id);
	}

	@Override
	public ServerResponse<List<CartDetail>> getCartDetailListByUserId(int userId) {
		// TODO Auto-generated method stub
		return restTemplate.getForObject("http://localhost:8086/cart/detailList/{1}", ServerResponse.class, userId);
	}

}
