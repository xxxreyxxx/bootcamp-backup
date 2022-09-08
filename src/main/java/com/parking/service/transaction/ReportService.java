package com.parking.service.transaction;

import java.sql.Date;
import java.util.List;

import com.parking.entity.transaction.TransactionDetail;

public interface ReportService {
	public byte[] doGetReport(List<TransactionDetail> transactionDetail);

	List<TransactionDetail> allReportToday();

	List<TransactionDetail> allReportBetween(Date start, Date end);

	List<TransactionDetail> allReportTodayType(Long type);

	List<TransactionDetail> allReportBetweenType(Date start, Date end, Long type);
	
	List<TransactionDetail> allReportTodayTypeAndAdminId(Long type, Long adminId);

	List<TransactionDetail> allReportBetweenTypeAndAdminId(Date start, Date end, Long type, Long adminId);
}
