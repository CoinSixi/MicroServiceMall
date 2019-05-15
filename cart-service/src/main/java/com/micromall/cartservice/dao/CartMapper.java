package com.micromall.cartservice.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.micromall.apiservice.entities.Cart;
import com.micromall.apiservice.entities.CartDetail;


@Mapper
public interface CartMapper {
    int insert(Cart record);
    int insertSelective(Cart record);
    
    int setUpdateTime(Integer id);
    int setQuantity(@Param("id") Integer id, @Param("quantity") Integer quantity);
    
    int deleteById(Integer id);
    
    Cart getCart(@Param("userId") Integer userId, @Param("productId") Integer productId);
    List<CartDetail> getCartDetailListByUserId(Integer userId);
    
}