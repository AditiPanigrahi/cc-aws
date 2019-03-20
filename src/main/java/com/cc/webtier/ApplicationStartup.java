package com.cc.webtier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.cc.S3.CreateS3Bucket;
import com.cc.SQS.QueueVideoObjectService;

@Component
public class ApplicationStartup 
implements ApplicationListener<ApplicationReadyEvent> {

	/**
	 * This event is executed as late as conceivably possible to indicate that 
	 * the application is ready to service requests.
	 */
	@Autowired
	CreateS3Bucket createS3Bucket;

	@Autowired
	QueueVideoObjectService queueService;

	@Value("${s3.fileName.ObjectDetected.bucket.name}")
	private String S3_BUCKET_NAME_FETCH;

	@Value("${s3.fileObject.bucket.name}")
	private String S3_BUCKET_NAME_UPLOAD;

	@Override
	public void onApplicationEvent(final ApplicationReadyEvent event) {

		queueService.createQueue();
		createS3Bucket.createBucket(S3_BUCKET_NAME_UPLOAD);
		createS3Bucket.createBucket(S3_BUCKET_NAME_FETCH);
	}

} 
