package com.stephen.coursedesign.exception;

import com.stephen.coursedesign.util.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: course-design
 * @author: Stephen·Wang
 * @date: 2021/4/22 15:10
 * @Version:
 * @Description:
 */
@ControllerAdvice
public class GloabllExceptionHandler {
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e) {
        e.printStackTrace();
        return new Result("30001",e.getMessage() != null?e.getMessage():"服务器发生错误！");
    }
}
