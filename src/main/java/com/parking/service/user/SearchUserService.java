package com.parking.service.user;

import org.springframework.data.domain.Page;

import com.parking.entity.user.MasterUserEntity;

public interface SearchUserService {
	Page<MasterUserEntity> searchByEmail(Integer page, Integer pageSize, String email, String orderBy, String sort);

	Page<MasterUserEntity> searchByFullname(Integer page, Integer pageSize, String fullname, String orderBy,
			String sort);

	Page<MasterUserEntity> sortAll(Integer page, Integer pageSize, String orderBy, String sort);
}
