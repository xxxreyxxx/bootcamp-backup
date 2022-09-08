package com.parking.service.admin.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.parking.dto.user.AdminDto;
import com.parking.entity.building.MasterBuildingEntity;
import com.parking.entity.sysmenu.SysMenuHeader;
import com.parking.entity.user.MasterUserEntity;
import com.parking.entity.user.MasterUserInfoEntity;
import com.parking.procces.GenerateRandom;
import com.parking.repository.admin.AdminMenuRepository;
import com.parking.repository.building.MasterBuildingRepository;
import com.parking.repository.user.MasterUserInfoRepository;
import com.parking.repository.user.MasterUserRepository;
import com.parking.service.admin.AdminService;
import com.parking.service.email.impl.EmailServiceImpl;

@Service
public class AdminServiceImpl implements AdminService {
	private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	@Autowired
	private MasterUserRepository userRepository;

	@Autowired
	private AdminMenuRepository adminMenuRepository;

	@Autowired
	private MasterUserInfoRepository userInfoRepository;

	@Autowired
	private MasterBuildingRepository buildingRepository;

	@Autowired
	private GenerateRandom generateRandomAlfa;

	@Autowired
	private EmailServiceImpl emailService;

	@Override
	public MasterUserEntity findById(Long id) {
		Optional<MasterUserEntity> user = userRepository.findById(id);
		MasterUserEntity result = new MasterUserEntity();

		if (user.isPresent()) {
			result = user.get();
		}

		return result;
	}

	@Transactional
	@Override
	public MasterUserEntity createUser(AdminDto adminDto) {
		MasterUserInfoEntity userInfo = new MasterUserInfoEntity();
		userInfo.setFullname(adminDto.getFullname());
		userInfo.setBirthdate(adminDto.getBirthdate());
		userInfo.setPhoneNumber(adminDto.getPhoneNumber());
		userInfo = userInfoRepository.save(userInfo);

		MasterUserEntity admin = new MasterUserEntity();
		String random = generateRandomAlfa.getAlphaNumericString(10);
		admin.setEmail(adminDto.getEmail());
		admin.setPassword(bCryptPasswordEncoder.encode(random));
		admin.setRoleId(adminDto.getAdminRoleId());

		admin.setUserInfo(userInfo);
		MasterUserEntity result = userRepository.save(admin);

		if (adminDto.getBuildingId() != null) {
			Optional<MasterBuildingEntity> optional = buildingRepository.findById(adminDto.getBuildingId());
			if (optional.isPresent()) {
				MasterBuildingEntity building = optional.get();
				building.setAdminId(result.getId());
				buildingRepository.save(building);
				emailService.sendMessageAdmin(adminDto.getEmail(), "Confirmation Account!", building.getBarcode(),
						adminDto.getEmail(), random);
			}
		} else {
			emailService.sendMessageSuperAdmin(adminDto.getEmail(), "Confirmation Account!", adminDto.getEmail(),
					random);
		}

		return result;
	}

	@Transactional
	@Override
	public MasterUserEntity updateUser(AdminDto adminDto) {
		MasterUserEntity admin = findById(adminDto.getId());
		MasterUserInfoEntity userInfo = admin.getUserInfo();

		userInfo.setId(userInfo.getId());
		userInfo.setFullname(adminDto.getFullname());
		userInfo.setBirthdate(adminDto.getBirthdate());
		userInfo.setPhoneNumber(adminDto.getPhoneNumber());
		userInfoRepository.save(userInfo);

		admin.setEmail(adminDto.getEmail());
		if (adminDto.getPassword() != null) {
			admin.setPassword(adminDto.getPassword());

		}
		return userRepository.save(admin);
	}

	@Override
	public MasterUserEntity updateUserPassword(AdminDto admin) {
		MasterUserEntity save = findById(admin.getId());
		save.setPassword(admin.getPassword());
		return userRepository.save(save);
	}

	@Override
	public MasterUserEntity findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public List<MasterUserEntity> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Page<MasterUserEntity> findAll(Integer page, Integer pageSize) {
		Pageable paging = PageRequest.of(page, pageSize);
		return userRepository.findAllByRoleIdOrRoleId(paging, 1l, 2l);
	}

	@Override
	public List<SysMenuHeader> getlistMenu(Long role) {
		return adminMenuRepository.findAllByAdminRole(role);
	}

}
