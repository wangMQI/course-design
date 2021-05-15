package com.stephen.coursedesign.util.fileUpload.videoSplit;

import lombok.extern.slf4j.Slf4j;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;


/**
 * @program: course-design
 * @author: Stephen·Wang
 * @date: 2021/5/14 10:50
 * @Version:
 * @Description:视频转码
 */
@Slf4j
public class VideoFfmpegUtil {

    private final String ffmpegExePath = "C:\\Program Files\\ffmpeg\\bin\\ffmpeg.exe";

    private final String baseOutputPath = "D:\\videoShareDb\\videoFragment";

    public String transFormVideo(String videoId,String inputFile,String resolution,String bitRate) throws IOException {
        List<String> command = new ArrayList<String>();

        String outputPath = getOutputPath(videoId,bitRate);



        log.info("转换不同码率函数输入文件参数：" + inputFile);

        // ffmpeg -i video.mp4 -s 320x180 -c:v libx264 -b:v 500k -g 90 -an video_320x180_500k.mp4

        command.add(ffmpegExePath);

        command.add("-i");

        command.add(inputFile);

        command.add("-s");

        command.add(resolution);

        command.add("-c:v");

        command.add("libx264");

        command.add("-b:v");

        command.add(bitRate);

        command.add("-g");

        command.add("90");

        command.add("-an");

        command.add(outputPath);

        CmdUtils.runCmd(command);

        return outputPath;

    }

    //  ffmpeg -i video.mp4 -c:a aac -b:a 500k -vn -strict -2 video_audio_500k.mp4

    public String getVideoVoice(String videoId,String inputFile,String bitRate) throws IOException {

        log.info("转换视频音频函数输入文件参数：" + inputFile);

        List<String> command = new ArrayList<String>();

        //  ffmpeg -i video.mp4 -c:a aac -b:a 500k -vn -strict -2 video_audio_500k.mp4
        String outputPath = getOutputPath(videoId,bitRate);

        outputPath = outputPath.substring(0,outputPath.lastIndexOf('.'))+"_audio.mp4";

        command.add(ffmpegExePath);

        command.add("-i");

        command.add(inputFile);

        command.add("-c:a");

        command.add("aac");

        command.add("-b:a");

        command.add(bitRate);

        command.add("-vn");

        command.add("-strict");

        command.add("-2");

        command.add(outputPath);

        CmdUtils.runCmd(command);




        return outputPath;

    }

    public String getOutputPath(String videoId,String bitRate) throws IOException{
        StringBuffer stb = new StringBuffer();
        stb.append(baseOutputPath)
                .append("\\")
                .append(String.valueOf(System.currentTimeMillis()))
                .append("\\")
                .append(videoId)
                .append("\\")
                .append(videoId)
                .append("_")
                .append(bitRate)
                .append(".mp4");

        String out = stb.toString();

        //创建输出目录
        Path path= Paths.get(out);
        Files.createDirectories(path.getParent());

        return out;
    }

}
