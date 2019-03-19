package com.cc.SQS;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.AmazonSQSException;
import com.amazonaws.services.sqs.model.CreateQueueRequest;

public class QueueVideoObjectService {

	private static final String QUEUE_NAME = "VideoObjectRequestQueue";
    public void createQueue() {
        final AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
        try {
            CreateQueueRequest createQueueRequest =
            		new CreateQueueRequest(QUEUE_NAME)
                    .addAttributesEntry("DelaySeconds", "60")
                    .addAttributesEntry("MessageRetentionPeriod", "86400");

            String myQueueUrl = sqs.createQueue(createQueueRequest)
                    .getQueueUrl();
            System.out.println(myQueueUrl);
        } catch (AmazonSQSException e) {
            if (!e.getErrorCode().equals("QueueAlreadyExists")) {
                throw e;
            }
        }
    }

}
