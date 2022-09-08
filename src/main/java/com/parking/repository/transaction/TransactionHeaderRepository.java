package com.parking.repository.transaction;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parking.entity.transaction.TransactionHeader;

public interface TransactionHeaderRepository extends JpaRepository<TransactionHeader, Long> {
	List<TransactionHeader> findAllByStatusAndVehicleMasterUserInfoIdOrderByBookingDateDesc(Long status, Long userInfo);
	TransactionHeader findByStatusAndVehicleId(Long status, Long vehicleId);
}
