package com.rsn.seed;

import java.text.ParseException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

////Run this as a java program. This program populates your database
public class Populate {
	public static void main(String[] args) throws ParseException {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("localContext.xml");
		MakeUsers.run(appContext);
	}
}
