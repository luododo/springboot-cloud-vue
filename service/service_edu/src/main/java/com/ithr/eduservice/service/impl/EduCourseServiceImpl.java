package com.ithr.eduservice.service.impl;

import com.ithr.eduservice.entity.EduChapter;
import com.ithr.eduservice.entity.EduCourse;
import com.ithr.eduservice.entity.EduCourseDescription;
import com.ithr.eduservice.entity.vo.CourseInfoVo;
import com.ithr.eduservice.entity.vo.CoursePublishVo;
import com.ithr.eduservice.mapper.EduCourseMapper;
import com.ithr.eduservice.service.EduChapterService;
import com.ithr.eduservice.service.EduCourseDescriptionService;
import com.ithr.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ithr.eduservice.service.EduVideoService;
import com.ithr.servicebase.exceptionhandler.IthrException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.provider.certpath.CertId;

import javax.annotation.Resource;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author 子涵
 * @since 2020-04-12
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {
    //课程描述的注入
    @Resource
    private EduCourseDescriptionService courseDescriptionService;
    //注入章节做删除
    @Resource
    private EduChapterService eduChapterService;
    //注入小节做删除
    @Resource
    private EduVideoService eduVideoService;
    //添加课程基本信息
    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {

         //1 向课程表添加课程基本信息
        // CourseInfoVo对象转换eduCourse对象
        //此方法可以解决我们封装的实体类封装到对应的实体中
        //实体类特性 有对应的属性才做封装吗就不封装
        EduCourse eduCourse=new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
       //eduCourse.setSubjectParentId("");
        int insert = baseMapper.insert(eduCourse);
        if(insert==0){
            throw new IthrException(20001,"添加课程失败");
        }
        //目前描述是一对一添加之后id是一样的获取到成功之后的id
        String id = eduCourse.getId();

        //2 向课程简介表添加课程简介
        EduCourseDescription  eduCourseDescription=new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        //设置描述id对应我们课程id完成一对一关系
        eduCourseDescription.setId(id);
        courseDescriptionService.save(eduCourseDescription);
            return id;
    }
    //根据课程查询查询的课程基本信息
    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        //1.查询课程表
        EduCourse eduCourse = baseMapper.selectById(courseId);
        CourseInfoVo courseInfoVo=new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse,courseInfoVo);
        //查询描述表
        EduCourseDescription byId = courseDescriptionService.getById(courseId);
        //方式一courseInfoVo.setDescription(byId.getDescription());
        BeanUtils.copyProperties(byId,courseInfoVo);
        return courseInfoVo;
    }

    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        //修改课程表
        EduCourse eduCourse=new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int i = baseMapper.updateById(eduCourse);
        if(i==0){
            throw new IthrException(200001,"修改课程失败");
        }

       //修改描述信息
        EduCourseDescription eduCourseDescription=new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoVo,eduCourseDescription);
        courseDescriptionService.updateById(eduCourseDescription);
    }
    ///根据课程id查询课程确认信息
    @Override
    public CoursePublishVo publishCourseInfo(String id) {
        CoursePublishVo info = baseMapper.getPublishCourseInfo(id);
        return info;
    }
    //根据课程id courseid删除课程
    @Override
    public void removeCourse(String id) {
         //根据课程id删除小节
        eduVideoService.removeVideoByCourseId(id);
        // 根据课程id删除章节
        eduChapterService.removeChapterByCourseId(id);
        //根据课程id删除描述此方法是一对一 描述id就是课程id可以直接删
        courseDescriptionService.removeById(id);
        //根据课程id删除本身
        int i = baseMapper.deleteById(id);

        if(i==0){
            throw new IthrException(20001,"删除失败检查数据");
        }
    }
}
