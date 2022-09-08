package com.parking.repository.voucher;

import java.sql.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.parking.entity.voucher.VoucherTopupEntity;

public interface SearchVoucherTopupRepository extends JpaRepository<VoucherTopupEntity, Long> {
	@Query("FROM VoucherTopupEntity v WHERE voucherCode LIKE :voucherCode% "
			+ "AND status = 1")
	Page<VoucherTopupEntity> searchByVoucherCode(Pageable paging, String voucherCode);
	
	@Query(value = "SELECT * FROM voucher_topup WHERE cast(value as char) LIKE :value% "
			+ "AND status = 1", nativeQuery = true)
	Page<VoucherTopupEntity> searchByDiscount(Pageable paging, @Param("value") String value);
	
	@Query("FROM VoucherTopupEntity v WHERE expired = :expired "
			+ "AND status = 1")
	Page<VoucherTopupEntity> searchByExpired(Pageable paging, Date expired);
	
	@Query("FROM VoucherTopupEntity v WHERE status = 1")
	Page<VoucherTopupEntity> searchAll(Pageable paging);
}
