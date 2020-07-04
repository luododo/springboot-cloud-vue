package com.ithr.eduservice.service;

import com.ithr.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ithr.eduservice.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author 子涵
 * @since 2020-04-11
 */
public interface EduSubjectService extends IService<EduSubject> {
   //添加课程分类
    void saveSubject(MultipartFile file,EduSubjectService eduSubjectService);
    //课程分类树形
    List<OneSubject> getAlloneTwoSbject();
}
