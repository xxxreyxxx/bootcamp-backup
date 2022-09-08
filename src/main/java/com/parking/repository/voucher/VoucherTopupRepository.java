package com.parking.repository.voucher;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.parking.entity.voucher.VoucherTopupEntity;

public interface VoucherTopupRepository extends JpaRepository<VoucherTopupEntity, Long> {
	VoucherTopupEntity findByVoucherCodeAndStatus(String voucherCode,Long status);
	Page<VoucherTopupEntity> findAllByStatus(Pageable paging,Long status);
}
