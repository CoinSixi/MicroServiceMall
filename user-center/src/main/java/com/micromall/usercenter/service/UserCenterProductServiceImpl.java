package com.micromall.usercenter.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.micromall.apiservice.common.ServerResponse;
import com.micromall.apiservice.entities.Product;
import com.micromall.apiservice.entities.ProductDetail;
@Service("UserCenterProductService")
public class UserCenterProductServiceImpl implements UserCenterProductService {

	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public ServerResponse<List<Product>> getProductList(int userId) {
		// TODO Auto-generated method stub
		ServerResponse<List<Product>> response = restTemplate.getForObject("http://localhost:8085/product/list/{1}", ServerResponse.class, userId);
	    return response;
	}

	@Override
	public ServerResponse<List<Product>> getProductStatus(int userId, int status) {
		// TODO Auto-generated method stub
		Map<String, Integer> map = new HashMap();
		map.put("userId", userId);
		map.put("status", status);
		ServerResponse<List<Product>> response = restTemplate.getForObject("http://localhost:8085/product/statusList/{userId}/{status}", ServerResponse.class, map);
	    return response;
	}

	@Override
	public ServerResponse<List<Product>> getProductSold(int userId) {
		// TODO Auto-generated method stub
		ServerResponse<List<Product>> response = restTemplate.getForObject("http://localhost:8085/product/soldList/{1}", ServerResponse.class, userId);
	    return response;
	}

	@Override
	public ServerResponse<String> addProduct(Product product) {
		// TODO Auto-generated method stub
		ServerResponse<String> response = restTemplate.postForObject("http://localhost:8085/product/insert", product, ServerResponse.class);
	    return response;
	}

	@Override
	public ServerResponse<String> deleteProduct(int id) {
		// TODO Auto-generated method stub
		ServerResponse<String> response = restTemplate.getForObject("http://localhost:8085/product/delete/{1}", ServerResponse.class, id);
	    return response;
	}

	@Override
	public ServerResponse<String> updateProduct(Product product) {
		// TODO Auto-generated method stub
		ServerResponse<String> response = restTemplate.postForObject("http://localhost:8085/product/update", product, ServerResponse.class);
	    return response;
	}

	
}
