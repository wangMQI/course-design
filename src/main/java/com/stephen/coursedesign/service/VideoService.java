package com.stephen.coursedesign.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.stephen.coursedesign.entity.User;
import com.stephen.coursedesign.entity.Video;

import java.util.List;

/**
 * @program: course-design
 * @author: Stephen·Wang
 * @date: 2021/4/18 15:11
 * @Version:
 * @Description:
 */
public interface VideoService extends IService<Video> {

    boolean upload(Video video);

    List<Video> getUserVideo(String userPhone);

    List<Video> searchVideo(String videoDesc);

}
