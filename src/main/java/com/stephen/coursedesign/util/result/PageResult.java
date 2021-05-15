/*
package com.stephen.coursedesign.util.result;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

*/
/**
 * @program: course-design
 * @author: Stephen·Wang
 * @date: 2021/5/15 17:57
 * @Version:
 * @Description: 分页结果
 *//*

@Data
@NoArgsConstructor
public class PageResult implements Serializable {
    //@ApiModelProperty(value = "操作标识，false失败，true成功")
    private boolean success;//是否成功
    //@ApiModelProperty(value = "数据总条数，状态码")
    private Integer code;// 返回码
    //@ApiModelProperty(value = "响应提示消息")
    private String message;//返回信息
    //@ApiModelProperty(value = "响应数据，可能是对象，也可能是数组对象，具体看业务需求")
    private Object data;// 返回数据


    public PageResult() {
    }

    public PageResult(boolean success, Integer code, String message, Object data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
*/
