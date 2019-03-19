package com.cc.webtier;

import org.springframework.beans.factory.annotation.Autowired;
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
  
  @Override
  public void onApplicationEvent(final ApplicationReadyEvent event) {
 
    queueService.createQueue();
    createS3Bucket.createBucket();
  }
 
} 
