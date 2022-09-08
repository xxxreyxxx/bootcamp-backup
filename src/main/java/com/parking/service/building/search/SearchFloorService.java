package com.parking.service.building.search;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.parking.entity.building.MasterFloorEntity;

@Service
public interface SearchFloorService {
	Page<MasterFloorEntity> searchAllByFloorName(String floorName, Integer page, Integer pageSize, String orderBy,
			String sort, Long adminId);
	
	Page<MasterFloorEntity> searchAllByBuildingName(String buildingName, Integer page, Integer pageSize, String orderBy,
			String sort, Long adminId);
	
	Page<MasterFloorEntity> sortAll(Integer page, Integer pageSize, String orderBy,
			String sort, Long adminId);
}
