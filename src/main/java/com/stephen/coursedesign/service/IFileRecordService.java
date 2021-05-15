package com.stephen.coursedesign.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.stephen.coursedesign.entity.FileRecord;
import com.stephen.coursedesign.entity.FileZoneRecord;
import com.stephen.coursedesign.comment.entity.ResultV;

import javax.servlet.http.HttpServletRequest;
/**
 * @program: course-design
 * @author: Stephen·Wang
 * @date: 2021/5/5 20:58
 * @Version:
 * @Description:件上传记录 服务类
 */
public interface IFileRecordService extends IService<FileRecord> {

    ResultV upload(HttpServletRequest request, Integer uploadType, Integer storageYear);

    ResultV zoneUpload(HttpServletRequest request, String contentType, FileZoneRecord fileZoneRecord);

    ResultV md5Check(FileZoneRecord fileZoneRecord, Integer checkType, String contentType, HttpServletRequest request);

    ResultV mergeZoneFile(String totalmd5, HttpServletRequest request);

    ResultV delZoneFile(String totalmd5);

    ResultV delFile(String fileId);

//    void recordDownloadLog(String fileId, FileRecord fileRecord);
}
