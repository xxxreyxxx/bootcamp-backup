package com.parking.dto.building;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BuildingDto {
	private Long id;
	private Long adminId;
	private String buildingName;
	private String barcode;
	private String location;
	private String longitude;
	private String latitude;
	private BigDecimal minPrice;
	private BigDecimal maxPrice;
	private Long status;
}
