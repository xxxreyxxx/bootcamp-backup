package com.parking.repository.user;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.parking.entity.user.MasterVehicleEntity;

public interface MasterVehicleRepository extends JpaRepository<MasterVehicleEntity, Long> {
	List<MasterVehicleEntity> findAllByMasterUserInfoIdAndStatus(Long userInfoId, Long status);

	@Query("FROM MasterVehicleEntity v WHERE licensePlate LIKE :licensePlate%")
	Page<MasterVehicleEntity> searchByLicensePlate(Pageable paging, String licensePlate);
}
