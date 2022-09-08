package com.parking.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.parking.entity.user.MasterUserEntity;
import com.parking.repository.user.SearchUserRepository;
import com.parking.service.user.SearchUserService;

@Service
public class SearchUserServiceImpl implements SearchUserService {
	@Autowired
	SearchUserRepository searchUserRepository;

	@Override
	public Page<MasterUserEntity> searchByEmail(Integer page, Integer pageSize, String email, String orderBy,
			String sort) {
		Pageable paging = sortBy(page, pageSize, orderBy, sort);
		return searchUserRepository.searchByEmail(paging, email);
	}

	@Override
	public Page<MasterUserEntity> searchByFullname(Integer page, Integer pageSize, String fullname, String orderBy,
			String sort) {
		Pageable paging = sortBy(page, pageSize, orderBy, sort);
		return searchUserRepository.searchByFullname(paging, fullname);
	}

	@Override
	public Page<MasterUserEntity> sortAll(Integer page, Integer pageSize, String orderBy, String sort) {
		Pageable paging = sortBy(page, pageSize, orderBy, sort);
		return searchUserRepository.findAll(paging);
	}

	private String orderBy(String orderBy) {
		switch (orderBy) {
		case "email":
			return "email";
		case "fullname":
			return "userInfo.fullname";
		case "role":
			return "roleId";
		default:
			return "id";
		}
	}

	private Pageable sortBy(Integer page, Integer pageSize, String orderBy, String sort) {
		if (sort.equals("asc")) {
			return PageRequest.of(page, pageSize, Sort.by(orderBy(orderBy)).ascending());
		} else {
			return PageRequest.of(page, pageSize, Sort.by(orderBy(orderBy)).descending());
		}
	}

}
