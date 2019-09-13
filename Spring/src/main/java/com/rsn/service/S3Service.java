package com.rsn.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.WritableResource;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.GroupGrantee;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.Permission;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.rsn.component.S3Values;
import com.rsn.config.S3Config;


public class S3Service {
	@Autowired
	static S3Config conf;
	
//	@Autowired
//	private S3Values s3vals;
	
	@Value("#{s3Values.bucketName}")		
	private String bucketName;
	
	public void uploadFile(InputStream content, 
    		ObjectMetadata metadata, String objectKey) throws IOException {
		PutObjectRequest por = new PutObjectRequest(bucketName, objectKey, content, metadata);
	    AccessControlList acl = new AccessControlList();
	    acl.grantPermission(GroupGrantee.AllUsers, Permission.Read);
	    por.setAccessControlList(acl);
        conf.s3Client().putObject(por);
	}
	
	public boolean objectExists(String objectKey) {
        boolean exists = false;
        try {
            exists = conf.s3Client().doesObjectExist(bucketName, objectKey);
        } catch (AmazonServiceException e) {}
          catch (SdkClientException e) {}
        return exists;
    }
}
