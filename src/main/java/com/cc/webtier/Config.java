package com.cc.webtier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.cc.S3.CreateS3Bucket;
import com.cc.S3.FetchObject;
import com.cc.S3.UploadObject;
import com.cc.SQS.QueueVideoObjectProducer;
import com.cc.SQS.QueueVideoObjectService;

@Configuration
@ComponentScan
public class Config {
    @Bean
    public CreateS3Bucket CreateS3Bucket() {
    	return new CreateS3Bucket();
    }
    @Bean
    public QueueVideoObjectService QueueVideoObjectService() {
    	return new QueueVideoObjectService();
    }
    @Bean
    public QueueVideoObjectProducer queueVideoObjectProducer() {
    	return new QueueVideoObjectProducer();
    }
    
    @Bean
    public UploadObject UploadObject() {
    	return new UploadObject();
    }
   
    @Bean
    public VideoService VideoService() {
    	return new VideoService();
    }
    
    @Bean
    public FetchObject FetchObject() {
    	return new FetchObject();
    }
   
}