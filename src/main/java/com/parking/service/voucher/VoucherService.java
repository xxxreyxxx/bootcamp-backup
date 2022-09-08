package com.parking.service.voucher;

import java.util.List;

import org.springframework.data.domain.Page;

import com.parking.dto.voucher.VoucherDto;
import com.parking.entity.voucher.MasterVoucherEntity;

public interface VoucherService {
	MasterVoucherEntity createVoucher(VoucherDto voucher);

	MasterVoucherEntity updateVoucher(VoucherDto voucher);

	MasterVoucherEntity deleteVoucher(Long id);

	MasterVoucherEntity findById(Long id);
	
	MasterVoucherEntity findByVoucherCodeAndStatus(String voucherCode);

	List<MasterVoucherEntity> findAll();

	Page<MasterVoucherEntity> findAll(Integer page, Integer pageSize);
}
