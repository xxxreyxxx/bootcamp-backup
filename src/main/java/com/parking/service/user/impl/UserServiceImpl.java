package com.parking.service.user.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.parking.dto.user.UserDto;
import com.parking.entity.user.MasterSaldoEntity;
import com.parking.entity.user.MasterUserEntity;
import com.parking.entity.user.MasterUserInfoEntity;
import com.parking.repository.user.MasterSaldoRepository;
import com.parking.repository.user.MasterUserInfoRepository;
import com.parking.repository.user.MasterUserRepository;
import com.parking.service.user.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private MasterUserRepository userRepository;

	@Autowired
	private MasterUserInfoRepository userInfoRepository;

	@Autowired
	private MasterSaldoRepository saldoRepository;

	@Transactional
	@Override
	public MasterUserEntity saveUser(UserDto userDto) {
		MasterUserInfoEntity userInfo = new MasterUserInfoEntity();
		userInfo.setFullname(userDto.getFullname());
		userInfo.setBirthdate(userDto.getBirthdate());
		userInfo.setPhoneNumber(userDto.getPhoneNumber());
		userInfo = userInfoRepository.save(userInfo);

		MasterSaldoEntity saldo = new MasterSaldoEntity(BigDecimal.valueOf(0d));
		saldo = saldoRepository.save(saldo);

		MasterUserEntity user = new MasterUserEntity(userDto.getEmail(), userDto.getPassword(), 3l, saldo, userInfo);
		return userRepository.save(user);
	}

	@Override
	public MasterUserEntity findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Transactional
	@Override
	public MasterUserEntity updateUser(UserDto userDto) {
		MasterUserEntity user = findById(userDto.getId());
		MasterUserInfoEntity userInfo = user.getUserInfo();

		userInfo.setId(user.getUserInfo().getId());
		userInfo.setFullname(userDto.getFullname());
		userInfo.setBirthdate(userDto.getBirthdate());
		userInfo.setPhoneNumber(userDto.getPhoneNumber());
		userInfoRepository.save(userInfo);

		user.setId(user.getId());
		user.setEmail(userDto.getEmail());
		return userRepository.save(user);
	}

	@Override
	public MasterUserEntity updateUserPassword(UserDto userDto) {
		MasterUserEntity user = findById(userDto.getId());
		return userRepository.save(user);
	}

	@Override
	public MasterUserEntity findById(Long id) {
		return userRepository.findByUserId(id);
	}

	@Override
	public List<MasterUserEntity> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Page<MasterUserEntity> findAll(Integer page, Integer pageSize) {
		Pageable paging = PageRequest.of(page, pageSize);
		return userRepository.findAll(paging);
	}

	@Override
	public MasterUserInfoEntity findByPhoneNumber(String phoneNumber) {
		return userInfoRepository.findByPhoneNumber(phoneNumber);
	}

}
