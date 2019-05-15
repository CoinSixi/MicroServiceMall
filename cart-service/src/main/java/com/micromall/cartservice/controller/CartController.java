package com.micromall.cartservice.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

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

import com.micromall.apiservice.common.ServerResponse;
import com.micromall.apiservice.entities.Cart;
import com.micromall.apiservice.entities.CartDetail;
import com.micromall.apiservice.entities.User;
import com.micromall.cartservice.service.ICartService;

@RestController
public class CartController {
    
    @Autowired
    private ICartService iCartService;
    private static Logger logger = LoggerFactory.getLogger(CartController.class);
    
    @RequestMapping(value = "/cart/add", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8")
    public ServerResponse<String> register(@RequestBody Cart cart){
         return iCartService.addCart(cart);
    }
    
    @RequestMapping(value = "/cart/delete/{id}")
    public ServerResponse<String> deleteById(@PathVariable("id") int id){
         return iCartService.deleteById(id);
    }
    
    @RequestMapping(value = "/cart/detailList/{userId}")
    public ServerResponse<List<CartDetail>> getCartDetailListByUserId(@PathVariable("userId") int userId){
         return iCartService.getCartDetailListByUserId(userId);
    }
}
