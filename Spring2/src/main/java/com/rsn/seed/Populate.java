package com.rsn.seed;

import java.text.ParseException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

////Run this as a java program. This program populates your database
////Go to the Run Configurations and add a java config for RevatureSocialNetwork2
////Copy Apache's RevatureSocialNetwork config to RevatureSocialNetwork2 
public class Populate {
	///// DO NOT RUN!!!!!!!!!!!!!!!
	///// DO NOT RUN!!!!!!!!!!!!!!!
	public static void main(String[] args) throws ParseException {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("localContext.xml");
		MakeUsers.run(appContext);
	}
}
