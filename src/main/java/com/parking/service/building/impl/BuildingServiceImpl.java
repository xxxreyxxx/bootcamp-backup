package com.parking.service.building.impl;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.parking.entity.building.MasterBuildingEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.parking.repository.building.MasterBuildingRepository;
import com.parking.service.building.BuildingService;

@Service
public class BuildingServiceImpl implements BuildingService {
	private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	private Random random = new Random();

	@Autowired
	MasterBuildingRepository buildingRepository;

	@Override
	public MasterBuildingEntity saveBuilding(MasterBuildingEntity building) {
		String barcode = bCryptPasswordEncoder.encode(String.valueOf(random.nextDouble()));

		building.setStatus(1l);
		building.setBarcode(barcode);
		return buildingRepository.save(building);
	}

	@Override
	public MasterBuildingEntity updateBuilding(MasterBuildingEntity building) {
		MasterBuildingEntity save = findById(building.getId());
		save.setBuildingName(building.getBuildingName());
		save.setLatitude(building.getLatitude());
		save.setLongitude(building.getLongitude());
		save.setLocation(building.getLocation());
		save.setMinPrice(building.getMinPrice());
		save.setMaxPrice(building.getMaxPrice());
		return buildingRepository.save(save);
	}

	@Override
	public MasterBuildingEntity deleteBuilding(Long id) {
		MasterBuildingEntity building = findById(id);
		building.setStatus(0l);
		return buildingRepository.save(building);
	}

	@Override
	public MasterBuildingEntity findById(Long id) {
		Optional<MasterBuildingEntity> optional = buildingRepository.findById(id);
		MasterBuildingEntity building = new MasterBuildingEntity();
		if (optional.isPresent()) {
			building = optional.get();
		}
		return building;
	}

	@Override
	public List<MasterBuildingEntity> findByAdminId(Long id) {
		return buildingRepository.findAllByAdminId(id);
	}

	@Override
	public List<MasterBuildingEntity> findAll() {
		return buildingRepository.findAllByStatus(1l);
	}

	@Override
	public Page<MasterBuildingEntity> findAll(Integer page, Integer pageSize) {
		Pageable paging = PageRequest.of(page, pageSize);
		return buildingRepository.findAllByStatus(paging, 1l);
	}

	@Override
	public List<MasterBuildingEntity> getAllBuildingAdminNull() {
		return buildingRepository.getAllBuildingAdminNull();
	}

}
