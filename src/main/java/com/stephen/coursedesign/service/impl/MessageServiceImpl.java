package com.stephen.coursedesign.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stephen.coursedesign.entity.Message;
import com.stephen.coursedesign.entity.mapper.MessageMapper;
import com.stephen.coursedesign.service.MessageService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @program: course-design
 * @author: Stephen·Wang
 * @date: 2021/4/18 13:25
 * @Version:
 * @Description:
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

    @Override
    public boolean sendNotify(String userId){
        return true;
    }

    @Override
    public String sendMessage(String sendPhone, String receverPhone, String msg) {
        Message m = new Message(sendPhone,receverPhone,String.valueOf(System.currentTimeMillis()),msg);
        this.saveOrUpdate(m);
        return String.valueOf(m.getMessageId());
    }

    @Override
    public List getMessages(String messagesIds) {
        String [] strs = messagesIds.split(",");
        return (List) this.listByIds(Arrays.asList(strs));
    }
}
