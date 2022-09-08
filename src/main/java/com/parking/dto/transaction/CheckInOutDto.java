package com.parking.dto.transaction;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CheckInOutDto {
	private Long trHeaderId;
	private Long trDetailId;
	private String voucherCode;
	private String barcode;
}
