package com.rsn.service;

import org.springframework.stereotype.Service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

//@Service
public class S3Service {
	private BasicAWSCredentials credential;
	
	private String bucketName;
	
	private String URL;
	
	private AmazonS3 s3Client;
	
	private Regions clientRegion;
	
	public S3Service(BasicAWSCredentials credential) {
		this.s3Client = AmazonS3ClientBuilder.standard()
	            .withRegion(clientRegion)
	            .withCredentials(new AWSStaticCredentialsProvider(credential))
	            .build();
	}
	
	public String getCompleteURL(String uri) {
		return URL + uri;
	}
    
    public boolean objectExists(String objectKey) {
    	boolean exists = false;
    	try {
    		exists = s3Client.doesObjectExist(bucketName, objectKey);
    	} catch (AmazonServiceException e) {} 
    	  catch (SdkClientException e) {}
    	return exists;
    }
	
}
