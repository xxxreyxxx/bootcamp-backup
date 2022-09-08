package com.parking.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.parking.dto.building.SlotDto;
import com.parking.entity.building.MasterFloorEntity;
import com.parking.entity.building.SlotEntity;
import com.parking.repository.building.MasterFloorRepository;
import com.parking.repository.building.SlotRepository;
import com.parking.service.building.SlotService;
import com.parking.service.building.impl.SlotServiceImpl;

@RunWith(SpringRunner.class)
public class SlotServiceImplTest {
	public SlotServiceImplTest() {
	}

	@TestConfiguration
	static class SlotServiceImplTestContextConfiguration {
		@Bean
		public SlotService slotService() {
			return new SlotServiceImpl();
		}
	}

	@Autowired
	private SlotService slotService;

	@MockBean
	private SlotRepository slotReposioty;

	@MockBean
	private MasterFloorRepository floorRepository;

	private List<MasterFloorEntity> floorList = new ArrayList<>();
	private List<SlotEntity> slotList = new ArrayList<>();
//	private Page<SlotEntity> slotPage = null;

	@Before
	public void setUp() {
		for (int i = 0; i < 5; i++) {
			MasterFloorEntity floor = new MasterFloorEntity();
			floor.setId(Long.valueOf(String.valueOf(i)));
			floor.setFloorName("floorName " + i);
			floor.setBuilding(null);
			floor.setStatus(1l);
			floorList.add(floor);
		}

		for (int i = 0; i < 5; i++) {
			SlotEntity slot = new SlotEntity();
			slot.setId(Long.valueOf(String.valueOf(i)));
			slot.setFloor(floorList.get(i));
			slot.setSlotName("slotName " + i);
			slot.setSlotType(1l);
			slot.setStatus(1l);
			slotList.add(slot);
		}
	}

	@Test
	public void saveSlot() {
		Optional<MasterFloorEntity> floor = Optional.of(floorList.get(0));
		Mockito.when(floorRepository.findById(1l)).thenReturn(floor);

		SlotDto slotDto = new SlotDto();
		slotDto.setId(slotList.get(0).getId());
		slotDto.setFloorId(1l);
		slotDto.setSlotName(slotList.get(0).getSlotName());
		slotDto.setSlotType(slotList.get(0).getSlotType());
		slotDto.setStatus(1l);

		SlotEntity save = new SlotEntity();
		save.setId(slotDto.getId());
		save.setSlotName(slotDto.getSlotName());
		save.setSlotType(slotDto.getSlotType());
		save.setFloor(floor.get());
		save.setStatus(1l);
		
		Mockito.when(slotReposioty.save(save)).thenReturn(slotList.get(0));
		Mockito.when(slotService.saveSlot(slotDto)).thenReturn(slotList.get(0));
	}

	@Test
	public void findAll() {
		Mockito.when(slotService.findAll()).thenReturn(slotList);
		Assert.assertEquals(slotList, slotService.findAll());
	}

	@Test
	public void findById() {
		Optional<SlotEntity> slot = Optional.of(slotList.get(0));
		Mockito.when(slotReposioty.findById(1l)).thenReturn(slot);
		Assert.assertEquals(slot.get(), slotService.findById(1l));
	}
}
