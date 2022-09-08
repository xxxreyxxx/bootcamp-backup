package com.parking.controller.voucher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.parking.dto.voucher.VoucherDto;
import com.parking.dto.voucher.VoucherIsExist;
import com.parking.entity.voucher.MasterVoucherEntity;
import com.parking.service.voucher.impl.VoucherServiceImpl;

@RestController
public class VoucherController {

	@Autowired
	VoucherServiceImpl voucherServiceImpl;

	@PostMapping("/voucher")
	public ResponseEntity<MasterVoucherEntity> createVoucher(@RequestBody VoucherDto voucher) {
		try {
			return new ResponseEntity<>(voucherServiceImpl.createVoucher(voucher), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@PutMapping("/voucher")
	public ResponseEntity<MasterVoucherEntity> updateVoucher(@RequestBody VoucherDto voucher) {
		try {
			return new ResponseEntity<>(voucherServiceImpl.updateVoucher(voucher), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@DeleteMapping("/voucher")
	public ResponseEntity<MasterVoucherEntity> updateVoucher(@RequestParam("id") Long id) {
		try {
			return new ResponseEntity<>(voucherServiceImpl.deleteVoucher(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/voucher")
	public ResponseEntity<MasterVoucherEntity> findVoucherById(@RequestParam("id") Long id) {
		try {
			return new ResponseEntity<>(voucherServiceImpl.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/voucher/code")
	public ResponseEntity<VoucherIsExist> findVoucherByVoucherCode(@RequestParam("code") String code) {
		try {
			MasterVoucherEntity voucher = voucherServiceImpl.findByVoucherCodeAndStatus(code);
			VoucherIsExist response = new VoucherIsExist();
			if (voucher != null) {
				response.setResponse(true);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				response.setResponse(false);
				return new ResponseEntity<>(response, HttpStatus.OK);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/voucherss")
	public ResponseEntity<List<MasterVoucherEntity>> findAllVoucher(@RequestParam("id") Long id) {
		try {
			return new ResponseEntity<>(voucherServiceImpl.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/vouchers")
	public ResponseEntity<Page<MasterVoucherEntity>> findAllVoucher(@RequestParam("page") Integer page,
			@RequestParam("pageSize") Integer pageSize) {
		try {
			return new ResponseEntity<>(voucherServiceImpl.findAll(page, pageSize), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
}
