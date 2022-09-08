package com.parking.service.transaction.impl;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.parking.entity.transaction.TransactionDetail;
import com.parking.repository.transaction.SearchTrxRepository;
import com.parking.service.transaction.SearchTrxService;

@Service
public class SearchTrxServiceImpl implements SearchTrxService {
	@Autowired
	SearchTrxRepository searchRepository;

	@Override
	public Page<TransactionDetail> searchAllByBuildingName(String buildingName, Integer page, Integer pageSize,
			String orderBy, String sort, Long slotType, Long adminId) {
		Pageable paging = sortBy(page, pageSize, orderBy, sort);

		if (adminId != null) {
			return searchRepository.searchAllByBuildingName(paging, slotType, buildingName, adminId);
		} else {
			return searchRepository.searchAllByBuildingName(paging, slotType, buildingName);
		}
	}

	@Override
	public Page<TransactionDetail> searchAllByLisencePlate(String licensePlate, Integer page, Integer pageSize,
			String orderBy, String sort, Long slotType, Long adminId) {
		Pageable paging = sortBy(page, pageSize, orderBy, sort);

		if (adminId != null) {
			return searchRepository.searchAllByLicensePlate(paging, slotType, licensePlate, adminId);
		} else {
			return searchRepository.searchAllByLicensePlate(paging, slotType, licensePlate);
		}
	}

	@Override
	public Page<TransactionDetail> searchAllByBeteenDate(Date startDate, Date endDate, Integer page, Integer pageSize,
			String orderBy, String sort, Long slotType, Long adminId) {
		Pageable paging = sortBy(page, pageSize, orderBy, sort);

		if (adminId != null) {
			return searchRepository.searchAllByBetweenDate(paging, startDate, endDate, slotType, adminId);
		} else {
			return searchRepository.searchAllByBetweenDate(paging, startDate, endDate, slotType);
		}
	}

	@Override
	public Page<TransactionDetail> sortAllTrx(Integer page, Integer pageSize, String orderBy, String sort,
			Long slotType, Long adminId) {
		Pageable paging = sortBy(page, pageSize, orderBy, sort);

		if (adminId != null) {
			return searchRepository.sortAllTrx(paging, slotType, adminId);
		} else {
			return searchRepository.sortAllTrx(paging, slotType);
		}
	}

	private String orderBy(String orderBy) {
		switch (orderBy) {
		case "building":
			return "transactionHeader.slot.floor.building.buildingName";
		case "vehicle":
			return "transactionHeader.vehicle.vehicleName";
		case "plate":
			return "transactionHeader.vehicle.licensePlate";
		case "enter":
			return "enterDate";
		case "exit":
			return "exitDate";
		case "total":
			return "totalPrice";
		case "status":
			return "status";
		default:
			return "transactionHeader.slot.floor.building.buildingName";
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
