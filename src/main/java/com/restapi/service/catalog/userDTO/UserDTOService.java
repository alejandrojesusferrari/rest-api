package com.restapi.service.catalog.userDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restapi.beans.dto.user.UserDTO;
import com.restapi.beans.model.user.User;
import com.restapi.service.model.user.UserService;





@Service
public class UserDTOService {

	
	@Autowired
	private UserService userService;  
	
	
	

	public UserDTO getUserInfo(Long userId) {
		
		try {
			User user = userService.findById(userId);
			UserDTO userDTO = new UserDTO(); 
			userDTO.setUser(user);
			return userDTO;
			
		} catch (Exception e) {
			return null;
		}
		
		

	}









}
