package com.parking.service.voucher.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.parking.entity.voucher.MasterVoucherEntity;
import com.parking.repository.voucher.SearchVoucherRepository;
import com.parking.service.voucher.SearchVoucherService;

@Service
public class SearchVoucherServiceImpl implements SearchVoucherService {
	@Autowired
	SearchVoucherRepository searchVoucherRepository;

	@Override
	public Page<MasterVoucherEntity> searchByVoucherCode(Integer page, Integer pageSize, String voucherCode,
			String orderBy, String sort) {
		Pageable paging = sortBy(page, pageSize, orderBy, sort);
		return searchVoucherRepository.searchByVoucherCode(paging, voucherCode);
	}

	@Override
	public Page<MasterVoucherEntity> searchByDiscount(Integer page, Integer pageSize, String discount, String orderBy,
			String sort) {
		Pageable paging;

		switch (orderBy) {
		case "code":
			if (sort.equals("asc")) {
				paging = PageRequest.of(page, pageSize, Sort.by("voucher_code").ascending());
			} else {
				paging = PageRequest.of(page, pageSize, Sort.by("voucher_code").descending());
			}
			break;
		default:
			paging = sortBy(page, pageSize, orderBy, sort);
			break;
		}
		
		return searchVoucherRepository.searchByDiscount(paging, discount);
	}

	@Override
	public Page<MasterVoucherEntity> sortAll(Integer page, Integer pageSize, String orderBy, String sort) {
		Pageable paging = sortBy(page, pageSize, orderBy, sort);
		return searchVoucherRepository.searchAll(paging);
	}

	private String orderBy(String orderBy) {
		switch (orderBy) {
		case "code":
			return "voucherCode";
		case "discount":
			return "discount";
		default:
			return "id";
		}
	}

	private Pageable sortBy(Integer page, Integer pageSize, String orderBy, String sort) {
		if (sort.equals("asc")) {
			return PageRequest.of(page, pageSize, Sort.by(orderBy(orderBy)).ascending());
		} else {
			return PageRequest.of(page, pageSize, Sort.by(orderBy(orderBy)).descending());
		}
	}

}
