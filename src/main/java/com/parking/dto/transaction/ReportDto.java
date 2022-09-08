package com.parking.dto.transaction;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReportDto {
	private String buildingName;
	private String vehicleName;
	private String licensePlat;
	private Date enterDate;
	private Date exitDate;
	private BigDecimal price;
}
