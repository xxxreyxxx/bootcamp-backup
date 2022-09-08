package com.parking.config.email;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class EmailTemplate {
	public SimpleMailMessage templateMessageAdmin(String barcode, String email, String password) {
	    SimpleMailMessage message = new SimpleMailMessage();
	    message.setText(
	      "Thank you for join with us.\n"
	    + "Our Website http://pascal-enigma.site:3001 \n"
	    + "Let's login and change the password. \n\n"
	    + "email : "+email+"\n"
	    + "password : "+password+"\n\n"
	    + "Please click this link below to download your building's barcode. \n"
	    + "barcode : http://barcodes4.me/barcode/qr/parkMe.png?value="+barcode+"&size=10 \n\n"
	    + "parkMe Team Indonesia");
	    return message;
	}
	
	public SimpleMailMessage templateMessageSuperAdmin(String email, String password) {
	    SimpleMailMessage message = new SimpleMailMessage();
	    message.setText(
	      "Thank you for join with us.\n"
	    + "Our Website http://pascal-enigma.site:3001 \n"
	    + "Let's login and change the password. \n\n"
	    + "email : "+email+"\n"
	    + "password : "+password+"\n\n"
	    + "parkMe Team Indonesia");
	    return message;
	}
}
