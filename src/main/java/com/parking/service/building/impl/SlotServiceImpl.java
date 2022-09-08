package com.parking.service.building.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.parking.dto.building.SlotDto;
import com.parking.entity.building.MasterFloorEntity;
import com.parking.entity.building.SlotEntity;
import com.parking.repository.building.MasterFloorRepository;
import com.parking.repository.building.SlotRepository;
import com.parking.service.building.SlotService;

@Service
public class SlotServiceImpl implements SlotService {
	@Autowired
	SlotRepository slotReposioty;

	@Autowired
	MasterFloorRepository floorRepository;

	@Override
	public SlotEntity saveSlot(SlotDto slot) {
		Optional<MasterFloorEntity> optional = floorRepository.findById(slot.getFloorId());
		MasterFloorEntity floor = new MasterFloorEntity();
		
		if (optional.isPresent()) {
			floor = optional.get();
		}
		
		SlotEntity save = new SlotEntity();
		save.setSlotName(slot.getSlotName());
		save.setSlotType(slot.getSlotType());
		save.setStatus(1l);
		save.setFloor(floor);
		return slotReposioty.save(save);
	}

	@Override
	public SlotEntity updateSlot(SlotDto slotDto) {
		SlotEntity slot = findById(slotDto.getId());
		slot.setSlotName(slotDto.getSlotName());
		slot.setSlotType(slotDto.getSlotType());
		slot.setStatus(1l);
		return slotReposioty.save(slot);
	}

	@Override
	public SlotEntity findById(Long id) {
		Optional<SlotEntity> optional = slotReposioty.findById(id);
		SlotEntity slot = new SlotEntity();
		
		if (optional.isPresent()) {
			slot = optional.get();
		}
		
		return slot;
	}

	@Override
	public List<SlotEntity> findAll() {
		return slotReposioty.findAllByStatusAndFloorStatusAndFloorBuildingStatus(1l, 1l, 1l);
	}

	@Override
	public Page<SlotEntity> findAll(Integer page, Integer pageSize) {
		Pageable paging = PageRequest.of(page, pageSize);
		return slotReposioty.findAllByStatusAndFloorStatusAndFloorBuildingStatus(paging, 1l, 1l, 1l);
	}

	@Override
	public Page<SlotEntity> findAllByTypeSlotAndAdminId(Integer page, Integer pageSize, Long slotType, Long adminId) {
		Pageable paging = PageRequest.of(page, pageSize);
		return slotReposioty.findAllByStatusAndFloorStatusAndFloorBuildingStatusAndSlotTypeAndFloorBuildingAdminId(
				paging, 1l, 1l, 1l, slotType, adminId);
	}

	@Override
	public Page<SlotEntity> findAllByTypeSlot(Integer page, Integer pageSize, Long slotType) {
		Pageable paging = PageRequest.of(page, pageSize);
		return slotReposioty.findAllByStatusAndFloorStatusAndFloorBuildingStatusAndSlotType(paging, 1l, 1l, 1l,
				slotType);
	}

	@Override
	public SlotEntity deleteBuilding(Long id) {
		SlotEntity slot = findById(id);
		slot.setStatus(0l);
		return slotReposioty.save(slot);
	}

}
