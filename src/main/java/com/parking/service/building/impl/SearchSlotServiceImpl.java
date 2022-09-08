package com.parking.service.building.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.parking.entity.building.SlotEntity;
import com.parking.repository.building.search.SearchSlotRepository;
import com.parking.service.building.search.SearchSlotService;

@Service
public class SearchSlotServiceImpl implements SearchSlotService {
	@Autowired
	SearchSlotRepository searchSlotRepository;

	@Override
	public Page<SlotEntity> searchAllBySlotName(String slotName, Integer page, Integer pageSize, String orderBy,
			String sort, Long slotType, Long adminId) {
		Pageable paging = sortBy(page, pageSize, orderBy, sort);

		if (adminId != null) {
			return searchSlotRepository.searchAllBySlotName(paging, slotName, slotType, adminId);
		} else {
			return searchSlotRepository.searchAllBySlotName(paging, slotName, slotType);
		}
	}

	@Override
	public Page<SlotEntity> searchAllByFloorName(String floorName, Integer page, Integer pageSize, String orderBy,
			Long slotType, String sort, Long adminId) {
		Pageable paging = sortBy(page, pageSize, orderBy, sort);

		if (adminId != null) {
			return searchSlotRepository.searchAllByFloorName(paging, floorName, slotType, adminId);
		} else {
			return searchSlotRepository.searchAllByFloorName(paging, floorName, slotType);
		}
	}

	@Override
	public Page<SlotEntity> searchAllByBuildingName(String buildingName, Integer page, Integer pageSize, Long slotType,
			String orderBy, String sort, Long adminId) {
		Pageable paging = sortBy(page, pageSize, orderBy, sort);

		if (adminId != null) {
			return searchSlotRepository.searchAllByBuildingName(paging, buildingName, slotType, adminId);
		} else {
			return searchSlotRepository.searchAllByBuildingName(paging, buildingName, slotType);
		}
	}

	@Override
	public Page<SlotEntity> sortAll(Integer page, Integer pageSize, String orderBy, String sort, Long slotType,
			Long adminId) {
		Pageable paging = sortBy(page, pageSize, orderBy, sort);

		if (adminId != null) {
			return searchSlotRepository.getAll(paging, slotType, adminId);
		} else {
			return searchSlotRepository.getAll(paging, slotType);
		}
	}

	private String orderBy(String orderBy) {
		switch (orderBy) {
		case "slot":
			return "slotName";
		case "floor":
			return "floor.floorName";
		case "building":
			return "floor.building.buildingName";
		default:
			return "floor.building.buildingName";
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
