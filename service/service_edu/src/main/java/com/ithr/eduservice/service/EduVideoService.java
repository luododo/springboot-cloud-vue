package com.ithr.eduservice.service;

import com.ithr.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author 子涵
 * @since 2020-04-12
 */
public interface EduVideoService extends IService<EduVideo> {
    //根据课程id删除小节
    void removeVideoByCourseId(String id);
}
