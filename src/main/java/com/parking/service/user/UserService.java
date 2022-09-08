package com.parking.service.user;

import java.util.List;

import org.springframework.data.domain.Page;

import com.parking.dto.user.UserDto;
import com.parking.entity.user.MasterUserEntity;
import com.parking.entity.user.MasterUserInfoEntity;

public interface UserService {
	MasterUserEntity saveUser(UserDto userDto);

	MasterUserEntity updateUser(UserDto userDto);
	
	MasterUserEntity updateUserPassword(UserDto userDto);

	MasterUserEntity findByEmail(String email);

	MasterUserEntity findById(Long id);

	List<MasterUserEntity> findAll();

	Page<MasterUserEntity> findAll(Integer page, Integer pageSize);

	MasterUserInfoEntity findByPhoneNumber(String phoneNumber);
}
