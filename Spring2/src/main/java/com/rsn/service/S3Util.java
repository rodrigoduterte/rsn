package com.rsn.service;

import java.net.URL;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.amazonaws.HttpMethod;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;

@Component
public class S3Util implements InitializingBean {

	@Value("#{T(java.lang.System).getenv('s3_bucket_name')}")
	private String bucketName;

	@Value("#{T(java.lang.System).getenv('s3_accesskey')}")  //s3_accesskey
	private String accessKey;

	@Value("#{T(java.lang.System).getenv('s3_secretkey')}")
	private String secretAccessKey;

	@Value("#{T(java.lang.System).getenv('s3_region')}")
	private String region;

	private BasicAWSCredentials awsCreds;
	private AmazonS3 s3Client;

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

}

