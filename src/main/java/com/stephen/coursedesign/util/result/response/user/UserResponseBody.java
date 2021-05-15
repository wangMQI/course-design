package com.stephen.coursedesign.util.result.response.user;

import lombok.Data;



/**
 * @program: course-design
 * @author: Stephen·Wang
 * @date: 2021/5/8 20:00
 * @Version:0.1
 * @Description:用户响应工具类
 */
@Data
public class UserResponseBody {



    private static final long serialVersionUID = 1L;

    /**
     * 用户Id
     */
    private Integer uid;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 手机
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 上传视频id
     */
    private String videoId;

    /**
     * 评论id
     */
    private String commentId;

    /**
     * 密码
     */
    private String password;

    /**
     * 消息
     */
    private String sendMessage;

    /**
     * 消息
     */
    private String receiveMessage;

    /**
     * 状态
     */
    private Integer state;

    /**
     * 用户头像地址
     */
    private String userAvatarUrl;

    /**
     * 注册时间
     */
    private String createTime;

    /**
     * token
     */
    private String token;


    public UserResponseBody() {
    }

    public UserResponseBody(Integer uid, String commentId) {
        this.uid = uid;
        this.commentId = commentId;
    }
}

