package com.ithr.eduservice.controller;


import com.ithr.commonutils.Result;
import com.ithr.eduservice.entity.subject.OneSubject;
import com.ithr.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author 子涵
 * @since 2020-04-11
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
@Api(description = "课程分类")
public class EduSubjectController {
    @Autowired
    private EduSubjectService eduSubjectService;
    //添加课程分类
    //把文件读取
    @PostMapping("insertSubject")
    public Result insertSubject(MultipartFile file){
        eduSubjectService.saveSubject(file,eduSubjectService);
        return Result.ok();
    }
    //课程分类树形树形
    @GetMapping("getAllSubject")
    public  Result getAllSubject(){
        //list是一级分类因为他里面有二级分类
     List<OneSubject> list= eduSubjectService.getAlloneTwoSbject();
        return  Result.ok().data("list",list);
    }
}

