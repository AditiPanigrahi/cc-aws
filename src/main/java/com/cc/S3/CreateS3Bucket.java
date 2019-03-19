package com.cc.S3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;

public class CreateS3Bucket {

	public void createBucket()
	
	
	{
		final AmazonS3  s3 = AmazonS3ClientBuilder.defaultClient();
		String bucketName = "video-file-bucket";
		try {
			if(!s3.doesBucketExistV2(bucketName)) {
				s3.createBucket(bucketName);
			}
		} catch (AmazonS3Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
