package com.parking.service.building.search;

import org.springframework.data.domain.Page;

import com.parking.entity.building.MasterBuildingEntity;

public interface SearchBuildingService {
	Page<MasterBuildingEntity> searchAllByBuildingName(String buildingName, Integer page, Integer pageSize, String orderBy,
			String sort);

	Page<MasterBuildingEntity> searchAllByLocation(String location, Integer page, Integer pageSize, String orderBy,
			String sort);
	
	Page<MasterBuildingEntity> sortAll(Integer page, Integer pageSize, String orderBy,
			String sort);
}
