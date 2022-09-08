package com.parking.service.email.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.parking.config.email.EmailTemplate;
import com.parking.service.email.EmailService;

@Component
public class EmailServiceImpl implements EmailService {

	@Autowired
	public JavaMailSender emailSender;

	@Autowired
	private EmailTemplate emailTemplate;

	@Override
	public void sendMessageAdmin(String to, String subject, String barcode, String email, String password) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setFrom("no-reply@parkme.co.id");
		message.setSubject(subject);
		message.setText(emailTemplate.templateMessageAdmin(barcode, email, password).getText());
		emailSender.send(message);
	}

	@Override
	public void sendMessageSuperAdmin(String to, String subject, String email, String password) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setFrom("no-reply@parkme.co.id");
		message.setSubject(subject);
		message.setText(emailTemplate.templateMessageSuperAdmin(email, password).getText());
		emailSender.send(message);
	}
}