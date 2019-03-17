package com.cc.awsproject;

import com.cc.apptier.VideoService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        VideoService video = new VideoService();
        System.out.println(video.getVideo());
    }
}
