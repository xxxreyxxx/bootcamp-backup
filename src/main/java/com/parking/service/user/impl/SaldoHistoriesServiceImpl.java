package com.parking.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parking.entity.user.MasterSaldoHistoriesEntity;
import com.parking.repository.user.MasterSaldoHistoriesRepository;
import com.parking.service.user.SaldoHistoriesService;

@Service
public class SaldoHistoriesServiceImpl implements SaldoHistoriesService {

	@Autowired
	MasterSaldoHistoriesRepository historiesRepository;

	@Override
	public MasterSaldoHistoriesEntity saveHistories(MasterSaldoHistoriesEntity histories) {
		return historiesRepository.save(histories);
	}

}
