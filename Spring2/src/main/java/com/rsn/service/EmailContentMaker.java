package com.rsn.service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;

//@Service
public class EmailContentMaker {
	private String emailToRecipient;
	private String username;
	private String firstname;
	private String middlename;
	private String lastname;
	private String activateName;
	
	private String server = "http://ec2-18-188-105-4.us-east-2.compute.amazonaws.com:8080/rsn";
	
	
	public EmailContentMaker(String emailToRecipient, String username, String firstname, String middlename, String lastname,
			String activateName) {
		super();
		this.emailToRecipient = emailToRecipient;
		this.username = username;
		this.firstname = firstname;
		this.middlename = middlename;
		this.lastname = lastname;
		this.activateName = activateName;
	}

	public String build() throws TemplateException, IOException {
		Configuration cfg = new Configuration(new Version("2.3.23"));
		cfg.setClassForTemplateLoading(EmailContentMaker.class, "/");
		cfg.setDefaultEncoding("UTF-8");
		
		String html = "";
		Template template = cfg.getTemplate("mailContent.ftl");

		Map<String, Object> templateData = new HashMap<>();
		
		templateData.put("username", this.username);
		templateData.put("firstname", this.firstname);
		templateData.put("middlename", this.middlename);
		templateData.put("lastname", this.lastname);
		templateData.put("activateName", this.activateName);
		templateData.put("server", this.server);
		
		
		try (StringWriter out = new StringWriter()) {

			template.process(templateData, out);
			html = out.getBuffer().toString();

			out.flush();
		}
		
		return html;
	}

	public String getUsername() {
		return username;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getMiddlename() {
		return middlename;
	}

	public String getLastname() {
		return lastname;
	}

	public String getActivateName() {
		return activateName;
	}

	public String getEmailToRecipient() {
		return emailToRecipient;
	}
	
}
