package com.ithr.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ithr.commonutils.Result;

import com.ithr.eduservice.entity.EduCourse;
import com.ithr.eduservice.entity.vo.CourseInfoVo;
import com.ithr.eduservice.entity.vo.CoursePublishVo;
import com.ithr.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author 子涵
 * @since 2020-04-12
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin//解决跨域
@Api(description = "添加课程基本信息")
public class EduCourseController {
    @Autowired
    private EduCourseService courseService;

    //修改
    //根据id查询课程
    @GetMapping("getCourse/{id}")
    public Result getCourse(@PathVariable String id) {
        EduCourse byId = courseService.getById(id);
        return Result.ok().data("byId", byId);
    }

    //根据课程id courseid删除课程
    @DeleteMapping("deleteCourse/{id}")
    public Result deleteCourse(@PathVariable String id) {
        courseService.removeCourse(id);
        return Result.ok();
    }

    //条件查询带分页
//    @PostMapping("pageTeacherCondition/{current}/{limit}")
//    public Result pageTeacherCondition(@RequestBody ){
//
//    }
    @PostMapping("getCourseList//{current}/{limit}")
    public Result getCourseList(@PathVariable long current, @PathVariable long limit, @RequestBody(required = false) EduCourse eduCourse) {
        Page<EduCourse> pages = new Page<>(current, limit);
        //条件实体

        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        //动态sql
        //  teacherQuery这个是用户输入的值

        String title = eduCourse.getTitle();
        String status = eduCourse.getStatus();
        if (!StringUtils.isEmpty(title)) {
            wrapper.like("title", title);
        }
        if (!StringUtils.isEmpty(status)) {
            wrapper.eq("status", status);
        }
        //按时间排序
        // wrapper.orderByDesc("gmt_create");
        courseService.page(pages, wrapper);
        long total = pages.getTotal();
        List<EduCourse> list = pages.getRecords();

        return Result.ok().data("total", total).data("list", list);



    }

    //添加课程基本信息
    @ApiOperation(value = "添加课程基本信息")
    @PostMapping("addCourseInfo")
    public Result addCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        //返回添加之后的课程id为后面大纲使用
        String id = courseService.saveCourseInfo(courseInfoVo);
        return Result.ok().data("courseId", id);
    }

    //根据课程id查询查询的课程基本信息
    @GetMapping("getCourseInfo/{courseId}")
    public Result getCourseInfo(@PathVariable String courseId) {
        CourseInfoVo courseInfo = courseService.getCourseInfo(courseId);
        return Result.ok().data("courseInfo", courseInfo);
    }

    //修改课程信息
    @PostMapping("updateCourseInfo")
    public Result updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        courseService.updateCourseInfo(courseInfoVo);
        return Result.ok();
    }

    ///根据课程id查询课程确认信息
    @GetMapping("getPublishCourseInfo/{id}")
    public Result getPublishCourseInfo(@PathVariable String id) {
        CoursePublishVo coursePublishVo = courseService.publishCourseInfo(id);
        return Result.ok().data("coursePublishVo", coursePublishVo);
    }

    //修改课程的最终发布
    @PostMapping("publishCourse/{id}")
    public Result publishCourse(@PathVariable String id) {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus("Normal");
        courseService.updateById(eduCourse);
        return Result.ok();
    }


}

