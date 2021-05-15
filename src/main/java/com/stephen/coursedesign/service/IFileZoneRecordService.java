package com.stephen.coursedesign.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.stephen.coursedesign.entity.FileZoneRecord;

import java.util.List;

/**
 * @program: course-design
 * @author: Stephen·Wang
 * @date: 2021/5/5 20:59
 * @Version:
 * @Description:
 */
public interface IFileZoneRecordService extends IService<FileZoneRecord> {

    FileZoneRecord selByMD5AndZoneTotalMd5(String zoneMd5, String zoneTotalMd5);

    List<FileZoneRecord> selByTotalMd5(String totalmd5);
}
