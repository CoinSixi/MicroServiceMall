package com.micromall.shippingservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micromall.apiservice.entities.Shipping;
import com.micromall.shippingservice.dao.ShippingMapper;
import com.micromall.apiservice.common.ServerResponse;




@Service("iShippingService")
public class ShippingServiceImpl implements IShippingService {
    //注入userMapper
    @Autowired
    private ShippingMapper shippingMapper;
   
    
    @Override
    public ServerResponse<String> insert(Shipping shipping) {
        
        //插入用户
        int resultCount = shippingMapper.insert(shipping);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("注册失败");
        }
        return ServerResponse.createBySuccessMessage("注册成功");
    }


	@Override
	public ServerResponse<String> update(Shipping shipping) {
		// TODO Auto-generated method stub
		//插入用户
        int resultCount = shippingMapper.updateByPrimaryKeySelective(shipping);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("更新失败");
        }
        return ServerResponse.createBySuccessMessage("更新成功");
	}


	@Override
	public ServerResponse<Shipping> getShippingById(int id) {
		// TODO Auto-generated method stub
		Shipping shipping = shippingMapper.getShippingById(new Integer(id));
		if (shipping == null) {
            return ServerResponse.createByErrorMessage("获取失败");
        }
        return ServerResponse.createBySuccess("获取成功",shipping);
	}


	@Override
	public ServerResponse<List<Shipping>> getShippingsByUserId(int userId) {
		// TODO Auto-generated method stub
		List<Shipping> shippings = shippingMapper.getShippingsByUserId(new Integer(userId));
		if (shippings == null) {
            return ServerResponse.createByErrorMessage("获取失败");
        }
        return ServerResponse.createBySuccess("获取成功",shippings);
	}


	@Override
	public ServerResponse<String> deleteShippingById(int id) {
		// TODO Auto-generated method stub
		int resultCount = shippingMapper.deleteShippingById(new Integer(id));
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("删除失败");
        }
        return ServerResponse.createBySuccessMessage("删除成功");
	}
 }
