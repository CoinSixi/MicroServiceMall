package com.micromall.usercenter.service;

import java.util.List;

import com.micromall.apiservice.common.ServerResponse;
import com.micromall.apiservice.entities.Shipping;

public interface UserCenterShippingService {
	ServerResponse<List<Shipping>> getShippings(int userId);
	ServerResponse<Shipping> getShipping(int id);
	ServerResponse<String> deleteShipping(int id);
	ServerResponse<String> insertShipping(Shipping shipping);
	ServerResponse<String> updateShipping(Shipping shipping);

}
