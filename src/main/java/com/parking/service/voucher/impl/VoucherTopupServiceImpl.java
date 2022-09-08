package com.parking.service.voucher.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.parking.entity.voucher.MasterVoucherEntity;
import com.parking.entity.voucher.VoucherTopupEntity;
import com.parking.procces.GenerateRandom;
import com.parking.repository.voucher.VoucherTopupRepository;
import com.parking.service.voucher.VoucherTopupService;

@Service
public class VoucherTopupServiceImpl implements VoucherTopupService {
	@Autowired
	VoucherTopupRepository topupRepository;
	
	@Autowired
	GenerateRandom generateRandom; 

	@Override
	public VoucherTopupEntity createVoucherTopup(VoucherTopupEntity voucher) {
		voucher.setVoucherCode(generateRandom.getNumericString(16));
		voucher.setStatus(1l);
		return topupRepository.save(voucher);
	}

	@Override
	public VoucherTopupEntity updateVoucherTopup(VoucherTopupEntity voucher) {
		VoucherTopupEntity update = findById(voucher.getId());
		update.setValue(voucher.getValue());
		update.setExpired(voucher.getExpired());
		return topupRepository.save(update);
	}

	@Override
	public VoucherTopupEntity deleteVoucherTopup(Long id) {
		VoucherTopupEntity voucher = findById(id);
		voucher.setStatus(0l);
		return topupRepository.save(voucher);
	}

	@Override
	public VoucherTopupEntity findById(Long id) {
		Optional<VoucherTopupEntity> optional = topupRepository.findById(id);
		VoucherTopupEntity voucherTopup = new VoucherTopupEntity();
		
		if (optional.isPresent()) {
			voucherTopup = optional.get();
		}
		
		return voucherTopup;
	}

	@Override
	public List<VoucherTopupEntity> getList() {
		return topupRepository.findAll();
	}

	@Override
	public Page<VoucherTopupEntity> getListPage(Integer page, Integer pageSize) {
		Pageable paging = PageRequest.of(page, pageSize);
		return topupRepository.findAllByStatus(paging, 1l);
	}
}
