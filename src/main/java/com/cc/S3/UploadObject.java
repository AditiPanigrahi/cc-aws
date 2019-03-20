package com.cc.S3;

import java.io.File;
import java.io.IOException;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;

public class UploadObject {
	
	final AmazonS3  s3 = AmazonS3ClientBuilder.defaultClient();

	public void uploadObject(String bucketName, File file) throws IOException {
		String key_Name = file.getName();
		try {
			s3.putObject (bucketName, key_Name, file);
			System.out.println("File uploaded successfully"+key_Name);
		} catch (AmazonS3Exception e) {
			System.out.println(e.getMessage());
		}
    }
	public void uploadFinalRes(String bucketName, String fileName, String result) throws IOException {
		try {
			s3.putObject (bucketName, fileName, result);
			System.out.println("File uploaded successfully"+fileName);
		} catch (AmazonS3Exception e) {
			System.out.println(e.getMessage());
		}
    }

}
