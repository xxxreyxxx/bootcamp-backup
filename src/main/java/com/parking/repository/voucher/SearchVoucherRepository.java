package com.parking.repository.voucher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.parking.entity.voucher.MasterVoucherEntity;

public interface SearchVoucherRepository extends JpaRepository<MasterVoucherEntity, Long>{
	@Query("FROM MasterVoucherEntity v WHERE voucherCode LIKE :voucherCode% "
			+ "AND status = 1")
	Page<MasterVoucherEntity> searchByVoucherCode(Pageable paging, String voucherCode);
	
	@Query(value = "SELECT * FROM master_voucher v WHERE cast(discount as char) LIKE :discount% "
			+ "AND status = 1", nativeQuery = true)
	Page<MasterVoucherEntity> searchByDiscount(Pageable paging, @Param("discount") String discount);
	
	@Query("FROM MasterVoucherEntity v WHERE status = 1")
	Page<MasterVoucherEntity> searchAll(Pageable paging);
}
