package com.parking.repository.building.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.parking.entity.building.MasterFloorEntity;

public interface SearchFloorRepository extends JpaRepository<MasterFloorEntity, Long> {
	@Query("FROM MasterFloorEntity b WHERE floorName LIKE :floorName% "
			+ "AND status = 1")
	Page<MasterFloorEntity> searchAllByFloorName(Pageable paging, String floorName);
	
	@Query("FROM MasterFloorEntity b WHERE floorName LIKE :floorName% "
			+ "AND building.adminId = :adminId "
			+ "AND status = 1")
	Page<MasterFloorEntity> searchAllByFloorName(Pageable paging, String floorName, Long adminId);
	

	@Query("FROM MasterFloorEntity b WHERE building.buildingName LIKE :buildingName% "
			+ "AND status = 1")
	Page<MasterFloorEntity> searchAllByBuildingName(Pageable paging, String buildingName);
	
	@Query("FROM MasterFloorEntity b WHERE building.buildingName LIKE :buildingName% "
			+ "AND building.adminId = :adminId "
			+ "AND status = 1")
	Page<MasterFloorEntity> searchAllByBuildingName(Pageable paging, String buildingName, Long adminId);
	
	@Query("FROM MasterFloorEntity b WHERE status = 1")
	Page<MasterFloorEntity> getAll(Pageable paging);
	
	@Query("FROM MasterFloorEntity b WHERE building.adminId = :adminId "
			+ "AND status = 1")
	Page<MasterFloorEntity> getAll(Pageable paging, Long adminId);
}
