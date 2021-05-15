package com.stephen.coursedesign.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.stephen.coursedesign.entity.Image;
import com.stephen.coursedesign.entity.User;
import com.stephen.coursedesign.entity.Video;
import com.stephen.coursedesign.util.result.Result;
import com.stephen.coursedesign.util.result.response.user.UserResponseBody;

import java.util.List;

/**
 * @program: course-design
 * @author: Stephen·Wang
 * @date: 2021/4/18 12:04
 * @Version:
 * @Description:
 */
public interface UserService extends IService<User> {


    //验证密码
    String verifyPasswordByUsername(String username);

    //登陆验证
    UserResponseBody loginVerify(String userPhone);


    //注册
    Result userRegist(User user);

    //验证密码
    String verifyPasswordByUserPhone(String userPhone);

    //通过用户名查用户信息
    User findUserByUserPhone(String userPhone);

    //查询用户是否存在
    boolean isExsit(String userPhone);

    //添加评论
    boolean addComment(String userId,String videoId,String parentId,String comment);

    boolean upload(Video video);

    //更改头像
    Boolean changeUserAvatar(Image image);



    //用户查看自己评论
    List getUsersComment(String userId);



    /**
     * Desc:发送消息
     */
    boolean sendMessage(String sendPhone,String receverPhone, String msg);

    /**
     * Desc:查看消息
     */
    List getUserMessage(String userId);


    //返回用户上传的视频
    List getVideo(String videoDesc);

    //搜索视频
    List searchVideo(String userPhone);


    /**
     * 更改用户状态
     * 用户状态码：-1 注销 、0 正常 、1 冻结
     * @param uid
     * @param state
     * @return
     */
    boolean changeUserState(Integer uid,Integer state);



    boolean deleteUser(String userId);

    User getUser(String phone);


    boolean logOut(User userId);
}
