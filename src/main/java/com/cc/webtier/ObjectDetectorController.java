package com.cc.webtier;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cc.S3.FetchObject;
import com.cc.S3.UploadObject;
import com.cc.SQS.QueueVideoObjectProducer;

@RestController
public class ObjectDetectorController {

	@Autowired
	public UploadObject uploadObject;
	@Autowired
	public VideoService videoService;
	@Autowired
	public QueueVideoObjectProducer objectProducer;
	@Autowired 
	public FetchObject fetchObject;
	
	@RequestMapping(value="/detectObjectFromVideo", method=RequestMethod.GET, produces="text/plain")
	public String getObject() {
		String error = null;
		ExecutorService executorService = Executors.newFixedThreadPool(100);
		Future<String> result =  executorService.submit(new ObjectDetectorControllerThread(uploadObject, 
				videoService, objectProducer, fetchObject));
		try {
			return result.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Object could not be detected";
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			error= e.getMessage();
			return "Object could not be detected";

		}
	}
}