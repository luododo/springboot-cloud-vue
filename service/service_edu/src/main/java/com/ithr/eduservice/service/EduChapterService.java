package com.ithr.eduservice.service;

import com.ithr.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ithr.eduservice.entity.chapter.OneChapter;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author 子涵
 * @since 2020-04-12
 */
public interface EduChapterService extends IService<EduChapter> {
    ////大纲列表 根据课程id进行查询
    List<OneChapter> getChapterVideoByCourseId(String courseId);
    //删除章节方法
    boolean deleteChapter(String id);
    // 根据课程id删除章节
    void removeChapterByCourseId(String id);
}
