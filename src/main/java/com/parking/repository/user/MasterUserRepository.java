package com.parking.repository.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.parking.entity.user.MasterUserEntity;

public interface MasterUserRepository extends JpaRepository<MasterUserEntity, Long> {
	@Query("FROM MasterUserEntity WHERE id = :id")
	MasterUserEntity findByUserId(@Param("id") Long id);

	MasterUserEntity findByEmail(String email);

	MasterUserEntity findByUserInfoId(Long id);

	MasterUserEntity findByUserInfoPhoneNumber(String phoneNumber);
	
	Page<MasterUserEntity> findAllByRoleIdOrRoleId(Pageable paging, Long roleId1, Long roleId2);
}
