package com.parking.service.user.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.parking.entity.user.MasterVehicleEntity;
import com.parking.repository.user.MasterUserInfoRepository;
import com.parking.repository.user.MasterVehicleRepository;
import com.parking.service.user.VehicleService;

@Service
public class VehicleServiceImpl implements VehicleService {
	@Autowired
	MasterVehicleRepository vehicleRepository;

	@Autowired
	MasterUserInfoRepository userInfoRepository;

	@Override
	public MasterVehicleEntity saveVehicle(MasterVehicleEntity vehicle) {
		vehicle.setStatus(1l);
		return vehicleRepository.save(vehicle);
	}

	@Override
	public MasterVehicleEntity updateVehicle(MasterVehicleEntity vehicle) {
		return vehicleRepository.save(vehicle);
	}

	@Override
	public MasterVehicleEntity deleteVehicle(Long id) {
		MasterVehicleEntity vehicle = findById(id);
		vehicle.setStatus(0l);
		return vehicleRepository.save(vehicle);
	}

	@Override
	public MasterVehicleEntity findById(Long id) {
		Optional<MasterVehicleEntity> optional = vehicleRepository.findById(id);
		MasterVehicleEntity vehicle = new MasterVehicleEntity();

		if (optional.isPresent()) {
			vehicle = optional.get();
		}
		return vehicle;
	}

	@Override
	public List<MasterVehicleEntity> findAll() {
		return vehicleRepository.findAll();
	}

	@Override
	public Page<MasterVehicleEntity> findAll(Integer page, Integer pageSize) {
		Pageable paging = PageRequest.of(page, pageSize);
		return vehicleRepository.findAll(paging);
	}

	@Override
	public List<MasterVehicleEntity> findAllByUserInfoId(Long userInfoId) {
		return vehicleRepository.findAllByMasterUserInfoIdAndStatus(userInfoId, 1l);
	}

}
