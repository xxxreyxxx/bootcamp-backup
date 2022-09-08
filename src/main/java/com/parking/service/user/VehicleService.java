package com.parking.service.user;

import java.util.List;

import org.springframework.data.domain.Page;

import com.parking.entity.user.MasterVehicleEntity;

public interface VehicleService {
	MasterVehicleEntity saveVehicle(MasterVehicleEntity vehicle);

	MasterVehicleEntity updateVehicle(MasterVehicleEntity vehicle);

	MasterVehicleEntity deleteVehicle(Long id);

	MasterVehicleEntity findById(Long id);

	List<MasterVehicleEntity> findAll();

	List<MasterVehicleEntity> findAllByUserInfoId(Long userInfoId);

	Page<MasterVehicleEntity> findAll(Integer page, Integer pageSize);
}
