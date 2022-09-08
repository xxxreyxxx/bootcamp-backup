package com.parking.service.user.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parking.entity.user.MasterSaldoEntity;
import com.parking.entity.user.MasterSaldoHistoriesEntity;
import com.parking.entity.user.MasterUserEntity;
import com.parking.entity.voucher.VoucherTopupEntity;
import com.parking.repository.user.MasterSaldoHistoriesRepository;
import com.parking.repository.user.MasterSaldoRepository;
import com.parking.repository.user.MasterUserRepository;
import com.parking.repository.voucher.VoucherTopupRepository;
import com.parking.service.user.UserSaldoService;

@Service
public class UserSaldoServieImpl implements UserSaldoService {
	@Autowired
	VoucherTopupRepository topupRepository;

	@Autowired
	MasterSaldoRepository saldoRepository;

	@Autowired
	MasterUserRepository userRepository;

	@Autowired
	MasterSaldoHistoriesRepository historiesRepository;

	@Transactional
	@Override
	public MasterSaldoEntity topUpSaldoBarcode(Long userId, String voucherCode) {
		VoucherTopupEntity voucher = topupRepository.findByVoucherCodeAndStatus(voucherCode, 1l);
		if (voucher != null) {
			if (voucher.getExpired().after(new Date())) {

				Optional<MasterUserEntity> optional = userRepository.findById(userId);
				MasterUserEntity user = new MasterUserEntity();

				if (optional.isPresent()) {
					user = optional.get();
				}

				MasterSaldoEntity saldo = user.getSaldo();

				saldo.setSaldo(BigDecimal.valueOf(saldo.getSaldo().doubleValue() + voucher.getValue().doubleValue()));
				saldo = saldoRepository.save(saldo);
				voucher.setStatus(0l);
				topupRepository.save(voucher);

				MasterSaldoHistoriesEntity history = new MasterSaldoHistoriesEntity();
				history.setSaldoId(saldo.getId());
				history.setDate(new Date());
				history.setInformation("+ Rp" + voucher.getValue());
				historiesRepository.save(history);
				return saldo;
			} else {
				return null;
			}
		} else {
			return null;
		}

	}

	@Transactional
	@Override
	public MasterSaldoEntity topUpMiniMarket(String phoneNumber, BigDecimal topUpValue) {
		MasterUserEntity user = userRepository.findByUserInfoPhoneNumber(phoneNumber);
		MasterSaldoEntity saldo = user.getSaldo();
		saldo.setSaldo(BigDecimal.valueOf(saldo.getSaldo().doubleValue() + topUpValue.doubleValue()));
		saldoRepository.save(saldo);

		MasterSaldoHistoriesEntity history = new MasterSaldoHistoriesEntity();
		history.setSaldoId(saldo.getId());
		history.setDate(new Date());
		history.setInformation("+ Rp" + topUpValue);
		historiesRepository.save(history);
		return saldo;
	}

	@Override
	public MasterSaldoEntity topUpTransfer(Long userId) {
		return null;
	}
}
