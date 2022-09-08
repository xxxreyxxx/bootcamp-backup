package com.parking.service.transaction;

import java.sql.Date;

import org.springframework.data.domain.Page;

import com.parking.entity.transaction.TransactionDetail;

public interface SearchTrxService {
	Page<TransactionDetail> searchAllByBuildingName(String buildingName, Integer page, Integer pageSize, String orderBy,
			String sort, Long slotType, Long adminId);

	Page<TransactionDetail> searchAllByLisencePlate(String licensePlate, Integer page, Integer pageSize, String orderBy,
			String sort, Long slotType, Long adminId);

	Page<TransactionDetail> searchAllByBeteenDate(Date startDate, Date endDate, Integer page, Integer pageSize,
			String orderBy, String sort, Long slotType, Long adminId);

	Page<TransactionDetail> sortAllTrx(Integer page, Integer pageSize, String orderBy, String sort, Long slotType,
			Long adminId);
}
