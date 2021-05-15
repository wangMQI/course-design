package com.stephen.coursedesign.comment.base;


import com.stephen.coursedesign.comment.entity.PageResult;
import com.stephen.coursedesign.comment.entity.ResultCode;
import com.stephen.coursedesign.comment.entity.ResultV;


import java.util.List;

/**
 * @program: course-design
 * @author: Stephen·Wang
 * @date: 2021/5/15 17:51
 * @Version:
 * @Description:
 */




public class BaseController {
    /**
     * redirect跳转
     * @param url 目标url
     */
    protected String redirect(String url) {
        return new StringBuilder("redirect:").append(url).toString();
    }
    /**
     * @Description //TODO 返回成功并且有提示信息,不带返回值
     * @Computer Administrator
     * @Date  17:34  2018/9/29/029
     * @Param [msg] 提示消息
     * @return java.lang.Object
     **/
    public ResultV renderSuccess(String msg){
        return new ResultV(ResultCode.SUCCESS,msg);
    }

    public ResultV renderSuccess(){
        return new ResultV(ResultCode.SUCCESS);
    }

    /**
     * @Description //TODO 返回成功并且有提示信息,不带返回值
     **/
    public ResultV renderError(){
        return new ResultV(ResultCode.FAIL);
    }

    public ResultV renderError(String msg){
        return new ResultV(ResultCode.FAIL,msg);
    }
    public ResultV renderError(ResultCode resultCode){
        return new ResultV(resultCode);
    }

    /**
     * 普通数据返回
     * @author haijun
     * @Date 13:31 2019/12/20
     * @Param [data]
     * @return com.carpark.common.entity.Result
     **/
    public ResultV renderDataSuccess(Object data){
        return new ResultV(ResultCode.SUCCESS,data);
    }
    public ResultV renderDataSuccess(Object data,String msg){
        return new ResultV(ResultCode.SUCCESS,data,msg);
    }
    /***
     * 带分页查询返回
     * @param total
     * @param records
     * @return
     */
    public ResultV renderDataPageSuccess(long total, List records){
        return new ResultV(ResultCode.SUCCESS,new PageResult(total,records));
    }


}

