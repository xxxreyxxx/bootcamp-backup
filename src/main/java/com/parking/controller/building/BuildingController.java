package com.parking.controller.building;

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

import com.parking.dto.building.BuildingDto;
import com.parking.entity.building.MasterBuildingEntity;
import com.parking.service.building.impl.BuildingServiceImpl;

@RestController
public class BuildingController {
	@Autowired
	private BuildingServiceImpl buildingServiceImpl;
	
	@Autowired
    private ModelMapper modelMapper;
	
	@PostMapping("/building")
	public ResponseEntity<MasterBuildingEntity> createBuilding(@RequestBody BuildingDto buildingDto) {
		try {
			MasterBuildingEntity building = modelMapper.map(buildingDto, MasterBuildingEntity.class);
			return new ResponseEntity<>(buildingServiceImpl.saveBuilding(building), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/building")
	public ResponseEntity<MasterBuildingEntity> updateBuilding(@RequestBody BuildingDto buildingDto) {
		try {
			MasterBuildingEntity building = modelMapper.map(buildingDto, MasterBuildingEntity.class);
			return new ResponseEntity<>(buildingServiceImpl.updateBuilding(building), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/building")
	public ResponseEntity<MasterBuildingEntity> findById(@RequestParam("id") Long id) {
		try {
			return new ResponseEntity<>(buildingServiceImpl.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/building/admin")
	public ResponseEntity<List<MasterBuildingEntity>> findByAdminId(@RequestParam("id") Long id) {
		try {
			return new ResponseEntity<>(buildingServiceImpl.findByAdminId(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/buildings")
	public ResponseEntity<Page<MasterBuildingEntity>> findAllBuilding(@RequestParam("page") Integer page,
			@RequestParam("pageSize") Integer pageSize) {
		try {
			return new ResponseEntity<>(buildingServiceImpl.findAll(page, pageSize), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/buildingss")
	public ResponseEntity<List<MasterBuildingEntity>> findAll() {
		try {
			return new ResponseEntity<>(buildingServiceImpl.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/buildings/set-admin")
	public ResponseEntity<List<MasterBuildingEntity>> getAllAdminNull() {
		try {
			return new ResponseEntity<>(buildingServiceImpl.getAllBuildingAdminNull(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/building")
	public ResponseEntity<MasterBuildingEntity> deleteBuilding(@RequestParam Long id) {
		try {
			return new ResponseEntity<>(buildingServiceImpl.deleteBuilding(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
