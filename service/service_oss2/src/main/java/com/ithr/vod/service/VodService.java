package com.ithr.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author com.hr.Text
 * @date 2020/4/21 - 0:27
 */
public interface VodService {
    //上传视频到阿里云
    String uploadVideoAly(MultipartFile file);
    //删除多个视频
    //删除多个阿里云视频的方法
    void removeMoreAlyVideo(List<String> videoIdList);
}
