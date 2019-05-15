package com.micromall.micromallcommon.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.micromall.apiservice.common.ServerResponse;
import com.micromall.apiservice.entities.Product;
import com.micromall.apiservice.entities.ProductDetail;
@Service("CommonProductService")
public class CommonServiceImpl implements CommonService {

	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public ServerResponse<Product> getProduct(int id) {
		// TODO Auto-generated method stub
		
		Product product = restTemplate.getForObject("http://localhost:8085/product/{1}", Product.class, id);
	    return ServerResponse.createBySuccess("success", product);
	}
	
	@Override
	public ServerResponse<List<ProductDetail>> getProductDetailList() {
		// TODO Auto-generated method stub
		ServerResponse<List<ProductDetail>> response = restTemplate.getForObject("http://localhost:8085/product/list/detail", ServerResponse.class);
	    return response;
	}

	@Override
	public ServerResponse<List<ProductDetail>> getProductDetailListByClassify(String classify) {
		// TODO Auto-generated method stub
		ServerResponse<List<ProductDetail>> response = restTemplate.getForObject("http://localhost:8085/product/List/detail/{1}", ServerResponse.class, classify);
	    return response;
	}

	@Override
	public ServerResponse<List<Product>> getProductList(int userId) {
		// TODO Auto-generated method stub
		ServerResponse<List<Product>> response = restTemplate.getForObject("http://localhost:8085/product/list/{1}", ServerResponse.class, userId);
	    return response;
	}

	@Override
	public ServerResponse<ProductDetail> getProductDetail(int id) {
		// TODO Auto-generated method stub
		ServerResponse<ProductDetail> response = restTemplate.getForObject("http://localhost:8085/product/detail/{1}", ServerResponse.class, id);
	    return response;
	}

	
}
