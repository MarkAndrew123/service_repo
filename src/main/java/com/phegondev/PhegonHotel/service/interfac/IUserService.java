package com.phegondev.PhegonHotel.service.interfac;

import com.phegondev.PhegonHotel.dto.Response;
import com.phegondev.PhegonHotel.dto.LoginRequest;
import com.phegondev.PhegonHotel.entity.User;

public interface IUserService {

	Response register(User loginRequest);
	
	Response login(LoginRequest loginRequest);
	
	Response getAllUsers();
	
	Response getUserBookingsHistory(String userId);
	
	Response deleteUser(String userId);
	
	Response getUserById(String userId);
	Response getMyInfo(String email);
	
		
}
