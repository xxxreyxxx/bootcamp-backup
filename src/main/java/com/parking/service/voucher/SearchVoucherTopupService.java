package com.parking.service.voucher;

import java.sql.Date;

import org.springframework.data.domain.Page;

import com.parking.entity.voucher.VoucherTopupEntity;

public interface SearchVoucherTopupService {
	Page<VoucherTopupEntity> searchByVoucherCode(Integer page, Integer pageSize, String voucherCode, String orderBy,
			String sort);

	Page<VoucherTopupEntity> searchByValue(Integer page, Integer pageSize, String value, String orderBy,
			String sort);
 
	Page<VoucherTopupEntity> searchByExpired(Integer page, Integer pageSize, Date expired, String orderBy, String sort);
	
	Page<VoucherTopupEntity> sortAll(Integer page, Integer pageSize, String orderBy, String sort);
}
