package com.stephen.coursedesign.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.stephen.coursedesign.entity.Image;

/**
 * @program: course-design
 * @author: Stephen·Wang
 * @date: 2021/4/28 19:47
 * @Version:
 * @Description:
 */
public interface ImageService extends IService<Image> {

    /**
     * 上传图片
     * @param image
     * @return
     */
    boolean uploadImage(Image image);

    /**
     * 返回用户图片
     * @param id
     * @return
     */
    Image getUserAvatarById(String id);
}
