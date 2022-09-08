package com.parking.controller.transaction;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.parking.service.transaction.impl.ReportServiceImpl;

@RestController
public class ReportController {
	@Autowired
	ReportServiceImpl reportServiceImpl;

	@GetMapping("/transaction/report/today")
	public ResponseEntity<byte[]> getReport() {
		byte[] report = reportServiceImpl.doGetReport(reportServiceImpl.allReportToday());
		if (report != null) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/pdf"));
			headers.setContentDispositionFormData("inline", "report.pdf");
			headers.add("reportName", "report.pdf");
			return ResponseEntity.ok().contentLength(report.length).headers(headers).body(report);
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}

	@GetMapping("/transaction/report/between")
	public ResponseEntity<byte[]> getReportBetween(@RequestParam("start") Date start, @RequestParam("end") Date end) {
		byte[] report = reportServiceImpl.doGetReport(reportServiceImpl.allReportBetween(start, end));
		if (report != null) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/pdf"));
			headers.setContentDispositionFormData("inline", "report.pdf");
			headers.add("reportName", "report.pdf");
			return ResponseEntity.ok().contentLength(report.length).headers(headers).body(report);
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}

	@GetMapping("/transaction/report/today/type")
	public ResponseEntity<byte[]> getReportType(@RequestParam("type") Long type) {
		byte[] report = reportServiceImpl.doGetReport(reportServiceImpl.allReportTodayType(type));
		if (report != null) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/pdf"));
			headers.setContentDispositionFormData("inline", "report.pdf");
			headers.add("reportName", "report.pdf");
			return ResponseEntity.ok().contentLength(report.length).headers(headers).body(report);
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}

	@GetMapping("/transaction/report/between/type")
	public ResponseEntity<byte[]> getReportBetweenType(@RequestParam("start") Date start, @RequestParam("end") Date end,
			@RequestParam("type") Long type) {
		byte[] report = reportServiceImpl.doGetReport(reportServiceImpl.allReportBetweenType(start, end, type));
		if (report != null) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/pdf"));
			headers.setContentDispositionFormData("inline", "report.pdf");
			headers.add("reportName", "report.pdf");
			return ResponseEntity.ok().contentLength(report.length).headers(headers).body(report);
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}

	@GetMapping("/transaction/report/today/type/admin")
	public ResponseEntity<byte[]> getReportTypeAdmin(@RequestParam("type") Long type, @RequestParam("id") Long id) {
		byte[] report = reportServiceImpl.doGetReport(reportServiceImpl.allReportTodayTypeAndAdminId(type, id));
		if (report != null) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/pdf"));
			headers.setContentDispositionFormData("inline", "report.pdf");
			headers.add("reportName", "report.pdf");
			return ResponseEntity.ok().contentLength(report.length).headers(headers).body(report);
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}

	@GetMapping("/transaction/report/between/type/admin")
	public ResponseEntity<byte[]> getReportBetweenTypeAdmin(@RequestParam("start") Date start,
			@RequestParam("end") Date end, @RequestParam("type") Long type, @RequestParam("id") Long id) {
		byte[] report = reportServiceImpl
				.doGetReport(reportServiceImpl.allReportBetweenTypeAndAdminId(start, end, type, id));
		if (report != null) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/pdf"));
			headers.setContentDispositionFormData("inline", "report.pdf");
			headers.add("reportName", "report.pdf");
			return ResponseEntity.ok().contentLength(report.length).headers(headers).body(report);
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}

}
