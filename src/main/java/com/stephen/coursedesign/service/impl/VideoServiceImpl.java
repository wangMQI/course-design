package com.stephen.coursedesign.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stephen.coursedesign.entity.Video;
import com.stephen.coursedesign.entity.mapper.VideoMapper;
import com.stephen.coursedesign.service.VideoService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @program: course-design
 * @author: Stephen·Wang
 * @date: 2021/4/18 15:09
 * @Version:
 * @Description:
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

    private String  url;

    @Override
    public boolean upload(Video video) {
        if (uploadToServer((MultipartFile) video)){
            video.setVideoUrl(url);
            return this.save(video);
        }
        return false;
    }




    @Override
    public List<Video> getUserVideo(String userPhone){
        QueryWrapper<Video> wrapper = new QueryWrapper<>();
        wrapper.eq(userPhone,userPhone);
        return this.list(wrapper);
    }

    @Override
    public List<Video> searchVideo(String videoDesc){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.like("title",videoDesc);
        return this.list(wrapper);
    }




    public boolean uploadToServer(MultipartFile file) {

        //System.out.print("上传文件==="+"\n");
        //判断文件是否为空
        if (file.isEmpty()) {
            return false;
        }

        // 获取文件名
        String fileName = file.getOriginalFilename();

        fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName;

        //加个时间戳，尽量避免文件名称重复
        String path = "D:/courseDesign/upload/" +fileName;
        //String path = "E:/fileUpload/" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName;

        //创建文件路径
        File dest = new File(path);

        //判断文件是否已经存在
        if (dest.exists()) {
            return false;
        }

        //判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }

        try {
            //上传文件
            file.transferTo(dest); //保存文件

            url="http://localhost:8088/uploadVideos/"+fileName;


        } catch (IOException e) {
            return false;
        }

        return true;
    }


}
