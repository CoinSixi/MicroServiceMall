package com.micromall.productservice.controller;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.micromall.apiservice.entities.Product;
import com.micromall.apiservice.entities.ProductDetail;
import com.micromall.productservice.service.IProductService;
import com.micromall.apiservice.common.ServerResponse;

@RestController
public class ProductController {
    
    @Autowired
    private IProductService iProductService;
    private static Logger logger = LoggerFactory.getLogger(ProductController.class);
    
    @RequestMapping(value = "/product/insert", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8")
    public ServerResponse<String> insert(@RequestBody Product product){
    	
         return iProductService.insert(product);
    }
    
    @RequestMapping(value = "/product/delete/{id}")
    public ServerResponse<String> deleteById(@PathVariable("id")  int id){
         return iProductService.deleteById(id);
    }
    
    @RequestMapping(value = "/product/update", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8")
    public ServerResponse<Product> updateProduct(@RequestBody Product product){
         return iProductService.updateProduct(product);
    }
    
    @RequestMapping(value = "/product/updateStatus/{id}/{status}")
    public ServerResponse<Product> updateStatus(@PathVariable("id")  int id, @PathVariable("status")  int status){
         return iProductService.updateStatus(id, status);
    }
    
    @RequestMapping(value = "/product/{id}")
    public Product getProduct(@PathVariable("id")  int id){
         return iProductService.getProductById(id).getData();
    }
    
    @RequestMapping(value = "/product/list/{userId}")
    public ServerResponse<List<Product>> getProducts(@PathVariable("userId")  int userId){
         return iProductService.getProducts(userId);
    }

    @RequestMapping(value = "/product/statusList/{userId}/{status}")
    public ServerResponse<List<Product>> getProducts(@PathVariable("userId")  int userId, @PathVariable("status")  int status){
         return iProductService.getProductStatus(userId, status);
    }
    
    @RequestMapping(value = "/product/list/detail")
    public ServerResponse<List<ProductDetail>> getProductDetailList(){
         return iProductService.getProductDetailList();
    }

    @RequestMapping(value = "/product/List/detail/{classify}")
    public ServerResponse<List<ProductDetail>> getProductDetailListByClassify(@PathVariable("classify") String classify){
         return iProductService.getProductDetailListByClassify(classify);
    }

    @RequestMapping(value = "/product/detail/{id}")
    public ServerResponse<ProductDetail> getProductDetail(@PathVariable("id") int id){
         return iProductService.getProductDetail(id);
    }
    /*@RequestMapping(value = "/product/soldList/{userId}")
    public ServerResponse<List<Product>> getProductsSold(@PathVariable("userId")  int userId){
         return iProductService.getProductSold(userId);
    }*/
    
}
