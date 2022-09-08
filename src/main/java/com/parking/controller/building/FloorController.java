package com.parking.controller.building;

import java.util.List;

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

import com.parking.dto.building.FloorDto;
import com.parking.entity.building.MasterFloorEntity;
import com.parking.service.building.impl.FloorServiceImpl;

@RestController
public class FloorController {
	@Autowired
	FloorServiceImpl floorServiceImpl;

	@PostMapping("/floor")
	public ResponseEntity<MasterFloorEntity> createFloor(@RequestBody FloorDto floorDto) {
		try {
			return new ResponseEntity<>(floorServiceImpl.saveFloor(floorDto), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/floor")
	public ResponseEntity<MasterFloorEntity> updateFlor(@RequestBody FloorDto floor) {
		try {
			return new ResponseEntity<>(floorServiceImpl.updateFloor(floor), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/floor")
	public ResponseEntity<MasterFloorEntity> findById(@RequestParam("id") Long id) {
		try {
			return new ResponseEntity<>(floorServiceImpl.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/floors")
	public ResponseEntity<Page<MasterFloorEntity>> findAllBuilding(@RequestParam("page") Integer page,
			@RequestParam("pageSize") Integer pageSize) {
		try {
			return new ResponseEntity<>(floorServiceImpl.findAll(page, pageSize), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/floors/admin")
	public ResponseEntity<Page<MasterFloorEntity>> findAllBuildingByAdminId(@RequestParam("page") Integer page,
			@RequestParam("pageSize") Integer pageSize, @RequestParam("id") Long adminId) {
		try {
			return new ResponseEntity<>(floorServiceImpl.findAllByAdminId(page, pageSize, adminId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/floorss")
	public ResponseEntity<List<MasterFloorEntity>> findAll() {
		try {
			return new ResponseEntity<>(floorServiceImpl.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	

	@GetMapping("/floors-by-building")
	public ResponseEntity<List<MasterFloorEntity>> findAllByBuildingId(@RequestParam("id") Long id) {
		try {
			return new ResponseEntity<>(floorServiceImpl.findAllByBuildingId(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/floor")
	public ResponseEntity<MasterFloorEntity> deleteBuilding(@RequestParam Long id) {
		try {
			return new ResponseEntity<>(floorServiceImpl.deleteBuilding(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
