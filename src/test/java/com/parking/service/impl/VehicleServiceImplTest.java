package com.parking.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit4.SpringRunner;

import com.parking.entity.user.MasterVehicleEntity;
import com.parking.repository.user.MasterUserInfoRepository;
import com.parking.repository.user.MasterVehicleRepository;
import com.parking.service.user.VehicleService;
import com.parking.service.user.impl.VehicleServiceImpl;

@RunWith(SpringRunner.class)
public class VehicleServiceImplTest {
	public VehicleServiceImplTest() {
	}

	@TestConfiguration
	static class VehicleServiceImplTestContextConfiguration {
		@Bean
		public  VehicleService vehicleService() {
			return new VehicleServiceImpl();
		}
	}
	
	@Autowired
	VehicleService vehicleService; 

	@MockBean
	MasterVehicleRepository vehicleRepository;

	@MockBean
	MasterUserInfoRepository userInfoRepository;
	
	private List<MasterVehicleEntity> vehicleList = new ArrayList<>();
	private Page<MasterVehicleEntity> vehiclePage = null;
	
	@Before
	public void setUp() {
		for (int i = 0; i < 5; i++) {
			MasterVehicleEntity vehicle = new MasterVehicleEntity();
			vehicle.setId(Long.valueOf(String.valueOf(i)));
			vehicle.setVehicleName("vehicleName " + i);
			vehicle.setVehicleType("vehicleType");
			vehicle.setLicensePlate("licensePlate");
			vehicle.setMasterUserInfoId(Long.valueOf(String.valueOf(i)));
			vehicle.setStatus(1l);
		}
		vehiclePage = new PageImpl<>(vehicleList);
	}
	
	@Test
	public void findAll() {
		Mockito.when(vehicleService.findAll()).thenReturn(vehicleList);
		Assert.assertEquals(vehicleList, vehicleService.findAll());
	}
	
	@Test
	public void findAllPage() {
		Mockito.when(vehicleService.findAll(0, 5)).thenReturn(vehiclePage);
		Assert.assertEquals(vehiclePage, vehicleService.findAll(0, 5));
	}
	
	@Test
	public void findAllByUserInfoId() {
		Mockito.when(vehicleService.findAllByUserInfoId(1l)).thenReturn(vehicleList);
		Assert.assertEquals(vehicleList, vehicleService.findAllByUserInfoId(1l));
	}
}
