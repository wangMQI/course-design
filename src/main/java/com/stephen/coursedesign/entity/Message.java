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
@TableName("message")
@Data
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "message_id", type = IdType.AUTO)
    private Integer messageId;

    /**
     * 消息接收人
     */
    private String senderId;

    private String messageDetail;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 消息发送者
     */
    private String receiverId;


    public Message(String senderId, String receverId, String createTime, String messageDetail) {
        this.senderId = senderId;
        this.messageDetail = messageDetail;
        this.createTime = createTime;
        this.receiverId = receverId;
    }
}
