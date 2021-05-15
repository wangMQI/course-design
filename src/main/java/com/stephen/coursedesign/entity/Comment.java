package com.stephen.coursedesign.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: course-design
 * @author: Stephen·Wang
 * @date: 2021/4/18 12:03
 * @Version:
 * @Description:
 */
@TableName("comment")
@Data
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer commentId;

    /**
     * 评论
     */
    private String comments;

    /**
     * 评论时间
     */
    private String createTime;

    /**
     * 回复id
     */
    private String parentId;

    /**
     * 所属视频id
     */
    private String videoId;

    /**
     * 点赞数
     */
    private String likes;

    /**
     * 评论人
     */
    private String cmmentCreater;

    /**
     * 评论人id
     */
    private String uid;


    public Comment() {
    }

    public Comment(String videoId,String parentId,String comments,String createTime) {
        this.parentId = parentId;
        this.comments = comments;
        this.videoId = videoId;
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", comments='" + comments + '\'' +
                ", createTime='" + createTime + '\'' +
                ", parentId='" + parentId + '\'' +
                ", videoId='" + videoId + '\'' +
                ", likes='" + likes + '\'' +
                '}';
    }
}
