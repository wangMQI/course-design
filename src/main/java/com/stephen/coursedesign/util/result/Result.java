package com.stephen.coursedesign.util.result;

import com.stephen.coursedesign.util.result.Code;
import lombok.Data;

/**
 * @program: course-design
 * @author: Stephen·Wang
 * @date: 2021/4/18 13:08
 * @Version:
 * @Description:
 */
@Data
public class Result <T>{

    /**
     * @Fields code : 返回状态码
     */
    private String status;

    /**
     * @Fields msg : 返回消息描述
     */
    private String msg;


    /**
     * @Fields data : 返回实体数据
     */
    private T data;

    /**
     * <p>Title: </p>
     * <p>Description: 异常返回时的返回构造描述</p>
     *
     * @param code
     * @param msg
     */
    public Result(String code, String msg) {
        this.status = code;
        this.msg = msg;
    }

    public Result(Code code, T data) {
        this.data = data;
        this.status = code.getNum();
        this.msg = code.getMsg();
    }

    public Result(Code code){
        this.status = code.getNum();
        this.msg = code.getMsg();
    }

    public Result(T t){
        this.data = t;
    }


    public Result() {
    }
}
