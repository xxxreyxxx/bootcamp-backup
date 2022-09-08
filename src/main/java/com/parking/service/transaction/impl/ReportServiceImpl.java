package com.parking.service.transaction.impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parking.dto.transaction.ReportDto;
import com.parking.entity.transaction.TransactionDetail;
import com.parking.repository.transaction.ReportRepository;
import com.parking.service.transaction.ReportService;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ReportServiceImpl implements ReportService {
	@Autowired
	private ReportRepository reportRepository;
	private final Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);

	@Override
	public byte[] doGetReport(List<TransactionDetail> transactionDetail) {
		List<ReportDto> reportData = setReportData(transactionDetail);

		HashMap<String, Object> parameters = new HashMap<>();

		InputStream jrxmlInput = null;
		try {
			jrxmlInput = this.getClass().getClassLoader().getResource("report/report2.jrxml").openStream();
			JasperReport report = JasperCompileManager.compileReport(jrxmlInput);
			JRDataSource datasource = new JRBeanCollectionDataSource(reportData, true);
			JasperPrint print = JasperFillManager.fillReport(report, parameters, datasource);
			return JasperExportManager.exportReportToPdf(print);
		} catch (JRException | IOException | NullPointerException e) {
			logger.error(e.toString());
			return null;
		} 

	}

	private List<ReportDto> setReportData(List<TransactionDetail> transactionDetail) {
		List<TransactionDetail> detailEntities = transactionDetail;
		List<ReportDto> reportData = new ArrayList<>();
		for (TransactionDetail data : detailEntities) {
			ReportDto report = new ReportDto();
			report.setBuildingName(data.getTransactionHeader().getSlot().getFloor().getBuilding().getBuildingName());
			report.setVehicleName(data.getTransactionHeader().getVehicle().getVehicleName());
			report.setLicensePlat(data.getTransactionHeader().getVehicle().getLicensePlate());
			report.setEnterDate(data.getEnterDate());
			report.setExitDate(data.getExitDate());
			report.setPrice(data.getTotalPrice());
			reportData.add(report);
		}

		return reportData;
	}

	@Override
	public List<TransactionDetail> allReportToday() {
		return reportRepository.allReportToday();
	}

	@Override
	public List<TransactionDetail> allReportBetween(Date start, Date end) {
		return reportRepository.allReportBetween(start, end);
	}

	@Override
	public List<TransactionDetail> allReportTodayType(Long type) {
		return reportRepository.allReportTodayType(type);
	}

	@Override
	public List<TransactionDetail> allReportBetweenType(Date start, Date end, Long type) {
		return reportRepository.allReportBetweenType(start, end, type);
	}

	@Override
	public List<TransactionDetail> allReportTodayTypeAndAdminId(Long type, Long adminId) {
		return reportRepository.allReportTodayTypeAndAdminId(type, adminId);
	}

	@Override
	public List<TransactionDetail> allReportBetweenTypeAndAdminId(Date start, Date end, Long type, Long adminId) {
		return reportRepository.allReportBetweenTypeAndAdminId(start, end, type, adminId);
	}

}
