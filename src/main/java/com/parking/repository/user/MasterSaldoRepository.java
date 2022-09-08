package com.parking.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parking.entity.user.MasterSaldoEntity;

public interface MasterSaldoRepository extends JpaRepository<MasterSaldoEntity, Long> {
	
}
