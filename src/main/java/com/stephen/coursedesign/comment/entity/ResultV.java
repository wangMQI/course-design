package com.stephen.coursedesign.comment.entity;

import com.stephen.coursedesign.util.result.Result;

/**
 * @program: course-design
 * @author: Stephen·Wang
 * @date: 2021/5/15 21:02
 * @Version:
 * @Description:
 */
public class ResultV {

    //@ApiModelProperty(value = "操作标识，false失败，true成功")
    private boolean success;//是否成功
    //@ApiModelProperty(value = "数据总条数，状态码")
    private Integer code;// 返回码
    //@ApiModelProperty(value = "响应提示消息")
    private String message;//返回信息
    //@ApiModelProperty(value = "响应数据，可能是对象，也可能是数组对象，具体看业务需求")
    private Object data;// 返回数据

    public ResultV(ResultCode code) {
        this.success = code.success;
        this.code = code.code;
        this.message = code.message;
    }

    public ResultV(ResultCode code,String message) {
        this.success = code.success;
        this.code = code.code;
        this.message = message;
    }

    public ResultV(ResultCode code,Object data) {
        this.success = code.success;
        this.code = code.code;
        this.message = code.message;
        this.data = data;
    }

    public ResultV(Integer code,String message,boolean success) {
        this.code = code;
        this.message = message;
        this.success = success;
    }

    public ResultV(ResultCode success, Object data, String msg) {
        this.data=data;
        this.code = success.code;
        this.message = msg;
        this.success = success.success;
    }


    public static Result SUCCESS(){
        return new Result(ResultCode.SUCCESS);
    }

    public static Result ERROR(){
        return new Result(ResultCode.SERVER_ERROR);
    }

    public static Result FAIL(){
        return new Result(ResultCode.FAIL);
    }


}
