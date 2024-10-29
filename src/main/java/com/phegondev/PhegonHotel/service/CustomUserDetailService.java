package com.phegondev.PhegonHotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.phegondev.PhegonHotel.exception.OurException;
import com.phegondev.PhegonHotel.repo.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService{

	
	@Autowired
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return userRepository.findByEmail(username).orElseThrow(()-> new OurException("Username/Email not found"));
	}

}
