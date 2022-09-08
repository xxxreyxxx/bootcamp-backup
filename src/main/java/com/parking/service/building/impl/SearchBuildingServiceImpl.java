package com.parking.service.building.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.parking.entity.building.MasterBuildingEntity;
import com.parking.repository.building.search.SearchBuildingRepository;
import com.parking.service.building.search.SearchBuildingService;

@Service
public class SearchBuildingServiceImpl implements SearchBuildingService {
	@Autowired
	SearchBuildingRepository searchBuildingRepository;

	@Override
	public Page<MasterBuildingEntity> searchAllByBuildingName(String buildingName, Integer page, Integer pageSize,
			String orderBy, String sort) {
		Pageable paging = sortBy(page, pageSize, orderBy, sort);
		return searchBuildingRepository.searchAllByBuildingName(paging, buildingName);
	}

	@Override
	public Page<MasterBuildingEntity> searchAllByLocation(String location, Integer page, Integer pageSize,
			String orderBy, String sort) {
		Pageable paging = sortBy(page, pageSize, orderBy, sort);
		return searchBuildingRepository.searchAllByLocation(paging, location);
	}

	@Override
	public Page<MasterBuildingEntity> sortAll(Integer page, Integer pageSize, String orderBy, String sort) {
		Pageable paging = sortBy(page, pageSize, orderBy, sort);
		return searchBuildingRepository.getAll(paging);
	}

	private String orderBy(String orderBy) {
		switch (orderBy) {
		case "building":
			return "buildingName";
		case "location":
			return "location";
		case "min":
			return "minPrice";
		case "max":
			return "maxPrice";
		default:
			return "buildingName";
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
