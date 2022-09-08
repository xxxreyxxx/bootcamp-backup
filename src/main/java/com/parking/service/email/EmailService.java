package com.parking.service.email;

public interface EmailService {
	void sendMessageSuperAdmin(String to, String subject, String email, String password);
	void sendMessageAdmin(String to, String subject, String barcode, String email, String password);
	
}
