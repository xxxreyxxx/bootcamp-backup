package com.parking.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parking.entity.user.MasterUserInfoEntity;

public interface MasterUserInfoRepository extends JpaRepository<MasterUserInfoEntity, Long> {
	MasterUserInfoEntity findByPhoneNumber(String phoneNumber);
}
