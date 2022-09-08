package com.parking.dto.transaction;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookingDto {
	private Long vehicleId;
	private Long buildingId;
}
