package com.micromall.shippingservice.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.micromall.apiservice.entities.Shipping;

@Mapper
public interface ShippingMapper {
    int insert(Shipping record);
    int insertSelective(Shipping record);
    
    int updateByPrimaryKey(Shipping record);
    int updateByPrimaryKeySelective(Shipping record);
    
    Shipping getShippingById(Integer id);
    List<Shipping> getShippingsByUserId(Integer userId);
    
    int deleteShippingById(Integer id);
}