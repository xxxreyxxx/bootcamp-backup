package com.parking.controller.transaction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.parking.dto.transaction.BookingDto;
import com.parking.dto.transaction.CheckInOutDto;
import com.parking.dto.transaction.NearbyListDto;
import com.parking.entity.transaction.TransactionDetail;
import com.parking.entity.transaction.TransactionHeader;
import com.parking.service.transaction.impl.TransactionDetailServiceImpl;
import com.parking.service.transaction.impl.TransactionHeaderServiceImpl;


@RestController
public class TransactionController {
	@Autowired
	TransactionDetailServiceImpl detailServiceImpl;

	@Autowired
	TransactionHeaderServiceImpl headerServiceImpl;

	@PostMapping("/booking")
	public ResponseEntity<TransactionHeader> booking(@RequestBody BookingDto booking) {
		try {
			TransactionHeader header = headerServiceImpl.createTrHeader(booking);
			if (header != null) {
				return new ResponseEntity<>(header, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping("/check-in")
	public ResponseEntity<TransactionDetail> checkIn(@RequestBody CheckInOutDto checkDto) {
		try {
			TransactionDetail detail = detailServiceImpl.checkIn(checkDto);
			if (detail != null) {
				return new ResponseEntity<>(detail, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@PutMapping("/check-out")
	public ResponseEntity<TransactionDetail> checkOut(@RequestBody CheckInOutDto checkDto) {
		try {
			TransactionDetail detail = detailServiceImpl.checkOut(checkDto);
			if (detail != null) {
				return new ResponseEntity<>(detail, HttpStatus.OK);

			} else {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/refresh")
	public ResponseEntity<TransactionDetail> refresh(@RequestParam("id") Long id) {
		try {
			return new ResponseEntity<>(detailServiceImpl.refresh(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/transactionss")
	public ResponseEntity<List<TransactionDetail>> getListCheckOut(@RequestParam("id") Long userInfoId) {
		try {
			return new ResponseEntity<>(detailServiceImpl.getListCheckOut(userInfoId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/transactions")
	public ResponseEntity<Page<TransactionDetail>> findAllPage(@RequestParam("page") Integer page,
			@RequestParam("pageSize") Integer pageSize) {
		try {
			return new ResponseEntity<>(detailServiceImpl.findAllPage(page, pageSize), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/transaction")
	public ResponseEntity<TransactionDetail> findById(@RequestParam("id") Long id) {
		try {
			return new ResponseEntity<>(detailServiceImpl.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/transactions/vehicle")
	public ResponseEntity<Page<TransactionDetail>> findAllByVehicleType(@RequestParam("page") Integer page,
			@RequestParam("pageSize") Integer pageSize, @RequestParam("type") Long vehicleType) {
		try {
			return new ResponseEntity<>(detailServiceImpl.findAllByVehicleType(page, pageSize, vehicleType),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/transactions/building")
	public ResponseEntity<Page<TransactionDetail>> findAllByVehicleTypeBuilding(@RequestParam("page") Integer page,
			@RequestParam("pageSize") Integer pageSize, @RequestParam("type") Long vehicleType,
			@RequestParam("id") Long buildingId) {
		try {
			return new ResponseEntity<>(detailServiceImpl.findAllByVehicleType(page, pageSize, vehicleType, buildingId),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/bookings")
	public ResponseEntity<List<TransactionHeader>> findAllBookingUser(@RequestParam("id") Long userInfoId) {
		try {
			return new ResponseEntity<>(headerServiceImpl.getListBooking(userInfoId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/bookings/check-in")
	public ResponseEntity<List<TransactionDetail>> findAllCheckIn(@RequestParam("id") Long userInfoId) {
		try {
			return new ResponseEntity<>(detailServiceImpl.getListCheckIn(userInfoId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/nearby")
	public ResponseEntity<List<NearbyListDto>> getNearbyList(@RequestParam("lat") Double lat,
			@RequestParam("lon") Double lon) {
		try {
			return new ResponseEntity<>(headerServiceImpl.getNearby(lat, lon), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

}
