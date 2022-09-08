package com.parking.controller.user;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.parking.dto.user.VehicleDto;
import com.parking.entity.user.MasterVehicleEntity;
import com.parking.service.user.impl.VehicleServiceImpl;

@RestController
public class VehicleController {
	@Autowired
	private VehicleServiceImpl vehicleServiceImpl;

	@Autowired
	private ModelMapper modelMapper;

	@PostMapping("/vehicle")
	public ResponseEntity<MasterVehicleEntity> createVehicle(@RequestBody VehicleDto vehicleDto) {
		try {
			MasterVehicleEntity vehicle = modelMapper.map(vehicleDto, MasterVehicleEntity.class);
			return new ResponseEntity<>(vehicleServiceImpl.saveVehicle(vehicle), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/vehicle")
	public ResponseEntity<MasterVehicleEntity> updateVehicle(@RequestBody VehicleDto vehicleDto) {
		try {
			MasterVehicleEntity vehicle = modelMapper.map(vehicleDto, MasterVehicleEntity.class);
			return new ResponseEntity<>(vehicleServiceImpl.updateVehicle(vehicle), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/vehicle")
	public ResponseEntity<MasterVehicleEntity> findById(@RequestParam("id") Long id) {
		try {
			return new ResponseEntity<>(vehicleServiceImpl.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/vehicles")
	public ResponseEntity<Page<MasterVehicleEntity>> findAllVehicle(@RequestParam("page") Integer page,
			@RequestParam("pageSize") Integer pageSize) {
		try {
			return new ResponseEntity<>(vehicleServiceImpl.findAll(page, pageSize), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/user-vehicles")
	public ResponseEntity<List<MasterVehicleEntity>> findAllUserVehicle(@RequestParam("id") Long id) {
		try {
			return new ResponseEntity<>(vehicleServiceImpl.findAllByUserInfoId(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/vehicle")
	public ResponseEntity<MasterVehicleEntity> deleteVehicle(@RequestParam Long id) {
		try {
			return new ResponseEntity<>(vehicleServiceImpl.deleteVehicle(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
