package com.parking.repository.building;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.parking.entity.building.MasterBuildingEntity;

public interface MasterBuildingRepository extends JpaRepository<MasterBuildingEntity, Long> {
	Page<MasterBuildingEntity> findAllByStatus(Pageable pageable, Long status);

	List<MasterBuildingEntity> findAllByStatus(Long status);

	List<MasterBuildingEntity> findAllByAdminId(Long id);

	@Query("FROM MasterBuildingEntity WHERE adminId = null AND status = 1")
	List<MasterBuildingEntity> getAllBuildingAdminNull();
}
