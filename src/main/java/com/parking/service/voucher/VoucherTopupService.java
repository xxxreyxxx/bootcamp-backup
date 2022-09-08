package com.parking.service.voucher;

import java.util.List;

import org.springframework.data.domain.Page;

import com.parking.entity.voucher.VoucherTopupEntity;

public interface VoucherTopupService {
	VoucherTopupEntity createVoucherTopup(VoucherTopupEntity voucher);

	VoucherTopupEntity updateVoucherTopup(VoucherTopupEntity voucher);

	VoucherTopupEntity deleteVoucherTopup(Long id);

	VoucherTopupEntity findById(Long id);

	List<VoucherTopupEntity> getList();

	Page<VoucherTopupEntity> getListPage(Integer page,Integer pageSize);
}
