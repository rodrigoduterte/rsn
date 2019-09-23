package com.rsn.component;

import org.springframework.stereotype.Component;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;

@Component("s3Values")
public class S3Values {
	public String bucketName ="4guys";
	public Regions clientRegion = Regions.US_EAST_2;
	public String URL = "https://4guys.s3.us-east-2.amazonaws.com/";
	public AWSCredentials credentials = 
			new BasicAWSCredentials( System.getenv("s3_accesskey"),
			System.getenv("s3_secretkey"));
}
