package com.rsn.service;

import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;

/**
 * EmailContentMaker 
 * Builds email content using Freemarker templating library
 * 
 *
 * @author  Gabriel Ferrer
 * @version 1.0
 */
public class EmailContentMaker {
	private String password;
	private String firstname;
	private String templateType;
	private String today;
	private String uri;
	private SimpleDateFormat formatter = new SimpleDateFormat("MMMM d, yyyy"); 
	private String url = "http://ec2-18-188-105-4.us-east-2.compute.amazonaws.com:8080/rsn/user/activate";
	
	
	public EmailContentMaker(String password, String firstname, 
			String templateType, Date today, String uri) {
		super();
		this.password = password;
		this.firstname = firstname;
		this.templateType = templateType;
		this.today = formatter.format(today);
		this.uri = uri;
	}

	public String build() throws TemplateException, IOException {
		Configuration cfg = new Configuration(new Version("2.3.23"));
		cfg.setClassForTemplateLoading(EmailContentMaker.class, "/");
		cfg.setDefaultEncoding("UTF-8");
		cfg.setWhitespaceStripping(true);
		
		String html = "";
		String templateType = "";
		if (this.templateType.equals("password")) {
			templateType = "recoverPassword.ftl";
		} else if (this.templateType.equals("activate")) {
			templateType = "welcome.ftl";
		}
		
		Template template = cfg.getTemplate(templateType);

		Map<String, Object> templateData = new HashMap<>();
		
		templateData.put("password", this.password);
		templateData.put("firstname", this.firstname);
		templateData.put("today", this.today);
		templateData.put("url", this.url);
		templateData.put("uri", this.uri);
		
		
		try (StringWriter out = new StringWriter()) {

			template.process(templateData, out);
			html = out.getBuffer().toString();

			out.flush();
		}
		
		return html;
	}

	public String getPassword() {
		return password;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getTemplateType() {
		return templateType;
	}

	public String getToday() {
		return today;
	}

	public String getUri() {
		return uri;
	}
}
