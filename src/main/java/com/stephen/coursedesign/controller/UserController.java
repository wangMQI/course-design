package com.stephen.coursedesign.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stephen.coursedesign.comment.base.BaseController;
import com.stephen.coursedesign.comment.consts.QueryWrapperConst;
import com.stephen.coursedesign.comment.consts.entity.SoulTableParam;
import com.stephen.coursedesign.entity.*;
import com.stephen.coursedesign.service.IFileRecordService;
import com.stephen.coursedesign.service.UserService;
import com.stephen.coursedesign.service.VideoService;
import com.stephen.coursedesign.util.annotation.VerifyToken;
import com.stephen.coursedesign.util.result.Code;
import com.stephen.coursedesign.util.result.Result;
import com.stephen.coursedesign.comment.entity.ResultV;
import com.stephen.coursedesign.util.result.response.UserMessageResponse;
import com.stephen.coursedesign.util.result.response.user.UserResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: course-design
 * @author: Stephen·Wang
 * @date: 2021/4/18 12:05
 * @Version:
 * @Description:
 */
@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class UserController{


    @Autowired
    private UserService userService;

    @Autowired
    private VideoService videoService;

    @Autowired
    private IFileRecordService fileRecordService;


    /**
     * 用户登录
     */
    @PostMapping(value = "/userLogin", produces = "application/json;charset=UTF-8")
    @CrossOrigin(origins = "*")
    public Result userLogin(@RequestParam("userPhone") String userPhone,
                        @RequestParam("password") String password) {
        UserResponseBody user = userService.loginVerify(userPhone);
        return password.equals(user.getPassword())?new Result(Code.Success,user):new Result(Code.Fail);

    }


    /**
     * 注册(添加用户)
     */
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/userRegist", produces = "application/json;charset=UTF-8")
    public Result register(User user) {
        return userService.userRegist(user);
    }

    /**获取用户信息
     *
     */
    @VerifyToken
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/getUserInfo", produces = "application/json;charset=UTF-8")
    public Result getUserInfo(String userPhone){
        return new Result();
    }

    /**发表评论
     *
     * @param userId
     * @param videoId
     * @param parentId
     * @param comment
     * @return
     */
    @VerifyToken
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/addUserComment", produces = "application/json;charset=UTF-8")
    public Result userAddComment(@RequestParam("userId") String userId,
                                 @RequestParam("videoId") String videoId,
                                 @RequestParam("parentId") String parentId,
                                 @RequestParam("comment") String comment) {
        return userService.addComment(userId,videoId,parentId,comment)?new Result(Code.Success):new Result(Code.Fail);
    }

    /**获取用户评论
     *
     * @param commentIds
     * @return
     */
    @VerifyToken
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/getUserComment", produces = "application/json;charset=UTF-8")
    public Result userGetComment(@RequestParam String commentIds) {
        log.info("当前参数 " + commentIds);
        List<Comment>list = userService.getUsersComment(commentIds);
        return list !=null?new Result(Code.Success,list):new Result(Code.Fail);
    }

    /**
     * 用户向其他用户发送消息
     */
    @PostMapping(value = "/sendMessage", produces = "application/json;charset=UTF-8")
    public Result sendMessage(@RequestParam String sender, @RequestParam String recever,@RequestParam String msg) {
        return userService.sendMessage(sender,recever,msg)?new Result(Code.Success):new Result(Code.Fail);
    }

    /**获取用户消息
     *
     * @param userId
     * @return
     */
    @VerifyToken
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/getUserMessage", produces = "application/json;charset=UTF-8")
    public Result userGetMessage(@RequestParam String userId) {
        log.info("当前参数 " + userId);
        List<UserMessageResponse> responseList = userService.getUserMessage(userId);
        return responseList !=null?new Result(Code.Success,responseList):new Result(Code.Fail);
    }








    /*
    *//**上传视频
     *
     * @param video
     * @return
     *//*
    @PostMapping(value = "/uploadFile", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Result loadVideo(Video video) {
        return userService.upload(video) ? new Result(Code.Upload_Success) : new Result(Code.Upload_Fail);
    }*/

    /**返回该用户所有视频
     *
     * @param userPhone
     * @return
     */
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/getVideos", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Result getVideoList(String userPhone) {
        List<Video> list = videoService.getUserVideo(userPhone);
        return list.size() >= 1 ? new Result(Code.Success, list) : new Result(Code.Fail);
    }



    /**
     *按搜索信息搜索用户
     */
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/searchVideo", produces = "application/json;charset=UTF-8")
    public Result searchVideo(@RequestParam String videoDesc){
        List<Video> list = userService.searchVideo(videoDesc);
        return list.size()>0?new Result(Code.Success,list):new Result(Code.Fail);
    }

    /**
     * 上传头像
     */
    @PostMapping(value = "/changeAvatar", produces = "application/json;charset=UTF-8")
    public Result uploadImage(Image image, HttpSession session){
        image.setUserId(String.valueOf(session.getAttribute("userId")));

        return userService.changeUserAvatar(image)?new Result(Code.Success):new Result(Code.Fail);

    }





}
