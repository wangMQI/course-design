package com.stephen.coursedesign.entity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stephen.coursedesign.entity.User;
import com.stephen.coursedesign.entity.Video;
import org.apache.ibatis.annotations.Mapper;

/**
 * @program: course-design
 * @author: Stephen·Wang
 * @date: 2021/4/18 15:12
 * @Version:
 * @Description:
 */
@Mapper
public interface VideoMapper extends BaseMapper<Video> {
}
