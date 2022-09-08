package com.parking.dto.transaction;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NearbyListDto implements Comparable<NearbyListDto> {
	private Long buildingId;
	private String buildingName;
	private String lat;
	private String lon;
	private Double distance;
	private Integer slotCar;
	private Integer totalSlotCar;
	private Integer slotMotorcycle;
	private Integer totalSlotMotorcycle;
	private BigDecimal minPrice;
	private BigDecimal maxPrice;

	@Override
	public int compareTo(NearbyListDto ne) {
		if (getDistance() == null || ne.getDistance() == null) {
			return 0;
		}
		return getDistance().compareTo(ne.getDistance());
	}
}
