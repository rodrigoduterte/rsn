package com.rsn.utility;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class S3 {
	
	
	private static String URL = "https://4guys.s3.us-east-2.amazonaws.com/";
    private static AWSCredentials credentials = new BasicAWSCredentials("AKIAZ76OY2SM6LR3DGRA",
            "9/11yTfqLfqhPjTIDQ1KRFWDztMN1h+lbL8aH27r");
    private static String bucketName ="4guys";
    private static Regions clientRegion = Regions.US_EAST_2;
    private static AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
           .withRegion(clientRegion)
           .withCredentials(new AWSStaticCredentialsProvider(credentials))
           .build();
    
    
    //checking to see if object exists in the bucket
    public static boolean objectExists(String objectKey) {
        boolean exists = false;
        try {
            exists = s3Client.doesObjectExist(bucketName, objectKey);
        } catch (AmazonServiceException e) {}
          catch (SdkClientException e) {}
        return exists;
    }
	
	
	
	public static void main(String[] args) {
		
		objectExists("4 guys stuff.docx");
		
		System.out.println("The object exists");
		
		
	
	
	
    
	}

}
