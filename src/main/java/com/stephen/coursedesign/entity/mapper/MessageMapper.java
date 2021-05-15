package com.stephen.coursedesign.entity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stephen.coursedesign.entity.Message;
import org.apache.ibatis.annotations.Mapper;

/**
 * @program: course-design
 * @author: Stephen·Wang
 * @date: 2021/4/18 13:25
 * @Version:
 * @Description:
 */
@Mapper
public interface MessageMapper extends BaseMapper<Message> {
}
