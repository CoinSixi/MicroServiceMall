package com.micromall.productservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micromall.apiservice.entities.Product;
import com.micromall.apiservice.entities.ProductDetail;
import com.micromall.productservice.dao.ProductMapper;
import com.micromall.apiservice.common.ServerResponse;


@Service("iProductService")
public class ProductServiceImpl implements IProductService {
    //注入userMapper
    @Autowired
    private ProductMapper productMapper;
   
    
    @Override
    public ServerResponse<String> insert(Product product) {
        
        //插入用户
        int resultCount = productMapper.insertSelective(product);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("注册失败");
        }
        return ServerResponse.createBySuccessMessage("注册成功");
    }

	@Override
	public ServerResponse<String> deleteById(int id) {
		// TODO Auto-generated method stub
		int resultCount = productMapper.checkProduct(new Integer(id));
        if(resultCount == 0 ){
            return ServerResponse.createByErrorMessage("产品不存在!");
        }
		productMapper.deleteByProductId(new Integer(id));
		return ServerResponse.createBySuccessMessage("删除成功!");
	}

	
	@Override
	public ServerResponse<Product> updateProduct(Product product) {
		// TODO Auto-generated method stub
        int resCount = productMapper.checkProduct(product.getId());
        if(resCount == 0 ){
            return ServerResponse.createByErrorMessage("产品不存在!");
        }
        productMapper.updateByPrimaryKeySelective(product);
        productMapper.setUpdateTime(product.getId());
        Product resProduct = productMapper.getProductById(product.getId());
       
		return ServerResponse.createBySuccess("更新成功", resProduct);
	}


	@Override
	public ServerResponse<Product> updateStatus(int id, int status) {
		// TODO Auto-generated method stub
		int resCount = productMapper.checkProduct(new Integer(id));
        if(resCount == 0 ){
            return ServerResponse.createByErrorMessage("产品不存在!");
        }
        productMapper.setStatus(new Integer(id), new Integer(status));
        productMapper.setUpdateTime(new Integer(id));
        Product resProduct = productMapper.getProductById(new Integer(id));
       
		return ServerResponse.createBySuccess("更新成功", resProduct);
	}


	@Override
	public ServerResponse<Product> getProductById(int id) {
		// TODO Auto-generated method stub
		int resCount = productMapper.checkProduct(new Integer(id));
        if(resCount == 0 ){
            return ServerResponse.createByErrorMessage("产品不存在!");
        }
        Product resProduct = productMapper.getProductById(new Integer(id));
       
		return ServerResponse.createBySuccess("获取成功", resProduct);
	}


	@Override
	public ServerResponse<List<Product>> getProducts(int userId) {
		// TODO Auto-generated method stub
		List<Product> products = new ArrayList<Product>();
		products = productMapper.getProductList(new Integer(userId));
		return ServerResponse.createBySuccess("success", products);
	}

	@Override
	public ServerResponse<List<Product>> getProductStatus(int userId, int status) {
		// TODO Auto-generated method stub
		List<Product> products = new ArrayList<Product>();
		products = productMapper.getProductStatusList(new Integer(userId), new Integer(status));
		return ServerResponse.createBySuccess("success", products);
	}

	@Override
	public ServerResponse<List<ProductDetail>> getProductDetailList() {
		// TODO Auto-generated method stub
		List<ProductDetail> list = productMapper.getProductDetailList();
		return ServerResponse.createBySuccess("success", list);
	}

	@Override
	public ServerResponse<List<ProductDetail>> getProductDetailListByClassify(String classify) {
		// TODO Auto-generated method stub
		List<ProductDetail> list = productMapper.getProductDetailListByClassify(classify);
		return ServerResponse.createBySuccess("success", list);
	}

	@Override
	public ServerResponse<ProductDetail> getProductDetail(int id) {
		// TODO Auto-generated method stub
		ProductDetail product = productMapper.getProductDetail(new Integer(id));
		return ServerResponse.createBySuccess("success", product);
	}

	/*@Override
	public ServerResponse<List<Product>> getProductSold(int userId) {
		// TODO Auto-generated method stub
		List<Product> products = new ArrayList<Product>();
		products = productMapper.getProductsSold(new Integer(userId));
		return ServerResponse.createBySuccess("success", products);
	}*/
 }
