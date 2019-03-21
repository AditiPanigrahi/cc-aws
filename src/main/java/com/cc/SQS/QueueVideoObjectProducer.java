package com.cc.SQS;

import org.springframework.beans.factory.annotation.Value;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;

public class QueueVideoObjectProducer {

	@Value("${sqs.queue.name}")
	private String QUEUE_NAME;
    final AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();

	//private static final String QUEUE_NAME = "VideoObjectRequestQueue";
	public void putVideoObjectInQueue(String requestId) {
        String queueUrl = sqs.getQueueUrl(QUEUE_NAME).getQueueUrl();
        SendMessageRequest sendMsgRqst = new SendMessageRequest()
                .withQueueUrl(queueUrl)
                .withMessageBody(requestId)
                .withDelaySeconds(5);
        sqs.sendMessage(sendMsgRqst);
        System.out.println("Request in queue for request id"+requestId);
    }


}
