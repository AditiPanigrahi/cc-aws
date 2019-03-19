package com.cc.S3;

import java.io.File;
import java.io.IOException;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;

public class UploadObject {
	public void uploadObject(File file) throws IOException {
		final AmazonS3  s3 = AmazonS3ClientBuilder.defaultClient();
		String bucketName = "video-file-bucket";
		String key_Name = file.getName();
		try {
			s3.putObject(bucketName, key_Name, file);
		} catch (AmazonS3Exception e) {
			System.out.println(e.getMessage());
		}
    }

}
