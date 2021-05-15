package com.stephen.coursedesign.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.stephen.coursedesign.entity.Comment;

import java.util.List;

/**
 * @program: course-design
 * @author: Stephen·Wang
 * @date: 2021/4/18 13:21
 * @Version:
 * @Description:
 */
public interface CommentService extends IService<Comment> {

    String addCommentAndgetId(String videoId,String parentId,String comment);

    List getCmments(String commentIds);

    Boolean deleteComment(String commentId);
}

