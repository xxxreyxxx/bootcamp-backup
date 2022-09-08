package com.parking.dto.transaction;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MinimarketDto {
	private String phoneNumber;
	private BigDecimal value;
}
