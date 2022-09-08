package com.parking.service.building.search;

import org.springframework.data.domain.Page;
import com.parking.entity.building.SlotEntity;

public interface SearchSlotService {
	Page<SlotEntity> searchAllBySlotName(String slotName, Integer page, Integer pageSize, String orderBy, String sort,
			Long slotType, Long adminId);

	Page<SlotEntity> searchAllByFloorName(String floorName, Integer page, Integer pageSize, String orderBy,
			Long slotType, String sort, Long adminId);

	Page<SlotEntity> searchAllByBuildingName(String buildingName, Integer page, Integer pageSize, Long slotType,
			String orderBy, String sort, Long adminId);

	Page<SlotEntity> sortAll(Integer page, Integer pageSize, String orderBy, String sort, Long slotType, Long adminId);
}
