package com.stephen.coursedesign.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.stephen.coursedesign.entity.Message;

import java.util.List;

/**
 * @program: course-design
 * @author: Stephen·Wang
 * @date: 2021/4/18 13:17
 * @Version:
 * @Description:
 */
public interface MessageService extends IService<Message> {

    boolean sendNotify(String userId);

    /**
     * 查询消息
     * @param messagesIds
     * @return
     */
    List getMessages(String messagesIds);

    String sendMessage(String sendPhone,String receverPhone, String msg);
}
