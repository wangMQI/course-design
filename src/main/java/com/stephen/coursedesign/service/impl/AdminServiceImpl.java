package com.stephen.coursedesign.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stephen.coursedesign.entity.Admin;
import com.stephen.coursedesign.entity.User;
import com.stephen.coursedesign.entity.mapper.AdminMapper;
import com.stephen.coursedesign.interceptor.TokenUtils;
import com.stephen.coursedesign.service.AdminService;
import com.stephen.coursedesign.service.CommentService;
import com.stephen.coursedesign.service.UserService;
import com.stephen.coursedesign.util.result.Code;
import com.stephen.coursedesign.util.result.Result;
import com.stephen.coursedesign.util.result.response.admin.AdminResponseBody;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @program: course-design
 * @author: Stephen·Wang
 * @date: 2021/4/18 12:05
 * @Version:
 * @Description:
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @Override
    public boolean changeUserStatus(String userPhone, String status) {
        return false;
    }

    @Override
    public boolean feeezeUser(String uid) {
        return userService.changeUserState(Integer.valueOf(uid),1);
    }

    @Override
    public boolean deleteUser(String uid) {
        return userService.changeUserState(Integer.valueOf(uid),-1);
    }

    @Override
    public boolean searchVideo(String videoInfo) {
        return false;
    }

    @Override
    public boolean deleteVideo(String videoId) {
        return false;
    }

    @Override
    public boolean deleteComment(String commentId) {
        return commentService.deleteComment(commentId);
    }

    @Override
    public boolean addAdmin(Admin admin) {
        return this.save(admin);
    }

    @Override
    public boolean deleteAdmin(String adminId) {
        return false;
    }

    @Override
    public Result login(String adminId, String password) {
        Admin admin = this.getById(adminId);
        if (admin != null && password.equals(admin.getPassword())) {
            AdminResponseBody responseBody = new AdminResponseBody();
            BeanUtils.copyProperties(admin,responseBody);
            responseBody.setToken(TokenUtils.getToken(adminId,password));
            return new Result(Code.Success,responseBody);
        }
        return new Result(Code.Fail);
    }



    public boolean getAdmin(String adminId, String password){

        return false;

    }

    // public User searchUserInfo(String nickName){
    //     return userService.getUser(nickName);
    // }

    @Override
    public User searchUser(String nickName){
        return userService.getUser(nickName);
    }

}
