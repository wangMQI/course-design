package com.stephen.coursedesign.util.result.response.admin;

import lombok.Data;

/**
 * @program: course-design
 * @author: Stephen·Wang
 * @date: 2021/5/13 1:44
 * @Version:0.1
 * @Description:
 */
@Data
public class AdminResponseBody {

    private static final long serialVersionUID = 1L;

    /**
     * 管理员id
     */
    private Integer adminId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 操作权限
     */
    private Integer operate;

    /**
     * 管理员头像
     */
    private String adminAvatarUrl;

    /**
     * 管理员身份标识
     */
    private String adminSignal;

    /**
     * 登录token
     */
    private String token;



}
