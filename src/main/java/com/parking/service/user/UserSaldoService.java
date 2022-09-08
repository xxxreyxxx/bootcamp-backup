package com.parking.service.user;

import java.math.BigDecimal;

import com.parking.entity.user.MasterSaldoEntity;

public interface UserSaldoService {
	MasterSaldoEntity topUpSaldoBarcode(Long userId, String barcode);
	MasterSaldoEntity topUpTransfer(Long userId);
	MasterSaldoEntity topUpMiniMarket(String phoneNumber, BigDecimal topUpValue);
}
