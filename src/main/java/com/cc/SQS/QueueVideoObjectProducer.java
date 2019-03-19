package com.cc.SQS;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;

public class QueueVideoObjectProducer {

	private static final String QUEUE_NAME = "VideoObjectRequestQueue";
	public void putVideoObjectInQueue(String requestId) {
        final AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
        String queueUrl = sqs.getQueueUrl(QUEUE_NAME).getQueueUrl();
        SendMessageRequest sendMsgRqst = new SendMessageRequest()
                .withQueueUrl(queueUrl)
                .withMessageBody(requestId)
                .withDelaySeconds(5);
        sqs.sendMessage(sendMsgRqst);
        System.out.println("Request in queue for request id"+requestId);
    }


}
