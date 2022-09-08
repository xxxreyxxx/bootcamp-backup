package com.parking.service.user;

import org.springframework.data.domain.Page;

import com.parking.entity.user.MasterVehicleEntity;

public interface SearchVehicleService {
	Page<MasterVehicleEntity> searchByLicensePlate(Integer page, Integer pageSize, String licensePlate, String orderBy,
			String sort);

	Page<MasterVehicleEntity> sortAll(Integer page, Integer pageSize, String orderBy, String sort);
}
