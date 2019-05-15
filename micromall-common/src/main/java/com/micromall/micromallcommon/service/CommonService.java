package com.micromall.micromallcommon.service;

import java.util.List;

import com.micromall.apiservice.common.ServerResponse;
import com.micromall.apiservice.entities.Product;
import com.micromall.apiservice.entities.ProductDetail;

public interface CommonService {
	
	ServerResponse<Product> getProduct(int id);
	ServerResponse<ProductDetail> getProductDetail(int id);
	ServerResponse<List<ProductDetail>> getProductDetailList();
	ServerResponse<List<ProductDetail>> getProductDetailListByClassify(String classify);
	ServerResponse<List<Product>> getProductList(int userId);
}
