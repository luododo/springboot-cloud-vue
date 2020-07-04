package com.ithr.oss.service;

/**
 * @author com.hr.Text
 * @date 2020/4/9 - 20:08
 */


import org.springframework.web.multipart.MultipartFile;


public interface OssService {
     //上传头像
    String uploadFileAvatar(MultipartFile file);
}
