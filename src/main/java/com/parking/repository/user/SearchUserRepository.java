package com.parking.repository.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.parking.entity.user.MasterUserEntity;

public interface SearchUserRepository extends JpaRepository<MasterUserEntity, Long> {
	@Query("FROM MasterUserEntity u WHERE email LIKE :email%")
	Page<MasterUserEntity> searchByEmail(Pageable paging, String email);

	@Query("FROM MasterUserEntity u WHERE userInfo.fullname LIKE %:fullname%")
	Page<MasterUserEntity> searchByFullname(Pageable paging, String fullname);
}
