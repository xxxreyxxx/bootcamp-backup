package com.parking.repository.building;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.parking.entity.building.MasterFloorEntity;

public interface MasterFloorRepository extends JpaRepository<MasterFloorEntity, Long> {
	List<MasterFloorEntity> findAllByBuildingIdAndStatus(Long id, Long status);
	List<MasterFloorEntity> findAllByStatusAndBuildingStatus(Long statusFloor, Long statusBuilding);
	Page<MasterFloorEntity> findAllByStatusAndBuildingStatus(Pageable paging, Long statusFloor, Long statusBuilding);
	Page<MasterFloorEntity> findAllByStatusAndBuildingStatusAndBuildingAdminId(Pageable paging, Long statusFloor, Long statusBuilding, Long adminId);
}
