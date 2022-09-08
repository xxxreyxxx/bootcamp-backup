package com.parking.controller.voucher;

import java.util.List;

import org.modelmapper.ModelMapper;
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

import com.parking.dto.transaction.MinimarketDto;
import com.parking.dto.voucher.TopupDto;
import com.parking.dto.voucher.VoucherTopupDto;
import com.parking.entity.user.MasterSaldoEntity;
import com.parking.entity.voucher.VoucherTopupEntity;
import com.parking.service.user.impl.UserSaldoServieImpl;
import com.parking.service.voucher.impl.VoucherTopupServiceImpl;

@RestController
public class TopupController {
	@Autowired
	UserSaldoServieImpl userSaldoServieImpl;

	@Autowired
	VoucherTopupServiceImpl voucherTopupServiceImpl;
	
	@Autowired
    private ModelMapper modelMapper;

	@PostMapping("/topup")
	public ResponseEntity<MasterSaldoEntity> topupSaldo(@RequestBody TopupDto topup) {
		try {
			MasterSaldoEntity saldo = userSaldoServieImpl.topUpSaldoBarcode(topup.getUserId(), topup.getVoucherCode());
			if (saldo != null) {
				return new ResponseEntity<>(saldo, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/topup/minimarket")
	public ResponseEntity<MasterSaldoEntity> topupSaldoMiniMarket(@RequestBody MinimarketDto minimarketDto) {
		try {
			MasterSaldoEntity saldo = userSaldoServieImpl.topUpMiniMarket(minimarketDto.getPhoneNumber(),
					minimarketDto.getValue());
			return new ResponseEntity<>(saldo, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/voucher/topup")
	public ResponseEntity<String> createVoucher(@RequestBody VoucherTopupDto voucherTopupDto) {
		try {
			VoucherTopupEntity voucher = modelMapper.map(voucherTopupDto, VoucherTopupEntity.class);
			voucherTopupServiceImpl.createVoucherTopup(voucher);
			return new ResponseEntity<>("Success", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/voucher/topup")
	public ResponseEntity<VoucherTopupEntity> updateVoucher(@RequestBody VoucherTopupDto voucherTopupDto) {
		try {
			VoucherTopupEntity voucher = modelMapper.map(voucherTopupDto, VoucherTopupEntity.class);
			return new ResponseEntity<>(voucherTopupServiceImpl.updateVoucherTopup(voucher), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/voucher/topup")
	public ResponseEntity<VoucherTopupEntity> deleteVoucher(@RequestParam("id") Long id) {
		try {
			return new ResponseEntity<>(voucherTopupServiceImpl.deleteVoucherTopup(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/voucher/topups")
	public ResponseEntity<List<VoucherTopupEntity>> getListVoucher() {
		try {
			return new ResponseEntity<>(voucherTopupServiceImpl.getList(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/voucher/topup")
	public ResponseEntity<Page<VoucherTopupEntity>> getListPageVoucher(@RequestParam("page") Integer page,
			@RequestParam("pageSize") Integer pageSize) {
		try {
			return new ResponseEntity<>(voucherTopupServiceImpl.getListPage(page, pageSize), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
