package com.parking.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.parking.entity.user.MasterVehicleEntity;
import com.parking.repository.user.MasterVehicleRepository;
import com.parking.service.user.SearchVehicleService;

@Service
public class SearchVehicleServiceImpl implements SearchVehicleService {
	@Autowired
	MasterVehicleRepository masterVehicleRepository;

	@Override
	public Page<MasterVehicleEntity> searchByLicensePlate(Integer page, Integer pageSize, String licensePlate,
			String orderBy, String sort) {
		Pageable paging = sortBy(page, pageSize, orderBy, sort);
		return masterVehicleRepository.searchByLicensePlate(paging, licensePlate);
	}

	@Override
	public Page<MasterVehicleEntity> sortAll(Integer page, Integer pageSize, String orderBy, String sort) {
		Pageable paging = sortBy(page, pageSize, orderBy, sort);
		return masterVehicleRepository.findAll(paging);
	}

	private String orderBy(String orderBy) {
		switch (orderBy) {
		case "name":
			return "vehicleName";
		case "type":
			return "vehicleType";
		case "plate":
			return "licensePlate";
		case "status":
			return "status";
		default:
			return "vehicleName";
		}
	}

	private Pageable sortBy(Integer page, Integer pageSize, String orderBy, String sort) {
		if (sort.equals("asc")) {
			return PageRequest.of(page, pageSize, Sort.by(orderBy(orderBy)).ascending());
		} else {
			return PageRequest.of(page, pageSize, Sort.by(orderBy(orderBy)).descending());
		}
	}
}
