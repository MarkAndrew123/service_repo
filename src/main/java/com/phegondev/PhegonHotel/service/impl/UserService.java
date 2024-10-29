package com.phegondev.PhegonHotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.phegondev.PhegonHotel.dto.LoginRequest;
import com.phegondev.PhegonHotel.dto.Response;
import com.phegondev.PhegonHotel.dto.UserDTO;
import com.phegondev.PhegonHotel.entity.User;
import com.phegondev.PhegonHotel.exception.OurException;
import com.phegondev.PhegonHotel.repo.UserRepository;
import com.phegondev.PhegonHotel.service.interfac.IUserService;
import com.phegondev.PhegonHotel.utils.JWTUtils;
import com.phegondev.PhegonHotel.utils.Utils;



@Service
public class UserService implements IUserService {

	
  @Autowired
  private UserRepository userRepository;
	
    @Autowired  
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JWTUtils jwtUtils;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Override
	public Response register(User user) {
	Response response = new Response();
	try {
		
		if(user.getRole() == null || user.getRole().isBlank()) {
			user.setRole("USER");
		}
		if (userRepository.existsByEmail(user.getEmail())) {
			throw new OurException(user.getEmail() + "Already Exists");
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User savedUser = userRepository.save(user);
		UserDTO userDTO = Utils.mapUserEntitytoUserDTO(savedUser);
		response.setStatusCode(200);
		response.setUser(userDTO);
		 
		
	} catch (OurException e) {
		 response.setStatusCode(400);
		 response.setMessage(e.getMessage());
	} catch (Exception e) {
		 response.setStatusCode(500);
		 response.setMessage("Error Occured During User Registration" + e.getMessage());
			}
		return response;
	}

	@Override
	public Response login(LoginRequest loginRequest) {
		Response response =  new Response();
		
		 try {
			 authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
			 
			 var user = userRepository.findByEmail((String) loginRequest.getEmail())
			            .orElseThrow(() -> new OurException("User Not found"));
			var token = jwtUtils.generateToken(user);
			 response.setStatusCode(200);
			 response.setToken(token);
			 response.setRole(user.getRole());
			 response.setExpiratoniTime("7 days");
			 response.setMessage("Successful"); 
			 			 
			 }catch (OurException e) {
			 response.setStatusCode(404);
			 response.setMessage(e.getMessage());
			
		} catch (Exception e) {
			 response.setStatusCode(500);
			 response.setMessage("Error Occured During User Login" + e.getMessage());
		}
		 return response;

}

	@Override
	public Response getAllUsers() {
	Response response = new Response();
	try {
		List<User> userList = userRepository.findAll();
		List<UserDTO> userDTOList = Utils.mapUserListEntityToUserListDTO(userList);
		response.setStatusCode(200);
		response.setMessage("Sucessful");
		response.setUserlist(userDTOList);
		
		
		
	}catch (Exception e) {
		response.setStatusCode(500);
		response.setMessage("Error Getting All Users" + e.getMessage());
	}
	
	return response;
	}

	@Override
	public Response getUserBookingsHistory(String userId) {
		
		Response response = new Response();
		
		try {
			User user = userRepository.findById(Long.valueOf(userId)).orElseThrow(() -> new OurException("User not Found"));
			UserDTO userDTO = Utils.mapUSerEntitytoUserDTOPlusUserBookingsAndRoom(user);
			response.setStatusCode(200);
			response.setMessage("Successful");
			response.setUser(userDTO);
			
		} catch (OurException e) {
			response.setStatusCode(404);
			response.setMessage(e.getMessage());
			}catch (Exception e) {
				response.setStatusCode(500);
				response.setMessage("Error Getting all users" + e.getMessage());
				
			}
		
		
		return response;
	}

	@Override
	public Response deleteUser(String userId) {
		
		Response response =  new Response();
		
		
		try {
		 userRepository.findById(Long.valueOf(userId)).orElseThrow(() -> new OurException("User not Found"));
		userRepository.deleteById(Long.valueOf(userId));
			response.setStatusCode(200);
			response.setMessage("Successful");
			
			
		} catch (OurException e) {
		    response.setStatusCode(404);
		    response.setMessage(e.getMessage());
		    
		}
		catch (Exception e) {
			response.setStatusCode(500);
			response.setMessage("Error deleting  user" + e.getMessage());
		}
		
		return response;
	}

	@Override
	public Response getUserById(String userId) {
	

		Response response =  new Response();
		
		
		try {
		 User user = userRepository.findById(Long.valueOf(userId)).orElseThrow(() -> new OurException("User not Found"));
		UserDTO userDTO = Utils.mapUserEntitytoUserDTO(user);
			response.setStatusCode(200);
			response.setMessage("Successful");
			response.setUser(userDTO);
			
			
		} catch (OurException e) {
		    response.setStatusCode(404);
		    response.setMessage(e.getMessage());
		    
		}
		catch (Exception e) {
			response.setStatusCode(500);
			response.setMessage("Error getting all users" + e.getMessage());
		}
		
		return response;
	}
	

	public Response getMyInfo(String email) {
	
Response response =  new Response();
		
		
		try {
		 User user = userRepository.findByEmail(String.valueOf(email)).orElseThrow(() -> new OurException("User not Found"));
		UserDTO userDTO = Utils.mapUserEntitytoUserDTO(user);
			response.setStatusCode(200);
			response.setMessage("Successful");
			response.setUser(userDTO);
			
			
		} catch (OurException e) {
		    response.setStatusCode(404);
		    response.setMessage(e.getMessage());
		    
		}
		catch (Exception e) {
			response.setStatusCode(500);
			response.setMessage("Error getting all users" + e.getMessage());
		}
		
		return response;
	}

}
