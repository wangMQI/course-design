package com.stephen.coursedesign.util.fileUpload.videoSplit;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: course-design
 * @author: Stephen·Wang
 * @date: 2021/5/14 14:27
 * @Version:
 * @Description:生成mpd文件
 */

@Slf4j
public class VideoSplit {

    private final String mp4fragmentPath = "C:\\Program Files\\bento4\\bin\\mp4fragment.exe";

    private final String mp4DashPath = "C:\\Program Files\\bento4\\bin\\mp4dash.bat";

    /**
     *
     * @author:Stephen Wang
     * @date 2021-05-14  16:49:58
     * @Param:
     * @param inputFile 要处理文件的绝对路径
     * @return
     **/

    public String getVideoFragment(String inputFile) throws IOException {

        log.info("获取视频切片函数输入文件参数：" + inputFile);

        Path path= Paths.get(inputFile);
        String videoName = path.getFileName().toString();

        StringBuffer outputFile = new StringBuffer();

        //为视频文件名添加”flag“后缀
        outputFile.append(path.getParent())
                .append(videoName.substring(0,videoName.lastIndexOf('.')))
                .append('_')
                .append("frag")
                .append(".mp4");


        // mp4fragment video_320x180_250k.mp4 video_320x180_250k_f.mp4

        List<String> command = new ArrayList<String>();

        command.add(mp4fragmentPath);

        //添加片段持续时间

        command.add("--fragment-duration");

        command.add("35000");

        command.add(inputFile);

        command.add(outputFile.toString());

        CmdUtils.runCmd(command);

/*        try {
            //开始执行命令

            Process process = builder.start();

            //如果你想获取到执行完后的信息，那么下面的代码也是需要的

            StringBuffer sbf = new StringBuffer();

            String line = null;

            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));

            while ((line = br.readLine()) != null) {

                sbf.append(line);

                sbf.append(" ");

            }

            String resultInfo = sbf.toString();

            System.out.println(resultInfo);

        } catch (IOException e) {

            e.printStackTrace();

        }*/

        return outputFile.toString();
    }


     //mp4dash video_320x180_250k_f.mp4 video_320x180_500k_f.mp4 video_320x180_750k_f.mp4 video_audio500k.f.mp4



    /**
     *
     * @author:Stephen Wang
     * @date 2021-05-14  16:43:31
     * @Param:
     * @param videoList 视频分片后绝对路径地址
     * @return 视频mpd文件绝对路径
     **/

    public String getVideoMpd(String[] videoList,String videoId) throws IOException {

        log.info("获取最终mpd文件函数输入文件参数：" + Arrays.toString(videoList));

        Path path= Paths.get(videoList[0]);
        String parentPath = path.getParent().toString();

        String outputPath = parentPath+"\\"+videoId;

        List<String> command = new ArrayList<String>();


        command.add(mp4DashPath);

        command.add("--output-dir");

        command.add(outputPath);

        for (int i = 0; i < videoList.length; i++) {
            command.add(videoList[i]);
        }



        CmdUtils.runCmd(command);

/*
        try {
            //开始执行命令

            Process process = builder.start();

            //如果你想获取到执行完后的信息，那么下面的代码也是需要的

            StringBuffer sbf = new StringBuffer();

            String line = null;

            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));

            while ((line = br.readLine()) != null) {

                sbf.append(line);

                sbf.append(" ");

            }

            String resultInfo = sbf.toString();

            System.out.println(resultInfo);

        } catch (IOException e) {

            e.printStackTrace();

        }*/


        return new StringBuffer()
                .append(outputPath)
                .append("\\")
                .append("stream.mpd")
                .toString();




    }
}
