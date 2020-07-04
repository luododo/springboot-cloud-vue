package com.ithr.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ithr.commonutils.Result;
import com.ithr.eduservice.entity.EduCourse;
import com.ithr.eduservice.entity.EduTeacher;
import com.ithr.eduservice.service.EduCourseService;
import com.ithr.eduservice.service.EduTeacherService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author com.hr.Text
 * @date 2020/4/22 - 16:06
 */
@RestController
@RequestMapping("/eduservice/indexfrontController")
@CrossOrigin
public class indexfrontController {
    @Resource
    private EduCourseService eduCourse;
    @Resource
    private EduTeacherService eduTeacher;
    //查询热门前8条课程
    @GetMapping("index")
    public Result index(){
        //查询热门前8条课程
        QueryWrapper<EduCourse> wrapper=new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 8");
        List<EduCourse> list = eduCourse.list(wrapper);
        //查询前4名老师
        QueryWrapper<EduTeacher> wrapper1=new QueryWrapper<>();
        wrapper1.orderByDesc("id");
        wrapper1.last("limit 4");
        List<EduTeacher> list1 = eduTeacher.list(wrapper1);
        return Result.ok().data("list",list).data("list1",list1);
    }
    }

