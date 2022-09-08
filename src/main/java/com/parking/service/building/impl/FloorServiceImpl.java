package com.parking.service.building.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.parking.dto.building.FloorDto;
import com.parking.entity.building.MasterBuildingEntity;
import com.parking.entity.building.MasterFloorEntity;
import com.parking.repository.building.MasterBuildingRepository;
import com.parking.repository.building.MasterFloorRepository;
import com.parking.service.building.FloorService;

@Service
public class FloorServiceImpl implements FloorService {

	@Autowired
	MasterFloorRepository floorRespository;

	@Autowired
	MasterBuildingRepository buildingRespository;

	@Override
	public MasterFloorEntity saveFloor(FloorDto floorDto) {
		Optional<MasterBuildingEntity> optional = buildingRespository.findById(floorDto.getMasterBuildingId());
		MasterBuildingEntity building = new MasterBuildingEntity();

		if (optional.isPresent()) {
			building = optional.get();
		}
		
		MasterFloorEntity floor = new MasterFloorEntity();
		floor.setFloorName(floorDto.getFloorName());
		floor.setBuilding(building);
		floor.setStatus(1l);
		return floorRespository.save(floor);
	}

	@Override
	public MasterFloorEntity updateFloor(FloorDto floorDto) {
		Optional<MasterBuildingEntity> optional = buildingRespository.findById(floorDto.getMasterBuildingId());

		MasterBuildingEntity building = new MasterBuildingEntity();

		if (optional.isPresent()) {
			building = optional.get();
		}
		
		MasterFloorEntity floor = new MasterFloorEntity();
		floor.setId(floorDto.getId());
		floor.setFloorName(floorDto.getFloorName());
		floor.setBuilding(building);
		floor.setStatus(1l);
		return floorRespository.save(floor);
	}

	@Override
	public MasterFloorEntity findById(Long id) {
		Optional<MasterFloorEntity> optional = floorRespository.findById(id);
		MasterFloorEntity floor = new MasterFloorEntity();

		if (optional.isPresent()) {
			floor = optional.get();
		}

		return floor;
	}

	@Override
	public List<MasterFloorEntity> findAll() {
		return floorRespository.findAllByStatusAndBuildingStatus(1l, 1l);
	}

	@Override
	public List<MasterFloorEntity> findAllByBuildingId(Long id) {
		return floorRespository.findAllByBuildingIdAndStatus(id, 1l);
	}

	@Override
	public Page<MasterFloorEntity> findAll(Integer page, Integer pageSize) {
		Pageable paging = PageRequest.of(page, pageSize);
		return floorRespository.findAllByStatusAndBuildingStatus(paging, 1l, 1l);
	}

	@Override
	public Page<MasterFloorEntity> findAllByAdminId(Integer page, Integer pageSize, Long adminId) {
		Pageable paging = PageRequest.of(page, pageSize);
		return floorRespository.findAllByStatusAndBuildingStatusAndBuildingAdminId(paging, 1l, 1l, adminId);
	}

	@Override
	public MasterFloorEntity deleteBuilding(Long id) {
		MasterFloorEntity floor = findById(id);
		floor.setStatus(0l);
		return floorRespository.save(floor);
	}

}
