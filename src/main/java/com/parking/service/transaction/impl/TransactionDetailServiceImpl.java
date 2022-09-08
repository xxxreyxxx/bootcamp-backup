
package com.parking.service.transaction.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.parking.dto.transaction.CheckInOutDto;
import com.parking.entity.building.SlotEntity;
import com.parking.entity.transaction.TransactionDetail;
import com.parking.entity.transaction.TransactionHeader;
import com.parking.entity.user.MasterSaldoEntity;
import com.parking.entity.user.MasterSaldoHistoriesEntity;
import com.parking.entity.user.MasterUserEntity;
import com.parking.entity.voucher.MasterVoucherEntity;
import com.parking.procces.ProccesGet;
import com.parking.repository.building.SlotRepository;
import com.parking.repository.transaction.TransactionDetailRepository;
import com.parking.repository.transaction.TransactionHeaderRepository;
import com.parking.repository.user.MasterSaldoHistoriesRepository;
import com.parking.repository.user.MasterSaldoRepository;
import com.parking.repository.user.MasterUserRepository;
import com.parking.repository.voucher.VoucherRepository;
import com.parking.service.transaction.TransactionDetailService;

@Service
public class TransactionDetailServiceImpl implements TransactionDetailService {

	@Autowired
	TransactionDetailRepository trDetailRepo;

	@Autowired
	TransactionHeaderRepository trHeaderRepo;

	@Autowired
	VoucherRepository voucherRepository;

	@Autowired
	MasterUserRepository userRepository;

	@Autowired
	MasterSaldoRepository saldoRepository;

	@Autowired
	SlotRepository slotRepository;

	@Autowired
	MasterSaldoHistoriesRepository historiesRepository;

	@Transactional
	@Override
	public TransactionDetail checkIn(CheckInOutDto checkIn) {
		Optional<TransactionHeader> optional = trHeaderRepo.findById(checkIn.getTrHeaderId());
		TransactionHeader header = new TransactionHeader();

		if (optional.isPresent()) {
			header = optional.get();
		}

		if (header.getSlot().getFloor().getBuilding().getBarcode().equals(checkIn.getBarcode())) {
			TransactionDetail detail = new TransactionDetail();
			header.setStatus(0l);
			trHeaderRepo.save(header);
			Date date = new Date();

			detail.setTransactionHeader(header);
			detail.setEnterDate(new Date());
			detail.setExitDate(date);
			detail.setStatus(1l);
			detail.setTotalPrice(BigDecimal.valueOf(0));

			SlotEntity slot = header.getSlot();
			slot.setStatus(0l);
			slotRepository.save(slot);

			return trDetailRepo.save(detail);
		} else {
			return null;
		}
	}

	@Transactional
	@Override
	public TransactionDetail checkOut(CheckInOutDto checkOut) {
		TransactionDetail detail = findById(checkOut.getTrDetailId());
		TransactionHeader header = detail.getTransactionHeader();
		if (header.getSlot().getFloor().getBuilding().getBarcode().equals(checkOut.getBarcode())) {
			ProccesGet con = new ProccesGet();
			MasterUserEntity user = userRepository
					.findByUserInfoId(detail.getTransactionHeader().getVehicle().getMasterUserInfoId());
			MasterSaldoEntity saldo = user.getSaldo();

			Integer diff = con.dateCalculate(detail.getEnterDate());
			BigDecimal price = con.price(diff, detail.getTransactionHeader());

			if (checkOut.getVoucherCode() != null) {
				MasterVoucherEntity voucher = voucherRepository.findByVoucherCodeAndStatus(checkOut.getVoucherCode(), 1l);
				detail.setVoucherCode(checkOut.getVoucherCode());
				price = BigDecimal.valueOf(price.doubleValue() - voucher.getDiscount().doubleValue());

				voucher.setStatus(0l);
				voucherRepository.save(voucher);
				
				if (price.doubleValue() < 0) {
					price = BigDecimal.valueOf(0);
				}
			}

			detail.setExitDate(new Date());
			detail.setTotalPrice(price);
			detail.setStatus(0l);

			Double pay = saldo.getSaldo().doubleValue() - price.doubleValue();
			saldo.setSaldo(BigDecimal.valueOf(pay));
			saldoRepository.save(saldo);

			MasterSaldoHistoriesEntity history = new MasterSaldoHistoriesEntity();
			history.setSaldoId(saldo.getId());
			history.setDate(new Date());
			history.setInformation("- Rp" + price);
			historiesRepository.save(history);

			SlotEntity slot = header.getSlot();
			slot.setStatus(1l);
			slotRepository.save(slot);
			return trDetailRepo.save(detail);

		} else {
			return null;
		}
	}

	@Override
	public TransactionDetail refresh(Long id) {
		ProccesGet con = new ProccesGet();
		TransactionDetail detail = findById(id);
		Integer diff = con.dateCalculate(detail.getEnterDate());
		BigDecimal price = con.price(diff, detail.getTransactionHeader());
		detail.setExitDate(new Date());
		detail.setTotalPrice(price);
		return trDetailRepo.save(detail);
	}

	@Override
	public TransactionDetail findById(Long id) {
		Optional<TransactionDetail> optional = trDetailRepo.findById(id);
		TransactionDetail detail = new TransactionDetail();

		if (optional.isPresent()) {
			detail = optional.get();
		}

		return detail;
	}

	@Override
	public List<TransactionDetail> findAll() {
		return trDetailRepo.findAll();
	}

	@Override
	public Page<TransactionDetail> findAllPage(Integer page, Integer pageSize) {
		Pageable paging = PageRequest.of(page, pageSize);
		return trDetailRepo.findAll(paging);
	}

	@Override
	public Page<TransactionDetail> findAllByVehicleType(Integer page, Integer pageSize, Long vehicleType) {
		Pageable paging = PageRequest.of(page, pageSize);
		return trDetailRepo.findAllByTransactionHeaderSlotSlotTypeOrderByTransactionHeaderBookingDateDesc(paging,
				vehicleType);
	}

	@Override
	public List<TransactionDetail> getListCheckIn(Long userInfoId) {
		return trDetailRepo.findAllByStatusAndTransactionHeaderVehicleMasterUserInfoIdOrderByIdDesc(1l, userInfoId);
	}

	public List<TransactionDetail> getListCheckOut(Long userInfoId) {
		return trDetailRepo.findAllByStatusAndTransactionHeaderVehicleMasterUserInfoIdOrderByIdDesc(0l, userInfoId);
	}

	@Override
	public Page<TransactionDetail> findAllByVehicleType(Integer page, Integer pageSize, Long vehicleType,
			Long adminId) {
		Pageable paging = PageRequest.of(page, pageSize);
		return trDetailRepo
				.findAllByTransactionHeaderSlotSlotTypeAndTransactionHeaderSlotFloorBuildingAdminIdOrderByTransactionHeaderBookingDateDesc(
						paging, vehicleType, adminId);
	}
}
