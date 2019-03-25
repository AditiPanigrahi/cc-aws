package com.cc.webtier;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cc.S3.DeleteObject;
import com.cc.S3.FetchObject;
import com.cc.S3.UploadObject;
import com.cc.SQS.QueueVideoObjectProducer;

@RestController
public class ObjectDetectorController {

	private static final String ERROR_MSG = "Object could not be detected due to ";
	@Autowired
	public UploadObject uploadObject;
	@Autowired
	public VideoService videoService;
	@Autowired
	public QueueVideoObjectProducer objectProducer;
	@Autowired 
	public FetchObject fetchObject;
	
	@Autowired 
	public DeleteObject deleteObject;
	
	@Value("${s3.fileName.ObjectDetected.bucket.name}")
	private String S3_BUCKET_NAME_FETCH;

	@Value("${s3.fileObject.bucket.name}")
	private String S3_BUCKET_NAME_UPLOAD;
	
	@Value("${request.timeout}")
	private long timeout;
	
	@RequestMapping(value="/detectObjectFromVideo", method=RequestMethod.GET, produces="text/plain")
	public String getObject() {
		Long startTime = System.currentTimeMillis();;
		String error = null;
		ExecutorService executorService = Executors.newFixedThreadPool(100);
		Future<String> result =  executorService.submit(new ObjectDetectorControllerThread(uploadObject, 
				videoService, objectProducer, fetchObject, deleteObject, S3_BUCKET_NAME_UPLOAD, S3_BUCKET_NAME_FETCH,
				timeout));
		try {
			Long timeTaken = System.currentTimeMillis()-startTime;
			return result.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			error = e.getMessage();
			return ERROR_MSG + error;
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			error= e.getMessage();
			return ERROR_MSG + error;

		}
	}
}