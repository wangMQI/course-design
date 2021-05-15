package com.stephen.coursedesign.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stephen.coursedesign.entity.Comment;
import com.stephen.coursedesign.entity.mapper.CommentMapper;
import com.stephen.coursedesign.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @program: course-design
 * @author: Stephen·Wang
 * @date: 2021/4/18 19:48
 * @Version:
 * @Description:
 */
@Service
@Slf4j
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Override
    public String addCommentAndgetId(String videoId,String parentId, String comment) {

        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");

        Comment c = new Comment(videoId,parentId,comment, String.valueOf(System.currentTimeMillis()));

        this.baseMapper.insert(c);

        log.info(c.toString());

        return String.valueOf(c.getCommentId());

    }


    @Override
    public List getCmments(String commentIds){
        String[] strs = commentIds.split(",");
        List<String> list = new ArrayList<>(strs.length);
        for (String s:strs) {
            list.add(s);
        }

        return (List) this.listByIds(list);
    }


    @Override
    public Boolean deleteComment(String commentId) {
        return this.removeById(commentId);
    }
}
