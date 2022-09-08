package com.parking.service.user.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.parking.entity.user.MasterUserEntity;

@Service
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	private UserServiceImpl userServiceImpl;

	@Override
	public UserDetails loadUserByUsername(String email) {
		MasterUserEntity user = userServiceImpl.findByEmail(email);
		if (user != null) {
			return new User(user.getEmail(), user.getPassword(), new ArrayList<>());
		} else {
			return null;
		}
	}
}
