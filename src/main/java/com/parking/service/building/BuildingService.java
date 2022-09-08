package com.parking.service.building;

import java.util.List;

import org.springframework.data.domain.Page;

import com.parking.entity.building.MasterBuildingEntity;

public interface BuildingService {
	MasterBuildingEntity saveBuilding(MasterBuildingEntity building);
	
	MasterBuildingEntity updateBuilding(MasterBuildingEntity building);

	MasterBuildingEntity deleteBuilding(Long id);

	MasterBuildingEntity findById(Long id);
	
	List<MasterBuildingEntity> findByAdminId(Long id);

	List<MasterBuildingEntity> findAll();
	
	List<MasterBuildingEntity> getAllBuildingAdminNull();

	Page<MasterBuildingEntity> findAll(Integer page, Integer pageSize);
}
