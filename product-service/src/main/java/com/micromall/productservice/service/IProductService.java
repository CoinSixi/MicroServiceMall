package com.micromall.productservice.service;

import java.util.List;

import com.micromall.apiservice.common.ServerResponse;
import com.micromall.apiservice.entities.Product;
import com.micromall.apiservice.entities.ProductDetail;

public interface IProductService {
   
    ServerResponse<String> insert(Product product);
    ServerResponse<String> deleteById(int id);
    ServerResponse<Product> updateProduct(Product product);
    ServerResponse<Product> updateStatus(int id, int status);
    ServerResponse<Product> getProductById(int id);
    ServerResponse<List<Product>> getProducts(int userId);
    ServerResponse<List<Product>> getProductStatus(int userId, int status);
    //ServerResponse<List<Product>> getProductSold(int userId);
    ServerResponse<List<ProductDetail>> getProductDetailList();
    ServerResponse<List<ProductDetail>> getProductDetailListByClassify(String classify);
    ServerResponse<ProductDetail> getProductDetail(int id);
    
 }