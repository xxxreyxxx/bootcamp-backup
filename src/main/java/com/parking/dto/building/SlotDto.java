package com.parking.dto.building;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SlotDto {
	private Long id;
	private String slotName;
	private Long slotType;
	private Long status;
	private Long floorId;
}
