package com.parking.service.transaction.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.parking.dto.transaction.BookingDto;
import com.parking.dto.transaction.NearbyListDto;
import com.parking.entity.building.MasterBuildingEntity;
import com.parking.entity.building.SlotEntity;
import com.parking.entity.transaction.TransactionDetail;
import com.parking.entity.transaction.TransactionHeader;
import com.parking.entity.user.MasterUserEntity;
import com.parking.entity.user.MasterVehicleEntity;
import com.parking.procces.ProccesGet;
import com.parking.repository.building.MasterBuildingRepository;
import com.parking.repository.building.SlotRepository;
import com.parking.repository.transaction.TransactionDetailRepository;
import com.parking.repository.transaction.TransactionHeaderRepository;
import com.parking.repository.user.MasterUserInfoRepository;
import com.parking.repository.user.MasterUserRepository;
import com.parking.repository.user.MasterVehicleRepository;
import com.parking.service.transaction.TransactionHeaderService;

@Service
public class TransactionHeaderServiceImpl implements TransactionHeaderService {

	@Autowired
	MasterBuildingRepository buildingReposiotry;

	@Autowired
	TransactionHeaderRepository headerRepository;

	@Autowired
	MasterUserInfoRepository userInfoRepository;

	@Autowired
	MasterVehicleRepository vehicleRepository;

	@Autowired
	SlotRepository slotRepository;

	@Autowired
	MasterUserRepository userRepository;
	
	@Autowired
	TransactionDetailRepository detailRepository; 

	@Transactional
	@Override
	public TransactionHeader createTrHeader(BookingDto booking) {
		Optional<MasterVehicleEntity> optional = vehicleRepository.findById(booking.getVehicleId());
		MasterVehicleEntity vehicle = new MasterVehicleEntity();
		if (optional.isPresent()) {
			vehicle = optional.get();
		}

		MasterUserEntity user = userRepository.findByUserInfoId(vehicle.getMasterUserInfoId());
		List<SlotEntity> slot;
		BigDecimal saldo;
		BigDecimal maxPrice;

		if (!isBooked(booking.getVehicleId()) && !isCheckIn(booking.getVehicleId())) {
			if (isCar(vehicle.getVehicleType())) {
				slot = slotRepository.findAllByStatusAndFloorStatusAndFloorBuildingStatusAndFloorBuildingIdAndSlotType(
						1l, 1l, 1l, booking.getBuildingId(), 1l);

				saldo = BigDecimal.valueOf(user.getSaldo().getSaldo().doubleValue() * 2);
				maxPrice = BigDecimal.valueOf(slot.get(0).getFloor().getBuilding().getMaxPrice().doubleValue() * 2);
			} else {
				slot = slotRepository.findAllByStatusAndFloorStatusAndFloorBuildingStatusAndFloorBuildingIdAndSlotType(
						1l, 1l, 1l, booking.getBuildingId(), 2l);

				saldo = user.getSaldo().getSaldo();
				maxPrice = slot.get(0).getFloor().getBuilding().getMaxPrice();
			}

			TransactionHeader header = new TransactionHeader();

			if (saldo.doubleValue() > maxPrice.doubleValue()) {
				Date bookingDate = new Date();
				Date expiredDate = new Date(new Date().getTime() + (30 * 60 * 1000));

				header.setBookingDate(bookingDate);
				header.setExpiredBooking(expiredDate);
				header.setVehicle(vehicle);
				header.setSlot(slot.get(0));
				header.setStatus(1l);

				slot.get(0).setStatus(0l);
				slotRepository.save(slot.get(0));
				return headerRepository.save(header);
			} else {
				return null;
			}
		} else {
			return null;
		}

	}

	@Override
	public TransactionHeader findById(Long id) {
		Optional<TransactionHeader> optional = headerRepository.findById(id);
		TransactionHeader header = new TransactionHeader();

		if (optional.isPresent()) {
			header = optional.get();
		}

		return header;
	}

	@Override
	public List<TransactionHeader> findAll() {
		return headerRepository.findAll();
	}

	@Override
	public Page<TransactionHeader> findAllPage(Integer page, Integer pageSize) {
		Pageable paging = PageRequest.of(page, pageSize);
		return headerRepository.findAll(paging);
	}

	public List<NearbyListDto> getNearby(Double lat1, Double lon1) {
		List<MasterBuildingEntity> building = buildingReposiotry.findAllByStatus(1l);
		List<NearbyListDto> result = new ArrayList<>();
		List<SlotEntity> slotMotorcycle;
		List<SlotEntity> slotCar;
		List<SlotEntity> totalSlotCar;
		List<SlotEntity> totalSlotMotorcycle;

		for (MasterBuildingEntity b : building) {
			Double distance = ProccesGet.getDistance(lat1, lon1, Double.valueOf(b.getLatitude()),
					Double.valueOf(b.getLongitude()));
			if (distance <= 5) {
				slotMotorcycle = slotRepository
						.findAllByStatusAndFloorStatusAndFloorBuildingStatusAndFloorBuildingIdAndSlotType(1l, 1l, 1l,
								b.getId(), 2l);
				totalSlotMotorcycle = slotRepository
						.findAllByFloorStatusAndFloorBuildingStatusAndFloorBuildingIdAndSlotType(1l, 1l, b.getId(), 2l);
				slotCar = slotRepository
						.findAllByStatusAndFloorStatusAndFloorBuildingStatusAndFloorBuildingIdAndSlotType(1l, 1l, 1l,
								b.getId(), 1l);
				totalSlotCar = slotRepository
						.findAllByFloorStatusAndFloorBuildingStatusAndFloorBuildingIdAndSlotType(1l, 1l, b.getId(), 1l);
				NearbyListDto n = new NearbyListDto();
				n.setBuildingId(b.getId());
				n.setBuildingName(b.getBuildingName());
				n.setLat(b.getLatitude());
				n.setLon(b.getLongitude());
				n.setSlotMotorcycle(slotMotorcycle.size());
				n.setTotalSlotMotorcycle(totalSlotMotorcycle.size());
				n.setSlotCar(slotCar.size());
				n.setTotalSlotCar(totalSlotCar.size());
				n.setDistance(distance);
				n.setMaxPrice(b.getMaxPrice());
				n.setMinPrice(b.getMinPrice());
				result.add(n);
			}
		}
		Collections.sort(result);

		return result;
	}

	@Override
	public List<TransactionHeader> getListBooking(Long userInfoId) {
		List<TransactionHeader> headers = headerRepository.findAllByStatusAndVehicleMasterUserInfoIdOrderByBookingDateDesc(1l, userInfoId);
		List<TransactionHeader> bookingList = new ArrayList<>();
		Date date = new Date();
		for (TransactionHeader header : headers) {
			if (date.before(header.getExpiredBooking())) {
				bookingList.add(header);
			} else {
				TransactionHeader trHeader = header;
				trHeader.setStatus(0l);
				headerRepository.save(trHeader);

				SlotEntity slot = header.getSlot();
				slot.setStatus(1l);
				slotRepository.save(slot);
			}
		}
		return bookingList;
	}

	private boolean isCar(String vehicleType) {
		return vehicleType.equals("Car");
	}

	private boolean isBooked(Long vehicleId) {
		TransactionHeader header = headerRepository.findByStatusAndVehicleId(1l, vehicleId);
		return header != null;
	}
	
	private boolean isCheckIn(Long vehicleId) {
		TransactionDetail detail = detailRepository.findByStatusAndAndTransactionHeaderVehicleId(1l, vehicleId);
		return detail != null;
	}
}
