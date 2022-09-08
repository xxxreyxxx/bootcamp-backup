package com.parking.service.admin;

import java.util.List;

import org.springframework.data.domain.Page;

import com.parking.dto.user.AdminDto;
import com.parking.entity.sysmenu.SysMenuHeader;
import com.parking.entity.user.MasterUserEntity;

public interface AdminService {

	MasterUserEntity createUser(AdminDto admin);

	MasterUserEntity updateUser(AdminDto admin);
	
	MasterUserEntity updateUserPassword(AdminDto admin);

	MasterUserEntity findByEmail(String email);

	MasterUserEntity findById(Long id);

	List<MasterUserEntity> findAll();

	List<SysMenuHeader> getlistMenu(Long id);

	Page<MasterUserEntity> findAll(Integer page, Integer pageSize);
}
