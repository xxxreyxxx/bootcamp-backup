package com.parking.service.transaction;

import java.util.List;

import org.springframework.data.domain.Page;

import com.parking.dto.transaction.CheckInOutDto;
import com.parking.entity.transaction.TransactionDetail;

public interface TransactionDetailService {
	TransactionDetail checkIn(CheckInOutDto checkIn);

	TransactionDetail checkOut(CheckInOutDto checkIn);

	TransactionDetail refresh(Long id);

	TransactionDetail findById(Long id);

	List<TransactionDetail> findAll();

	Page<TransactionDetail> findAllPage(Integer page, Integer pageSize);

	List<TransactionDetail> getListCheckIn(Long userInfoId);

	List<TransactionDetail> getListCheckOut(Long userInfoId);

	Page<TransactionDetail> findAllByVehicleType(Integer page, Integer pageSize, Long id);

	Page<TransactionDetail> findAllByVehicleType(Integer page, Integer pageSize, Long vehicleType, Long adminId);
}
