package com.stephen.coursedesign.comment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @program: course-design
 * @author: Stephen·Wang
 * @date: 2021/5/15 21:50
 * @Version:
 * @Description:动态参数查询
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DynamicCondition implements Serializable {
    /**
     * 属性名
     */
    private String conditionFieldVal;
    /***
     * 属性操作
     * 	equal:等于
     * 	like:包含
     * 	between:范围
     * 	start:开头字符
     * 	end:结尾字符
     * 	unequal:不等于
     * 	empty:为空
     */
    private String conditionOptionVal;
    /**
     * 查询值对象
     * {
     *     value:"",
     *     text:""
     * }
     */
    private String conditionValueVal;
    /**
     * 上限
     */
    private String conditionValueLeftVal;
    /***
     * 下限
     */
    private String conditionValueRightVal;

}
