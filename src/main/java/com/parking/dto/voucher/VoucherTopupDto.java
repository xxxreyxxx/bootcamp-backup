package com.parking.dto.voucher;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VoucherTopupDto {
	private Long id;
	private String voucherCode;
	private BigDecimal value;
	private Date expired;
	private Long status;
}
