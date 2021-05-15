package com.stephen.coursedesign.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @program: course-design
 * @author: Stephen·Wang
 * @date: 2021/5/5 21:08
 * @Version:
 * @Description:文件上传配置
 */

@Data
@Configuration
@ConfigurationProperties("fileupload.config")
public class FileUploadConfig {
    private String uploadFolder;
    private String staticAccessPath;
    private String localPath;
    private String userHeaderPicPath;
    private String archivesFilePath;
    private String domain;


}
