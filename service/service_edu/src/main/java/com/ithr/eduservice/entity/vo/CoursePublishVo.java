package com.ithr.eduservice.entity.vo;

import lombok.Data;

/**
 * @author com.hr.Text
 * @date 2020/4/19 - 13:04
 */
@Data
public class CoursePublishVo {
    private String id;//课程id
    private String title;//课程名称
    private String cover;//封面
    private Integer lessonNum;//课时数
    private String subjectLevelOne;//一级分类
    private String subjectLevelTwo;//二级分类
    private String teacherName;//讲师名称
    private String price;//只用于显示价格
}
