package com.stephen.coursedesign.util.result;

/**
 * @program: course-design
 * @author: Stephen·Wang
 * @date: 2021/4/18 13:08
 * @Version:
 * @Description:
 */
public enum Code {

    Login_Success("100000", "登陆成功"),
    No_Such_User("100001", "该用户未注册"),
    Password_Wrong("100010", "密码错误"),
    Service_Connect_Exception("100011", "服务器连接异常"),
    Clint_Connect_Exception("100100", "客户端连接异常"),
    Delete_Success("100101", "注销成功"),
    Delete_Fail("100110", "注销失败"),
    Logout_Success("100111", "成功退出"),
    Logout_Fail("101000", "退出失败"),
    Upload_Success("101001", "上传成功"),
    Upload_Fail("101010", "上传失败"),
    Success("101011", "成功"),
    Fail("101100","失败"),
    User_Existed("101101","用户已存在"),
    Regist_Success("101110","注册成功"),
    Regist_Fail("101111","注册失败");

    private String num;

    private String msg;

    public String getNum() {
        return num;
    }

    public String getMsg() {
        return msg;
    }

    Code(String num, String msg) {
        this.num = num;
        this.msg = msg;
    }
    }
