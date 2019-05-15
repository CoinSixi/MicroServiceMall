package com.micromall.micromallcommon.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micromall.apiservice.common.ServerResponse;
import com.micromall.apiservice.entities.Product;
import com.micromall.apiservice.entities.ProductDetail;
import com.micromall.micromallcommon.service.CommonService;

@RestController
public class CommonProductController {
	@Autowired
	CommonService commonService;
	
	private static Logger logger = LoggerFactory.getLogger(CommonProductController.class);
	
	@RequestMapping(value = "/micromall-common/product/{id}")
    public ServerResponse<Product> getProduct(@PathVariable("id") int id){
         return commonService.getProduct(id);
    }
	
	@RequestMapping(value = "/micromall-common/product/list/detail")
    public ServerResponse<List<ProductDetail>> getProductDetailList(){
         return commonService.getProductDetailList();
    }
	
	@RequestMapping(value = "/micromall-common/product/detail/{id}")
    public ServerResponse<ProductDetail> getProductDetail(@PathVariable("id") int id){
         return commonService.getProductDetail(id);
    }

    @RequestMapping(value = "/micromall-common/product/list/detail/{classify}")
    public ServerResponse<List<ProductDetail>> getProductDetailListByClassify(@PathVariable("classify") String classify){
         return commonService.getProductDetailListByClassify(classify);
    }
    
    @RequestMapping(value = "/micromall-common/product/list/{userId}")
    public ServerResponse<List<Product>> getProductListByUserId(@PathVariable("userId") int userId){
         return commonService.getProductList(userId);
    }
    
    
    
	
}
