package com.stephen.coursedesign.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stephen.coursedesign.entity.*;
import com.stephen.coursedesign.entity.mapper.UserMapper;
import com.stephen.coursedesign.interceptor.TokenUtils;
import com.stephen.coursedesign.service.*;

import com.stephen.coursedesign.util.result.Code;
import com.stephen.coursedesign.util.result.Result;
import com.stephen.coursedesign.util.result.response.UserMessageResponse;
import com.stephen.coursedesign.util.result.response.user.UserResponseBody;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @program: course-design
 * @author: Stephen·Wang
 * @date: 2021/4/18 12:05
 * @Version:
 * @Description:
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

    @Autowired
    private CommentService commentService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private VideoService videoService;

    @Autowired
    private ImageService imageService;


    @Override
    public String verifyPasswordByUsername(String nickName) {
        QueryWrapper<User> wrapper = new QueryWrapper();
        wrapper.eq(nickName,nickName);
        User u = getOne(wrapper);
        return u !=null ? u.getPassword() : "";
    }

    @Override
    public String verifyPasswordByUserPhone(String userPhone) {

        QueryWrapper<User> wrapper = new QueryWrapper();
        wrapper.eq("phone",userPhone);
        User u = getOne(wrapper);
        return u !=null ? u.getPassword() : "";
    }

    //登陆确认
    @Override
    public UserResponseBody loginVerify(String userPhone) {

        UserResponseBody responseBody = new UserResponseBody();

        QueryWrapper<User> wrapper = new QueryWrapper();
        wrapper.eq("phone",userPhone);
        User u = getOne(wrapper);
        BeanUtils.copyProperties(u,responseBody);
        responseBody.setToken(TokenUtils.getToken(userPhone,responseBody.getPassword()));
        return u.equals(null)?null:responseBody;
    }

    @Override
    public User findUserByUserPhone(String userPhone) {
        QueryWrapper<User> wrapper = new QueryWrapper();
        wrapper.eq("phone",userPhone);
        return this.getOne(wrapper);
    }

    @Override
    public boolean isExsit(String userPhone){
        QueryWrapper<User> wrapper = new QueryWrapper();
        wrapper.eq("phone",userPhone);
        return this.getOne(wrapper) != null ? true : false;
    }


    @Override
    public Result userRegist(User user) {
        boolean  exist = isExsit(user.getPhone());
        if (!exist){
            //user.setUid(null);
            //设置注册时间
            user.setCreateTime(String.valueOf(System.currentTimeMillis()));
            return this.save(user)?new Result(Code.Regist_Success):new Result(Code.Regist_Fail);
        }else {
            return new Result(Code.User_Existed);
        }
    }

    @Override
    public boolean deleteUser(String phone) {
        QueryWrapper<User> wrapper = new QueryWrapper();
        wrapper.eq("phone",phone);
        return this.remove(wrapper)?true:false;
    }

    //搜索用户
    @Override
    public User getUser(String nickName) {
        QueryWrapper<User> wrapper = new QueryWrapper();
        wrapper.eq("nick_name",nickName);
        return this.getOne(wrapper);
    }


    @Override
    public boolean logOut(User userId) {
        return false;
    }



    public List<Comment> getComment(String videoId){

        QueryWrapper<Comment> wrappers = new QueryWrapper<>();

        wrappers.eq("video_id",videoId);

        return commentService.list(wrappers);


    }

    /**
     * 用户发布评论,先判断是否存在用户，若存在则将新评论的id添加到用户评论id中
     * @param userId
     * @param videoId
     * @param parentId
     * @param comment
     * @return
     */

    @Override
    public boolean addComment(String userId,String videoId,String parentId,String comment){

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("uid",userId);

        User u = this.getOne(wrapper);

        if (u != null){
            String cId = commentService.addCommentAndgetId(videoId,parentId,comment);
            if (null != u.getCommentId()) {
                StringBuffer stb = new StringBuffer();
                stb.append(u.getCommentId());
                stb.append(",").append(cId);
                u.setCommentId(stb.toString());
            } else {
                u.setCommentId(cId);
            }
            this.saveOrUpdate(u); //去掉一个 wrapper 参数
            return true;
        }
        return false;
    }

    //获得用户评论
    @Override
    public List getUsersComment(String commentIds){
        return commentService.getCmments(commentIds);
    }


    //发送消息
    @Override
    public boolean sendMessage(String sendPhone,String receverPhone, String msg) {

        String msg_id = messageService.sendMessage(sendPhone,receverPhone,msg);
        return addMsg(sendPhone, msg_id) && addMsg(receverPhone, msg_id);
    }

    /**
     * 根据前台传递过来的userID，现在用户表中查询其所有消息id,再从消息表中查询出这些消息详细信息，
     * 再根据消息详情中发送者id查询其昵称，最终将这新数据组合封装在一起返回给前台
     * @param userId
     * @return
     */
    @Override
    public List getUserMessage(String userId) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("uid",userId);
        User u = this.getOne(wrapper);
        List<Message> messageList = messageService.getMessages(u.getReceiveMessage());
        List<UserMessageResponse> responsesList = new ArrayList<>(messageList.size());

        messageList.forEach(t->{
            UserMessageResponse response = new UserMessageResponse();
            BeanUtils.copyProperties(t,response);
            responsesList.add(response);
        });

        List<String>  userIdList = new ArrayList<>(responsesList.size());

        responsesList.forEach(t->{
            userIdList.add(t.getSenderId());
        });

        List<User> userList= (List<User>) this.listByIds(userIdList);
        HashMap<Integer,String> idAndNameMap = new HashMap<>();
        userList.forEach(t->{
            idAndNameMap.put(t.getUid(),t.getNickName());
        });

        responsesList.forEach(t->{
            t.setSenderName(idAndNameMap.get(t.getSenderId()));
        });


        return responsesList;

    }


    @Override
    public boolean changeUserState(Integer uid,Integer state) {
        User u = new User();
        u.setUid(uid);
        u.setState(state);
        return this.saveOrUpdate(u);
    }

    //添加消息
    private boolean addMsg(String sendPhone, String msg_id) {

        QueryWrapper<User>  wrapper = new QueryWrapper<>();

        wrapper.eq("send_phone", sendPhone);

        User u = this.getOne(wrapper);

        StringBuffer strs = new StringBuffer();

        strs.append(u.getSendMessage());
        strs.append(","+ msg_id);
        u.setSendMessage(strs.toString());
        return this.saveOrUpdate(u);
    }






    /**
     * 上传视频
     */
    @Override
    public boolean upload(Video video){
        return this.videoService.upload(video);
    }


    @Override
    public List getVideo(String userPhone) {
        return this.videoService.getUserVideo(userPhone);
    }

    @Override
    public List searchVideo(String videoDesc){
        return this.videoService.searchVideo(videoDesc);
    }

    @Override
    public Boolean changeUserAvatar(Image image){
        return imageService.uploadImage(image);


    }

}

