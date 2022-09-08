package com.parking.service.voucher.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.parking.dto.voucher.VoucherDto;
import com.parking.entity.voucher.MasterVoucherEntity;
import com.parking.repository.voucher.VoucherRepository;
import com.parking.service.voucher.VoucherService;

@Service
public class VoucherServiceImpl implements VoucherService {
	@Autowired
	VoucherRepository voucherRepository;

	@Override
	public MasterVoucherEntity createVoucher(VoucherDto voucher) {
		MasterVoucherEntity save = new MasterVoucherEntity();
		save.setVoucherCode(voucher.getVoucherCode());
		save.setDiscount(voucher.getDiscount());
		save.setStatus(1l);
		return voucherRepository.save(save);
	}

	@Override
	public MasterVoucherEntity updateVoucher(VoucherDto voucher) {
		MasterVoucherEntity save = new MasterVoucherEntity();
		save.setId(voucher.getId());
		save.setVoucherCode(voucher.getVoucherCode());
		save.setDiscount(voucher.getDiscount());
		save.setStatus(1l);
		return voucherRepository.save(save);
	}

	@Override
	public MasterVoucherEntity deleteVoucher(Long id) {
		MasterVoucherEntity voucher = findById(id);
		voucher.setStatus(0l);
		return voucherRepository.save(voucher);
	}

	@Override
	public MasterVoucherEntity findById(Long id) {
		Optional<MasterVoucherEntity> optional = voucherRepository.findById(id);
		MasterVoucherEntity voucher = new MasterVoucherEntity();

		if (optional.isPresent()) {
			voucher = optional.get();
		}

		return voucher;
	}

	@Override
	public MasterVoucherEntity findByVoucherCodeAndStatus(String voucherCode) {
		return voucherRepository.findByVoucherCodeAndStatus(voucherCode, 1l);
	}

	@Override
	public List<MasterVoucherEntity> findAll() {
		return voucherRepository.findAllByStatus(1l);
	}

	@Override
	public Page<MasterVoucherEntity> findAll(Integer page, Integer pageSize) {
		Pageable paging = PageRequest.of(page, pageSize);
		return voucherRepository.findAllByStatus(paging, 1l);
	}

}
