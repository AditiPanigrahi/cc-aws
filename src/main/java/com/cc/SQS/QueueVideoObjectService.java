package com.cc.SQS;

import org.springframework.beans.factory.annotation.Value;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.AmazonSQSException;
import com.amazonaws.services.sqs.model.CreateQueueRequest;

public class QueueVideoObjectService {

	private static final String QUEUE_ALREADY_EXISTS = "QueueAlreadyExists";
	private static final String MESSAGE_RETENTION_PERIOD = "MessageRetentionPeriod";
	private static final String DELAY_SECONDS = "DelaySeconds";
	@Value("${sqs.queue.name}")
	private String QUEUE_NAME;
	
	//private static final String QUEUE_NAME = "VideoObjectRequestQueue";
    public void createQueue() {
        final AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
        try {
            CreateQueueRequest createQueueRequest =
            		new CreateQueueRequest(QUEUE_NAME)
                    .addAttributesEntry(DELAY_SECONDS, "60")
                    .addAttributesEntry(MESSAGE_RETENTION_PERIOD, "86400");

            String myQueueUrl = sqs.createQueue(createQueueRequest)
                    .getQueueUrl();
            System.out.println(myQueueUrl);
        } catch (AmazonSQSException e) {
            if (!e.getErrorCode().equals(QUEUE_ALREADY_EXISTS)) {
                throw e;
            }
        }
    }

}
