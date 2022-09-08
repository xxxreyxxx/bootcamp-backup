package com.parking.repository.transaction;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.parking.entity.transaction.TransactionDetail;

public interface ReportRepository extends JpaRepository<TransactionDetail, Long> {

	@Query("FROM TransactionDetail "
			+ "WHERE DATE(enterDate) = CURDATE() "
			+ "AND status = 0 "
			+ "ORDER BY enterDate desc")
	List<TransactionDetail> allReportToday();
	
	@Query("FROM TransactionDetail "
			+ "WHERE DATE(enterDate) "
			+ "BETWEEN :startDate AND :endDate "
			+ "AND status = 0 ORDER BY enterDate desc")
	List<TransactionDetail> allReportBetween(@Param("startDate") Date start, @Param("endDate") Date end);

	@Query("FROM TransactionDetail "
			+ "WHERE DATE(enterDate) = CURDATE() "
			+ "AND status = 0 "
			+ "AND transactionHeader.slot.slotType = :type "
			+ "ORDER BY enterDate desc")
	List<TransactionDetail> allReportTodayType(@Param("type") Long type);

	@Query("FROM TransactionDetail "
			+ "WHERE DATE(enterDate) "
			+ "BETWEEN :startDate AND :endDate "
			+ "AND status = 0 "
			+ "AND transactionHeader.slot.slotType = :type "
			+ "ORDER BY enterDate desc")
	List<TransactionDetail> allReportBetweenType(@Param("startDate") Date start, @Param("endDate") Date end,
			@Param("type") Long type);
	
	@Query("FROM TransactionDetail "
			+ "WHERE DATE(enterDate) = CURDATE() "
			+ "AND status = 0 "
			+ "AND transactionHeader.slot.slotType = :type "
			+ "AND transactionHeader.slot.floor.building.adminId = :adminId "
			+ "ORDER BY enterDate desc")
	List<TransactionDetail> allReportTodayTypeAndAdminId(@Param("type") Long type, @Param("adminId") Long adminId);

	@Query("FROM TransactionDetail "
			+ "WHERE DATE(enterDate) "
			+ "BETWEEN :startDate AND :endDate "
			+ "AND status = 0 "
			+ "AND transactionHeader.slot.slotType = :type "
			+ "AND transactionHeader.slot.floor.building.adminId = :adminId "
			+ "ORDER BY enterDate desc")
	List<TransactionDetail> allReportBetweenTypeAndAdminId(@Param("startDate") Date start, @Param("endDate") Date end,
			@Param("type") Long type, @Param("adminId") Long adminId);

}
