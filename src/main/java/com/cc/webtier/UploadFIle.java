package com.cc.webtier;

import java.io.File;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;

public class UploadFIle {



	public static void main(String[] args) {
		final AmazonS3  s3 = AmazonS3ClientBuilder.defaultClient();
		String bucketName = "myfirstbucketakash1";
		String key_Name = "video-pie1-061910.h264";
		String file_path = "C:\\video-pie1-061910.h264";
		try {
		
		} catch (AmazonS3Exception e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}


}
