package com.rsn.service;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.springframework.stereotype.Service;

@Service
public class Validator {
	public boolean isEmail(String email) {
		try {
			//
			// Create InternetAddress object and validated the supplied
			// address which is this case is an email address.
			InternetAddress internetAddress = new InternetAddress(email);
			internetAddress.validate();
			
		} catch (AddressException e) {
			return false;
		}
		return true;
	}
	
	
	public static void main(String[] args) {
		Validator validate = new Validator();
		System.out.println( validate.isEmail("kkkkkkkkkkkk") );
		System.out.println( validate.isEmail("labgrie@gmail.com.us") );
	}
}
