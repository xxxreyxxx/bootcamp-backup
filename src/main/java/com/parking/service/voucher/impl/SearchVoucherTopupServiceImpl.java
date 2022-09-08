package com.parking.service.voucher.impl;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.parking.entity.voucher.VoucherTopupEntity;
import com.parking.repository.voucher.SearchVoucherTopupRepository;
import com.parking.service.voucher.SearchVoucherTopupService;

@Service
public class SearchVoucherTopupServiceImpl implements SearchVoucherTopupService {
	@Autowired
	SearchVoucherTopupRepository searchVoucherTopupRepository;

	@Override
	public Page<VoucherTopupEntity> searchByVoucherCode(Integer page, Integer pageSize, String voucherCode,
			String orderBy, String sort) {
		Pageable paging = sortBy(page, pageSize, orderBy, sort);
		return searchVoucherTopupRepository.searchByVoucherCode(paging, voucherCode);
	}

	@Override
	public Page<VoucherTopupEntity> searchByValue(Integer page, Integer pageSize, String value, String orderBy,
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
		
		return searchVoucherTopupRepository.searchByDiscount(paging, value);
	}

	@Override
	public Page<VoucherTopupEntity> searchByExpired(Integer page, Integer pageSize, Date expired, String orderBy,
			String sort) {
		Pageable paging = sortBy(page, pageSize, orderBy, sort);
		return searchVoucherTopupRepository.searchByExpired(paging, expired);
	}

	@Override
	public Page<VoucherTopupEntity> sortAll(Integer page, Integer pageSize, String orderBy, String sort) {
		Pageable paging = sortBy(page, pageSize, orderBy, sort);
		return searchVoucherTopupRepository.searchAll(paging);
	}

	private String orderBy(String orderBy) {
		switch (orderBy) {
		case "code":
			return "voucherCode";
		case "value":
			return "value";
		case "expired":
			return "expired";
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
