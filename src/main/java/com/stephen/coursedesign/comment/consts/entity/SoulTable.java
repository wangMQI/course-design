package com.stephen.coursedesign.comment.consts.entity;


import lombok.Data;

import java.util.List;

/**
 * @program: course-design
 * @author: Stephen·Wang
 * @date: 2021/5/15 17:48
 * @Version:
 * @Description:
 */
@Data
public class SoulTable {
    private boolean head;
    private String prefix;
    private String mode;
    private String field;
    private String value;
    private String type;
    private String groupId;

    private List<SoulTable> children;//子集合
}
