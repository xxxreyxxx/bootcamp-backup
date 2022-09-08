package com.parking.service.building;

import java.util.List;

import org.springframework.data.domain.Page;

import com.parking.dto.building.SlotDto;
import com.parking.entity.building.SlotEntity;

public interface SlotService {
	SlotEntity saveSlot(SlotDto slot);

	SlotEntity deleteBuilding(Long id);

	SlotEntity updateSlot(SlotDto building);

	SlotEntity findById(Long id);

	List<SlotEntity> findAll();

	Page<SlotEntity> findAll(Integer page, Integer pageSize);

	Page<SlotEntity> findAllByTypeSlot(Integer page, Integer pageSize, Long slotType);

	Page<SlotEntity> findAllByTypeSlotAndAdminId(Integer page, Integer pageSize, Long slotType, Long adminId);
}
