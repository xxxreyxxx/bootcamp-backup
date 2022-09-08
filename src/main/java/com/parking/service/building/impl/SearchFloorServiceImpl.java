package com.parking.service.building.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.parking.entity.building.MasterFloorEntity;
import com.parking.repository.building.search.SearchFloorRepository;
import com.parking.service.building.search.SearchFloorService;

@Service
public class SearchFloorServiceImpl implements SearchFloorService {
	@Autowired
	SearchFloorRepository searchFloorRepository;

	@Override
	public Page<MasterFloorEntity> searchAllByFloorName(String floorName, Integer page, Integer pageSize,
			String orderBy, String sort, Long adminId) {
		Pageable paging = sortBy(page, pageSize, orderBy, sort);

		if (adminId != null) {
			return searchFloorRepository.searchAllByFloorName(paging, floorName, adminId);
		} else {
			return searchFloorRepository.searchAllByFloorName(paging, floorName);
		}
	}

	@Override
	public Page<MasterFloorEntity> searchAllByBuildingName(String buildingName, Integer page, Integer pageSize,
			String orderBy, String sort, Long adminId) {
		Pageable paging = sortBy(page, pageSize, orderBy, sort);

		if (adminId != null) {
			return searchFloorRepository.searchAllByBuildingName(paging, buildingName, adminId);
		} else {
			return searchFloorRepository.searchAllByBuildingName(paging, buildingName);
		}
	}

	@Override
	public Page<MasterFloorEntity> sortAll(Integer page, Integer pageSize, String orderBy, String sort, Long adminId) {
		Pageable paging = sortBy(page, pageSize, orderBy, sort);

		if (adminId != null) {
			return searchFloorRepository.getAll(paging, adminId);
		} else {
			return searchFloorRepository.getAll(paging);
		}
	}

	private String orderBy(String orderBy) {
		switch (orderBy) {
		case "floor":
			return "floorName";
		case "building":
			return "building.buildingName";
		default:
			return "floorName";
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
