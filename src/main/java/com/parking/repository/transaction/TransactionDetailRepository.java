package com.parking.repository.transaction;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.parking.entity.transaction.TransactionDetail;

public interface TransactionDetailRepository extends JpaRepository<TransactionDetail, Long> {

	Page<TransactionDetail> findAllByTransactionHeaderSlotSlotTypeOrderByTransactionHeaderBookingDateDesc(
			Pageable paging, Long id);

	Page<TransactionDetail> findAllByTransactionHeaderSlotSlotTypeAndTransactionHeaderSlotFloorBuildingAdminIdOrderByTransactionHeaderBookingDateDesc(
			Pageable paging, Long vehicleType, Long adminId);

	List<TransactionDetail> findAllByStatusAndTransactionHeaderVehicleMasterUserInfoIdOrderByIdDesc(Long status,
			Long userInfoId);

	TransactionDetail findByStatusAndAndTransactionHeaderVehicleId(Long status, Long vehicleId);
}
