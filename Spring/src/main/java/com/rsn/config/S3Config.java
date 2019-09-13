package com.rsn.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class S3Config {
	@Value("#{s3Values.credentials}")		
	private AWSCredentials credentials;
	
	@Value("#{s3Values.clientRegion}")		
	private Regions clientRegion;
	
	@Bean
	public AmazonS3 s3Client() {
		return AmazonS3ClientBuilder.standard()
	           .withRegion(clientRegion)
	           .withCredentials(new AWSStaticCredentialsProvider(credentials))
	           .build();
	}
}
