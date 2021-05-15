package com.stephen.coursedesign.comment.consts.entity;
import lombok.Data;

/**
 * @program: course-design
 * @author: Stephen·Wang
 * @date: 2021/5/15 17:42
 * @Version:
 * @Description:分页
 */

@Data
public class SoulTableParam {
    /**
     * 条件集合
     **/
//    private List<SoulTable> filterSos;
    private String filterSos;

    /**
     * 属性名
     */
    private String field;
    /***
     * 排序方式
     * asc 或者 desc
     */
    private String order;

    /**
     * 页码
     */
    private Long page;
    /***
     * 分页大小
     */
    private Long size;
}
