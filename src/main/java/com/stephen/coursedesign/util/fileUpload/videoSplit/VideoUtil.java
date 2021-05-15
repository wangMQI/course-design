package com.stephen.coursedesign.util.fileUpload.videoSplit;


import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class VideoUtil {


    private final String RESOLUTION = "1280x720";

    private final String HIGH_BIT_RATE = "1024k";

    private final String MEDIUM_BIT_RATE = "512k";


    private final String LOW_BIT_RATE = "256k";

    public VideoFfmpegUtil videoFfmpegUtil; //视频转码类

    private VideoSplit videoSplit; //视频切片处理类

    /**
     *
     * @author:Stephen Wang
     * @date 2021-05-14  16:41:25
     * @Param:
     * @param inputVideo 输入视频url
     * @param videoId       视频Id
     * @return 视频mpd文件路径
     **/

    public String getVideoMpdPath(String inputVideo,String videoId) throws IOException {

        log.info("此时要处理视频地址："+ inputVideo);
        log.info("此时要处理视频Id："+ videoId);

        videoFfmpegUtil = new VideoFfmpegUtil();
        videoSplit = new VideoSplit();

        String [] videoList = new String[4];

        String videoVoice;

        videoList[0] = videoFfmpegUtil.transFormVideo(videoId,inputVideo,RESOLUTION,HIGH_BIT_RATE);
        videoList[1] = videoFfmpegUtil.transFormVideo(videoId,inputVideo,RESOLUTION,MEDIUM_BIT_RATE);
        videoList[2] = videoFfmpegUtil.transFormVideo(videoId,inputVideo,RESOLUTION,LOW_BIT_RATE);
        //获得音频文件
        videoList[3] = videoFfmpegUtil.getVideoVoice(videoId,inputVideo,MEDIUM_BIT_RATE);


        String [] videoFragList = new String[4];

        videoFragList[0] = videoSplit.getVideoFragment(videoList[0]);
        videoFragList[1] = videoSplit.getVideoFragment(videoList[1]);
        videoFragList[2] = videoSplit.getVideoFragment(videoList[2]);
        videoFragList[3] = videoSplit.getVideoFragment(videoList[3]);



        return videoSplit.getVideoMpd(videoFragList,videoId);
    }



}
