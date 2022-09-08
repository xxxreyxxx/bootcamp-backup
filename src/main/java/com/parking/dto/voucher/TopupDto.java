package com.parking.dto.voucher;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TopupDto {
	private Long userId;
	private String voucherCode;
}
