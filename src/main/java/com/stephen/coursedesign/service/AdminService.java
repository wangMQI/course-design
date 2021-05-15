package com.stephen.coursedesign.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.stephen.coursedesign.entity.Admin;
import com.stephen.coursedesign.entity.User;
import com.stephen.coursedesign.util.annotation.VerifyToken;
import com.stephen.coursedesign.util.result.Result;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @program: course-design
 * @author: Stephen·Wang
 * @date: 2021/4/18 12:04
 * @Version:
 * @Description:
 */
public interface AdminService extends IService<Admin> {


    Result login(String adminId, String password);



    boolean changeUserStatus(String userPhone, String status);


    boolean feeezeUser(String uid);


    boolean deleteUser(String uid);


    User searchUser(String userInfo);


    boolean searchVideo(String videoInfo);


    boolean deleteVideo(String videoId);



    boolean deleteComment(String commentId);



    boolean addAdmin(Admin admin);


    boolean deleteAdmin(String adminId);


}
