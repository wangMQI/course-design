package com.stephen.coursedesign.util.result.response;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: course-design
 * @author: Stephen·Wang
 * @date: 2021/5/11 15:48
 * @Version:.01
 * @Description:查询消息响应体
 */
@Data
public class UserMessageResponse implements Serializable {

    private static final long serialVersionUID = 1L;


    private Integer messageId;

    /**
     * 消息发送者
     */
    private String senderName;

    /**
     * 消息发送者id
     */
    private String senderId;

    /**
     * 消息
     */
    private String messageDetail;

    /**
     * 消息接收者
     */
    private String receiverId;

    /**
     * 创建时间
     */
    private String createTime;


}
