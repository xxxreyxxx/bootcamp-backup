package com.parking.repository.voucher;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.parking.entity.voucher.MasterVoucherEntity;

public interface VoucherRepository extends JpaRepository<MasterVoucherEntity, Long> {
	MasterVoucherEntity findByVoucherCodeAndStatus(String voucherCode, Long status);
	Page<MasterVoucherEntity> findAllByStatus(Pageable paging, Long status);
	List<MasterVoucherEntity> findAllByStatus(Long status);
}
