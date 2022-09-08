package com.parking.repository.building.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.parking.entity.building.SlotEntity;

public interface SearchSlotRepository extends JpaRepository<SlotEntity, Long>{
	@Query("FROM SlotEntity b WHERE slotName LIKE :slotName% "
			+ "AND slotType = :slotType "
			+ "AND status = 1")
	Page<SlotEntity> searchAllBySlotName(Pageable paging, String slotName, Long slotType);
	
	@Query("FROM SlotEntity b WHERE slotName LIKE :slotName% "
			+ "AND slotType = :slotType "
			+ "AND floor.building.adminId = :adminId "
			+ "AND status = 1")
	Page<SlotEntity> searchAllBySlotName(Pageable paging, String slotName, Long slotType, Long adminId);
	
	@Query("FROM SlotEntity b WHERE floor.floorName LIKE :floorName% "
			+ "AND slotType = :slotType "
			+ "AND status = 1")
	Page<SlotEntity> searchAllByFloorName(Pageable paging, String floorName, Long slotType);
	
	@Query("FROM SlotEntity b WHERE floor.floorName LIKE :floorName% "
			+ "AND slotType = :slotType "
			+ "AND floor.building.adminId = :adminId "
			+ "AND status = 1")
	Page<SlotEntity> searchAllByFloorName(Pageable paging, String floorName, Long slotType, Long adminId);
	
	@Query("FROM SlotEntity b WHERE floor.building.buildingName LIKE :buildingName% "
			+ "AND slotType = :slotType "
			+ "AND status = 1")
	Page<SlotEntity> searchAllByBuildingName(Pageable paging, String buildingName, Long slotType);
	
	@Query("FROM SlotEntity b WHERE floor.building.buildingName LIKE :buildingName% "
			+ "AND slotType = :slotType "
			+ "AND floor.building.adminId = :adminId "
			+ "AND status = 1")
	Page<SlotEntity> searchAllByBuildingName(Pageable paging, String buildingName, Long slotType,  Long adminId);
	
	@Query("FROM SlotEntity b WHERE slotType = :slotType "
			+ "AND status = 1")
	Page<SlotEntity> getAll(Pageable paging, Long slotType);
	
	@Query("FROM SlotEntity b WHERE slotType = :slotType "
			+ "AND floor.building.adminId = :adminId "
			+ "AND status = 1")
	Page<SlotEntity> getAll(Pageable paging, Long slotType,  Long adminId);
}
