package com.ithr.eduservice.mapper;

import com.ithr.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ithr.eduservice.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author 子涵
 * @since 2020-04-12
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
       //四表链查
    public CoursePublishVo getPublishCourseInfo(String courseId);
}
