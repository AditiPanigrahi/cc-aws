package com.cc.webtier;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;

import com.cc.S3.DeleteObject;
import com.cc.S3.FetchObject;
import com.cc.S3.UploadObject;
import com.cc.SQS.QueueVideoObjectProducer;

public class ObjectDetectorControllerThread implements Callable<String> {

	private static final String VIDEO_FETCHED_SUCCESFULLY = "Video fetched succesfully";
	private static final String FILE_UPLOAD_SUCCESSFUL = "File Upload successful";
	private static final String OBJECT_UPLOAD_SUCCESSFUL = "Object upload Successful";
	private static final String TIMEOUT_DUE_TO_DELAY_IN_OBJECT_DETECTION = "Timeout due to delay in object detection";
	public UploadObject uploadObject;
	public VideoService videoService;
	public QueueVideoObjectProducer objectProducer;
	public FetchObject fetchObject;
	public DeleteObject deleteObject;

	private String S3_BUCKET_NAME_FETCH;

	private String S3_BUCKET_NAME_UPLOAD;

	public ObjectDetectorControllerThread(UploadObject uo, VideoService vo, QueueVideoObjectProducer qvop
			,FetchObject fo, DeleteObject dob, String queueUpload, String queueFetch) {
		this.uploadObject = uo;
		this.videoService = vo;
		this.fetchObject = fo;
		this.objectProducer = qvop;
		this.deleteObject = dob;
		this.S3_BUCKET_NAME_UPLOAD = queueUpload;
		this.S3_BUCKET_NAME_FETCH = queueFetch;
	}
	public String call() {
		String object = null;
		File file = videoService.getVideo();
		System.out.println(object);

		System.out.println(VIDEO_FETCHED_SUCCESFULLY+ file.getName());
		if(file != null) {
			try {
				uploadObject.uploadObject(S3_BUCKET_NAME_UPLOAD, file);
				System.out.println(FILE_UPLOAD_SUCCESSFUL);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			objectProducer.putVideoObjectInQueue(file.getName());
		}
		Long starttime = System.currentTimeMillis();;
		Long endTime = System.currentTimeMillis();
		try {
			starttime = System.currentTimeMillis();;
			endTime = System.currentTimeMillis();
			while(object==null && !(endTime-starttime>120000)) {
				System.out.println("starttime : " +starttime);
				System.out.println("endtime :" + endTime);
				object=fetchObject.fetchObject(S3_BUCKET_NAME_FETCH, file);
				endTime = System.currentTimeMillis();
			}
		deleteObject.deleteObject(S3_BUCKET_NAME_UPLOAD, file.getName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		if(endTime-starttime>120000) {
			return TIMEOUT_DUE_TO_DELAY_IN_OBJECT_DETECTION;
		}
		return object;
	}

}