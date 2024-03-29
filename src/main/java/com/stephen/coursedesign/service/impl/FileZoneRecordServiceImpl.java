package com.stephen.coursedesign.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stephen.coursedesign.entity.FileZoneRecord;
import com.stephen.coursedesign.entity.mapper.FileZoneRecordMapper;
import com.stephen.coursedesign.service.IFileZoneRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: course-design
 * @author: Stephen·Wang
 * @date: 2021/5/5 21:01
 * @Version:
 * @Description:文件分片记录 服务实现类
 */

@Service
public class FileZoneRecordServiceImpl extends ServiceImpl<FileZoneRecordMapper, FileZoneRecord> implements IFileZoneRecordService {

    @Autowired
    private  FileZoneRecordMapper fileZoneRecordMapper;

    @Override
    public FileZoneRecord selByMD5AndZoneTotalMd5(String zoneMd5, String zoneTotalMd5) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("zone_md5",zoneMd5);
        queryWrapper.eq("zone_total_md5",zoneTotalMd5);
        List<FileZoneRecord> list = fileZoneRecordMapper.selectList(queryWrapper);
        if(list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<FileZoneRecord> selByTotalMd5(String zoneTotalMd5) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("zone_total_md5",zoneTotalMd5);
        queryWrapper.orderByAsc("zone_now_index");
        List<FileZoneRecord> list = fileZoneRecordMapper.selectList(queryWrapper);
        return list;
    }
}
