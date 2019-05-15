package com.micromall.usercenter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.micromall.apiservice.common.ServerResponse;
import com.micromall.apiservice.entities.Shipping;

@Service("UserCenterShippingService")
public class UserCenterShippingServiceImpl implements UserCenterShippingService {
	
	@Autowired
	RestTemplate restTemplate;

	@Override
	public ServerResponse<List<Shipping>> getShippings(int userId) {
		// TODO Auto-generated method stub
		ServerResponse<List<Shipping>> response = restTemplate.getForObject("http://localhost:8088/shipping/list/{1}", ServerResponse.class, userId);
	    return response;
	}

	@Override
	public ServerResponse<Shipping> getShipping(int id) {
		// TODO Auto-generated method stub
		ServerResponse<Shipping> response = restTemplate.getForObject("http://localhost:8088/shipping/{1}", ServerResponse.class, id);
	    return response;
	}

	@Override
	public ServerResponse<String> deleteShipping(int id) {
		// TODO Auto-generated method stub
		ServerResponse<String> response = restTemplate.getForObject("http://localhost:8088/shipping/delete/{1}", ServerResponse.class, id);
	    return response;
	}

	@Override
	public ServerResponse<String> insertShipping(Shipping shipping) {
		// TODO Auto-generated method stub
		ServerResponse<String> response = restTemplate.postForObject("http://localhost:8088/shipping/insert", shipping, ServerResponse.class);
		return response;
	}

	@Override
	public ServerResponse<String> updateShipping(Shipping shipping) {
		// TODO Auto-generated method stub
		ServerResponse<String> response = restTemplate.postForObject("http://localhost:8088/shipping/update", shipping, ServerResponse.class);
		return response;
	}
}
