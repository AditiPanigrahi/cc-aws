package com.cc.S3;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.S3Object;

public class FetchObject {
	public String fetchObject(File file) throws IOException {
		final AmazonS3  s3 = AmazonS3ClientBuilder.defaultClient();
		String bucketName = "video-object-pair-bucket";
		String key_Name = file.getName();
		try {
			S3Object object = s3.getObject(bucketName, key_Name);
			System.out.println(object);
			if(object!=null) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(object.getObjectContent()));
				String line = null;
				while ((line = reader.readLine()) != null) {
					return line.toString();
				}
				System.out.println();
			}
		} catch (AmazonS3Exception e) {
			//System.out.println(e.getMessage());
		}
		return null;
	}
}
