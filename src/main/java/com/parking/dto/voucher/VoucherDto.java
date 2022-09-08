package com.parking.dto.voucher;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VoucherDto {
	private Long id;
	private String voucherCode;
	private Long voucherTypeId;
	private BigDecimal discount;
}
