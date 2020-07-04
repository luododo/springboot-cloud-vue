package com.ithr.eduservice.service;

import com.ithr.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ithr.eduservice.entity.vo.CourseInfoVo;
import com.ithr.eduservice.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author 子涵
 * @since 2020-04-12
 */
public interface EduCourseService extends IService<EduCourse> {
       //添加课程基本信息
    String saveCourseInfo(CourseInfoVo courseInfoVo);
      // //根据课程查询查询的课程基本信息
    CourseInfoVo getCourseInfo(String courseId);
     ////修改课程信息
    void updateCourseInfo(CourseInfoVo courseInfoVo);
    ///根据课程id查询课程确认信息
    CoursePublishVo publishCourseInfo(String id);
    //根据课程id courseid删除课程
    void removeCourse(String id);
}
