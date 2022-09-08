package com.parking.repository.transaction;

import java.sql.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.parking.entity.transaction.TransactionDetail;

public interface SearchTrxRepository extends JpaRepository<TransactionDetail, Long> {
	@Query("FROM TransactionDetail td "
			+ "WHERE transactionHeader.slot.slotType = :slotType "
			+ "AND transactionHeader.slot.floor.building.buildingName LIKE :buildingName% ")
	Page<TransactionDetail> searchAllByBuildingName(Pageable paging, Long slotType, String buildingName);
	
	@Query("FROM TransactionDetail td "
			+ "WHERE transactionHeader.slot.slotType = :slotType "
			+ "AND transactionHeader.slot.floor.building.buildingName LIKE :buildingName% "
			+ "AND transactionHeader.slot.floor.building.adminId = :adminId ")
	Page<TransactionDetail> searchAllByBuildingName(Pageable paging, Long slotType, String buildingName, Long adminId);
	
	@Query("FROM TransactionDetail td "
			+ "WHERE transactionHeader.slot.slotType = :slotType "
			+ "AND transactionHeader.vehicle.licensePlate LIKE :licensePlate% ")
	Page<TransactionDetail> searchAllByLicensePlate(Pageable paging, Long slotType, String licensePlate);
	
	
	@Query("FROM TransactionDetail td "
			+ "WHERE transactionHeader.slot.slotType = :slotType "
			+ "AND transactionHeader.vehicle.licensePlate LIKE :licensePlate% "
			+ "AND transactionHeader.slot.floor.building.adminId = :adminId ")
	Page<TransactionDetail> searchAllByLicensePlate(Pageable paging, Long slotType, String licensePlate, Long adminId);
	
	@Query("FROM TransactionDetail td "
			+ "WHERE DATE(enterDate) "
			+ "BETWEEN :startDate AND :endDate "
			+ "AND transactionHeader.slot.slotType = :slotType ")
	Page<TransactionDetail> searchAllByBetweenDate(Pageable paging,Date startDate, Date endDate, Long slotType);
	
	@Query("FROM TransactionDetail td "
			+ "WHERE DATE(enterDate) "
			+ "BETWEEN :startDate AND :endDate "
			+ "AND transactionHeader.slot.slotType = :slotType "
			+ "AND transactionHeader.slot.floor.building.adminId = :adminId ")
	Page<TransactionDetail> searchAllByBetweenDate(Pageable paging,Date startDate, Date endDate, Long slotType, Long adminId);
	
	//Sort
	@Query("FROM TransactionDetail td "
			+ "WHERE transactionHeader.slot.slotType = :slotType ")
	Page<TransactionDetail> sortAllTrx(Pageable paging, Long slotType);
	
	@Query("FROM TransactionDetail td "
			+ "WHERE transactionHeader.slot.slotType = :slotType "
			+ "AND transactionHeader.slot.floor.building.adminId = :adminId ")
	Page<TransactionDetail> sortAllTrx(Pageable paging, Long slotType, Long adminId);
}
