package com.micromall.cartservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micromall.apiservice.common.ServerResponse;
import com.micromall.apiservice.entities.Cart;
import com.micromall.apiservice.entities.CartDetail;
import com.micromall.cartservice.dao.CartMapper;


@Service("iCartService")
public class CartServiceImpl implements ICartService {
    //注入userMapper
    @Autowired
    private CartMapper cartMapper;
   
    
    @Override
    public ServerResponse<String> addCart(Cart cart) {
        Cart cart1 = cartMapper.getCart(cart.getUserId(), cart.getProductId());
        if(cart1 == null){
        	//插入cart
        	cart.setQuantity(1);
            int resultCount = cartMapper.insertSelective(cart);
            if (resultCount == 0) {
                return ServerResponse.createByErrorMessage("插入失败");
            } 
            return ServerResponse.createBySuccessMessage("插入成功");
        }
        cartMapper.setQuantity(cart1.getId(), cart1.getQuantity()+1);
        cartMapper.setUpdateTime(cart1.getId());
        return ServerResponse.createBySuccessMessage("更新成功");
        
    }

	@Override
	public ServerResponse<String> deleteById(int id) {
		// TODO Auto-generated method stub
		//删除cart
        int resultCount = cartMapper.deleteById(new Integer(id));
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("删除失败");
        }
        cartMapper.setUpdateTime(new Integer(id));
        return ServerResponse.createBySuccessMessage("删除成功");
	}


	@Override
	public ServerResponse<List<CartDetail>> getCartDetailListByUserId(int userId) {
		// TODO Auto-generated method stub
		List<CartDetail> list = cartMapper.getCartDetailListByUserId(new Integer(userId));
		return ServerResponse.createBySuccess("success", list);
	}
 }
