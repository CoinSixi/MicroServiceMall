package com.micromall.usercenter.service;

import java.util.List;

import com.micromall.apiservice.common.ServerResponse;
import com.micromall.apiservice.entities.Product;
import com.micromall.apiservice.entities.ProductDetail;

public interface UserCenterProductService {
	
	ServerResponse<List<Product>> getProductList(int userId);
	ServerResponse<List<Product>> getProductStatus(int userId, int status);
	ServerResponse<List<Product>> getProductSold(int userId);
	
	ServerResponse<String> addProduct(Product product);
	ServerResponse<String> deleteProduct(int id);
	ServerResponse<String> updateProduct(Product product);

}
