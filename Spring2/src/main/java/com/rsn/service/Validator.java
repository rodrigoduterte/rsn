package com.rsn.service;

import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordGenerator;
import org.passay.PasswordValidator;
import org.passay.Rule;
import org.passay.WhitespaceRule;
import org.springframework.stereotype.Service;

/**
 * @author vorga
 * @version 1.0
 * Used to validate email and password
 *
 */

@Service
public class Validator {
	LengthRule lr = new LengthRule(8, 16);
	CharacterRule ucr = new CharacterRule(EnglishCharacterData.UpperCase, 1);
	CharacterRule lcr = new CharacterRule(EnglishCharacterData.LowerCase, 1);
	CharacterRule dr = new CharacterRule(EnglishCharacterData.Digit, 1);
	CharacterRule scr = new CharacterRule(EnglishCharacterData.Special, 1);
	WhitespaceRule wsr = new WhitespaceRule();
	
	private List<Rule> getRules() {
		List<Rule> rules = new ArrayList<Rule>();
		rules.add(lr);
		rules.add(ucr);
		rules.add(lcr);
		rules.add(dr);
		rules.add(scr);
		rules.add(wsr);
		
		return rules;
	}
	
	private PasswordValidator validator = new PasswordValidator(getRules());
	
	public boolean isValidPassword(String password) {
		return validator.validate(new 
				PasswordData(new String(password))).isValid();
	}
	
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
	
	public String generatePassword() {
		PasswordGenerator passwordGenerator = new PasswordGenerator();
		return passwordGenerator.generatePassword(8, lcr);
		//return passwordGenerator.generatePassword(8, ucr, lcr, dr, scr);
	}
	
}
