package com.parking.dto.building;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FloorDto {
	private Long id;
	private String floorName;
	private Long status;
	private Long masterBuildingId;
}
