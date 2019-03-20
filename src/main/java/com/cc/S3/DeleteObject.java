package com.cc.S3;

import java.io.File;
import java.io.IOException;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;

public class DeleteObject {
	
	final AmazonS3  s3 = AmazonS3ClientBuilder.defaultClient();

	public void deleteObject(String bucketName, String file) throws IOException {
		try {
			s3.deleteObject(bucketName, file);
			System.out.println("File Deleted successfully"+file);
		} catch (AmazonS3Exception e) {
			System.out.println(e.getMessage());
		}
    }

}
