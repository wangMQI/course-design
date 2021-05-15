package com.stephen.coursedesign.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stephen.coursedesign.entity.Image;
import com.stephen.coursedesign.entity.Video;
import com.stephen.coursedesign.entity.mapper.ImageMapper;
import com.stephen.coursedesign.service.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: course-design
 * @author: Stephen·Wang
 * @date: 2021/4/28 19:49
 * @Version:
 * @Description:
 */
@Service
public class ImageServiceImpl extends ServiceImpl<ImageMapper, Image> implements ImageService {


    private String url;


    @Override
    public Image getUserAvatarById(String id) {
        return super.getById(id);
    }


    @Override
    public boolean uploadImage(Image image) {
        if (uploadToServer((MultipartFile) image)){
            image.setImageUrl(url);
            return this.save(image);
        }
        return false;
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

            url="http://localhost:8088/uploadImagess/"+fileName;


        } catch (IOException e) {
            return false;
        }

        return true;
    }
}
