package com.parking.service.building;

import java.util.List;

import org.springframework.data.domain.Page;

import com.parking.dto.building.FloorDto;
import com.parking.entity.building.MasterFloorEntity;

public interface FloorService {
	MasterFloorEntity saveFloor(FloorDto floorDto);

	MasterFloorEntity deleteBuilding(Long id);

	MasterFloorEntity updateFloor(FloorDto floorDto);

	MasterFloorEntity findById(Long id);

	List<MasterFloorEntity> findAll();

	List<MasterFloorEntity> findAllByBuildingId(Long id);

	Page<MasterFloorEntity> findAll(Integer page, Integer pageSize);
	
	Page<MasterFloorEntity> findAllByAdminId(Integer page, Integer pageSize, Long adminId);
}
