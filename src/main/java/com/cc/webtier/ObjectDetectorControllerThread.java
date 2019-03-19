package com.cc.webtier;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;

import com.cc.S3.FetchObject;
import com.cc.S3.UploadObject;
import com.cc.SQS.QueueVideoObjectProducer;

public class ObjectDetectorControllerThread implements Callable<String> {
	
	public UploadObject uploadObject;
	public VideoService videoService;
	public QueueVideoObjectProducer objectProducer;
	public FetchObject fetchObject;
	public ObjectDetectorControllerThread(UploadObject uo, VideoService vo, QueueVideoObjectProducer qvop
			,FetchObject fo) {
		this.uploadObject = uo;
		this.videoService = vo;
		this.fetchObject=fo;
		this.objectProducer = qvop;
		}
		public String call() {
			String object = null;
			File file = videoService.getVideo();
			System.out.println(object);

			System.out.println("Video fetched"+ file.getName());
			if(file != null) {
				try {
					uploadObject.uploadObject(file);
					System.out.println("fILE put in S3");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				objectProducer.putVideoObjectInQueue(file.getName());
			}
			System.out.println("Object put in SQS queue complete");
			Long starttime = System.currentTimeMillis();;
			Long endTime = System.currentTimeMillis();
			try {
				starttime = System.currentTimeMillis();;
				endTime = System.currentTimeMillis();
				while(object==null && !(endTime-starttime>120000)) {
					object=fetchObject.fetchObject(file);
					endTime = System.currentTimeMillis();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
			if(endTime-starttime>120000) {
				return "Timeout due to delay in object detection";
			}
			return object;
		}

}