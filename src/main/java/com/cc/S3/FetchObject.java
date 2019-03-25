package com.cc.S3;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.util.StreamUtils;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.S3Object;

public class FetchObject {
	public String fetchObject(String bucketName, File file) throws IOException {
		final AmazonS3  s3 = AmazonS3ClientBuilder.defaultClient();
		String key_Name = file.getName();
		try {
			S3Object object = s3.getObject(bucketName, key_Name);
			System.out.println(object);
			if(object!=null && object.getObjectContent() != null) {
				String objectDetected = StreamUtils.copyToString(object.getObjectContent(), StandardCharsets.UTF_8);
				System.out.println(objectDetected);
				return objectDetected;
			}
		} catch (AmazonS3Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
}
