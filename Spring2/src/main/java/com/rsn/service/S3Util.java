package com.rsn.service;

import java.net.URL;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import com.amazonaws.HttpMethod;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;

@Component
//@PropertySource(value = {"classpath:app.properties"})
public class S3Util implements InitializingBean {

	//@Value("#{T(java.lang.System).getenv('s3_bucket_name')}")
	@Value("${s3_bucket_name}")
	private String bucketName;

	//@Value("#{T(java.lang.System).getenv('s3_accesskey')}")  //s3_accesskey
	@Value("${s3_accesskey}")
	private String accessKey;

	//@Value("#{T(java.lang.System).getenv('s3_secretkey')}")
	@Value("${s3_secretkey}")
	private String secretAccessKey;

	//@Value("#{T(java.lang.System).getenv('s3_region')}")
	@Value("${s3_region}")
	private String region;

	private BasicAWSCredentials awsCreds;
	public AmazonS3 s3Client;

	
	public boolean s3ObjectExists(String objectName) {
		return s3Client.doesObjectExist(bucketName, objectName);
	}
	
	public String createSignedUrl(String fileName, HttpMethod method) {

		// Set the presigned URL to expire after one minute.
		java.util.Date expiration = new java.util.Date();
		long expTimeMillis = expiration.getTime();
		expTimeMillis += 40000 * 60;
		expiration.setTime(expTimeMillis);

		// Generate the presigned URL.
		GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, fileName)
				.withMethod(method);  //.withExpiration(expiration);
		URL url = s3Client.generatePresignedUrl(generatePresignedUrlRequest);

		return url.toString();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		awsCreds = new BasicAWSCredentials(accessKey, secretAccessKey);
		s3Client = AmazonS3ClientBuilder.standard().withRegion(region)
				.withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();
	}
	
	public String createSignedGetUrl(String fileName) {
		System.out.println(fileName);
		if (fileName == null) {
			return "";
		} else {
			return createSignedUrl(fileName, HttpMethod.GET);
		}

	}

	public String createSignedPutUrl(String fileName) {
		if (fileName == null) {
			return "";
		} else {
			return createSignedUrl(fileName, HttpMethod.PUT);
		}
	}
	
}

