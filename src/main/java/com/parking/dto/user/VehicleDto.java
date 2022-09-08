package com.parking.dto.user;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VehicleDto {
	private Long id;
	private String vehicleName;
	private String vehicleType;
	private String licensePlate;
	private Long masterUserInfoId;
	private Long status;
}
