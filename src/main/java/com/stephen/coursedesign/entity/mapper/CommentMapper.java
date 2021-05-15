package com.stephen.coursedesign.entity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stephen.coursedesign.entity.Comment;
import com.stephen.coursedesign.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @program: course-design
 * @author: Stephen·Wang
 * @date: 2021/4/18 13:23
 * @Version:
 * @Description:
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
}
