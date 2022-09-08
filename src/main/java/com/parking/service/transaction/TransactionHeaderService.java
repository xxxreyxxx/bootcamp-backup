package com.parking.service.transaction;

import java.util.List;

import org.springframework.data.domain.Page;

import com.parking.dto.transaction.BookingDto;
import com.parking.entity.transaction.TransactionHeader;

public interface TransactionHeaderService {
	TransactionHeader createTrHeader(BookingDto booking);

	TransactionHeader findById(Long id);

	List<TransactionHeader> findAll();
	
	List<TransactionHeader> getListBooking(Long userInfoId);

	Page<TransactionHeader> findAllPage(Integer page, Integer pageSize);
}
