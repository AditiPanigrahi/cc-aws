package com.cc.S3;

import org.springframework.beans.factory.annotation.Value;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;

public class CreateS3Bucket {


	public void createBucket(String bucketName){
		//String bucketName = "video-h264file-bucket";
		final AmazonS3  s3 = AmazonS3ClientBuilder.defaultClient();
		try {
			if(!s3.doesBucketExistV2(bucketName)) {
				s3.createBucket(bucketName);
			}
		} catch (AmazonS3Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
