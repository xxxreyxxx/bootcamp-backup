package com.parking.repository.building;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.parking.entity.building.SlotEntity;

public interface SlotRepository extends JpaRepository<SlotEntity, Long> {
	List<SlotEntity> findAllByStatusAndFloorStatusAndFloorBuildingStatus(Long statusSlot, Long statusFloor,
			Long statusBuilding);

	Page<SlotEntity> findAllByStatusAndFloorStatusAndFloorBuildingStatus(Pageable paging, Long statusSlot,
			Long statusFloor, Long statusBuilding);

	Page<SlotEntity> findAllByStatusAndFloorStatusAndFloorBuildingStatusAndSlotType(Pageable paging, Long statusSlot,
			Long statusFloor, Long statusBuilding, Long slotType);

	Page<SlotEntity> findAllByStatusAndFloorStatusAndFloorBuildingStatusAndSlotTypeAndFloorBuildingAdminId(
			Pageable paging, Long statusSlot, Long statusFloor, Long statusBuilding, Long slotType, Long adminId);

	List<SlotEntity> findAllByStatusAndFloorStatusAndFloorBuildingStatusAndFloorBuildingIdAndSlotType(Long statusSlot,
			Long statusFloor, Long statusBuilding, Long idBuilding, Long slotType);

	List<SlotEntity> findAllByFloorStatusAndFloorBuildingStatusAndFloorBuildingIdAndSlotType(Long statusFloor,
			Long statusBuilding, Long idBuilding, Long slotType);
}
