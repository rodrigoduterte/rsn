package com.rsn.service;

import java.io.InputStream;
import java.net.URL;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import com.amazonaws.HttpMethod;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

/**
 * S3Util 
 * Communicates with S3 to get a signed url from S3
 * 
 *
 * @author  Gabriel Ferrer
 * @version 1.0
 */
@Component
public class S3Util implements InitializingBean {
	@Value("${s3_bucket_name}")
	private String bucketName;

	@Value("${s3_accesskey}")
	private String accessKey;

	@Value("${s3_secretkey}")
	private String secretAccessKey;

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

	public String uploadFile(String fileName, InputStream content, ObjectMetadata metadata) {
		s3Client.putObject(bucketName, fileName, content, metadata);
		return createSignedUrl(fileName, HttpMethod.GET);
	}
	
	
	@Override
	public void afterPropertiesSet() throws Exception {
		awsCreds = new BasicAWSCredentials(accessKey, secretAccessKey);
		s3Client = AmazonS3ClientBuilder.standard().withRegion(region)
				.withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();
	}
	
	public String createSignedGetUrl(String fileName) {
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

