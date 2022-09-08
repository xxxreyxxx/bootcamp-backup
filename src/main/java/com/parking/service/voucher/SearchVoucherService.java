package com.parking.service.voucher;

import org.springframework.data.domain.Page;

import com.parking.entity.voucher.MasterVoucherEntity;

public interface SearchVoucherService {
	Page<MasterVoucherEntity> searchByVoucherCode(Integer page, Integer pageSize, String voucherCode, String orderBy,
			String sort);

	Page<MasterVoucherEntity> searchByDiscount(Integer page, Integer pageSize, String discount, String orderBy,
			String sort);

	Page<MasterVoucherEntity> sortAll(Integer page, Integer pageSize, String orderBy, String sort);
}
