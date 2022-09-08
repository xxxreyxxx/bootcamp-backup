package com.parking.repository.building.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.parking.entity.building.MasterBuildingEntity;

public interface SearchBuildingRepository extends JpaRepository<MasterBuildingEntity, Long> {
	@Query("FROM MasterBuildingEntity b WHERE buildingName LIKE :buildingName% "
			+ "AND status = 1")
	Page<MasterBuildingEntity> searchAllByBuildingName(Pageable paging, String buildingName);

	@Query("FROM MasterBuildingEntity b WHERE location LIKE %:location% "
			+ "AND status = 1")
	Page<MasterBuildingEntity> searchAllByLocation(Pageable paging, String location);
	
	@Query("FROM MasterBuildingEntity b WHERE status = 1")
	Page<MasterBuildingEntity> getAll(Pageable paging);
}
