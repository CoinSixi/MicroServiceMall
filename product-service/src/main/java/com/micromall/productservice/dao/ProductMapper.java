package com.micromall.productservice.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.micromall.apiservice.entities.Product;
import com.micromall.apiservice.entities.ProductDetail;


@Mapper
public interface ProductMapper {

	int insert(Product record);

    int insertSelective(Product record);
  //更新数据
  	int updateByPrimaryKey(Product record);
  	int updateByPrimaryKeySelective(Product record);
  	
  	//更新时间
  	int setUpdateTime(Integer id);
  	//修改在售状态
  	int setStatus(@Param("id") Integer id, @Param("status") Integer status);
  	//删除产品
  	int deleteByProductId(Integer id);
  	
  	//获取数据
  	Product getProductById(Integer id);
  	ProductDetail getProductDetail(Integer id);
  	List<Product> getProductList(Integer userId);
  	List<Product> getProductStatusList(@Param("userId") Integer userId, @Param("status") Integer status);
  	List<ProductDetail> getProductDetailList();
  	List<ProductDetail> getProductDetailListByClassify(String classify);
  	//List<Product> getProductsSold(Integer userId);
  	
  	//验证产品是否存在
  	int checkProduct(Integer id);
}