package com.stephen.coursedesign.util.fileUpload.videoSplit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: course-design
 * @author: Stephen·Wang
 * @date: 2021/5/15 0:07
 * @Version:
 * @Description:控制命令行封装类
 */
public class CmdUtils {

    public static void convertor(String videoId,String inputFile,String resolution,String bitRate) throws Exception {

        List<String> command = new ArrayList<>();

        command.add("C:\\Program Files\\ffmpeg\\bin\\ffmpeg.exe");

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

        command.add("D:\\videoShareDb\\ffmpeg\\122_512K.mp4");


    }


    public static void runCmd( List<String> command) throws IOException {
        InputStream errorStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader br = null;
        try {
            ProcessBuilder builder = new ProcessBuilder(command);
            Process process = builder.start();

            errorStream = process.getErrorStream();
            inputStreamReader = new InputStreamReader(errorStream);
            br = new BufferedReader(inputStreamReader);
            while (br.readLine() != null) {
            }
        } finally {
            if (br != null) {
                br.close();
            }
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            if (errorStream != null) {
                errorStream.close();
            }

        }
    }

    public static void main(String[] args) throws Exception {
        convertor("112","D:\\testVideo\\test001.mp4",  "1280x720", "512K");
    }


}
