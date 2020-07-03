package com.ithr.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ithr.eduservice.entity.EduSubject;
import com.ithr.eduservice.entity.excel.SubjecData;
import com.ithr.eduservice.service.EduSubjectService;
import com.ithr.servicebase.exceptionhandler.IthrException;

import java.util.Map;

/**
 * @author com.hr.Text
 * @date 2020/4/11 - 13:36
 */
public class SubjecExcelListener extends AnalysisEventListener<SubjecData> {


    //因为SubjectExcelListener不能交给spring进行管理，需要自己new，不能注入其他对象
    //不能实现数据库操作
    public EduSubjectService edusubjectService;
    public SubjecExcelListener() {}
    public SubjecExcelListener(EduSubjectService edusubjectService) {
        this.edusubjectService = edusubjectService;
    }


    //监听器
    @Override
    public void invoke(SubjecData subjecData, AnalysisContext analysisContext) {
     if(subjecData==null){
         throw  new IthrException(20001,"文件数据为空");
     }

        //一行一行读取，每次读取有两个值，第一个值一级分类，第二个值二级分类
        //判断一级分类是否重复
        EduSubject existOneSubject = this.existOneSubject(edusubjectService,subjecData.getOneSubjectName());
        if(existOneSubject == null) { //没有相同一级分类，进行添加
            existOneSubject = new EduSubject();
            existOneSubject.setParentId("0");
            existOneSubject.setTitle(subjecData.getOneSubjectName());//一级分类名称
            edusubjectService.save(existOneSubject);
        }

        //获取一级分类id值
        String pid = existOneSubject.getId();

        //添加二级分类
        //判断二级分类是否重复
        EduSubject existTwoSubject = this.existTwoSubject(edusubjectService, subjecData.getTwoSubjectName(), pid);
        if(existTwoSubject == null) {
            existTwoSubject = new EduSubject();
            existTwoSubject.setParentId(pid);
            existTwoSubject.setTitle(subjecData.getTwoSubjectName());//二级分类名称
            edusubjectService.save(existTwoSubject);
        }
    }

    //判断一级分类不能重复添加
    private EduSubject existOneSubject(EduSubjectService edusubjectService, String name) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        EduSubject oneSubject = edusubjectService.getOne(wrapper);
        return oneSubject;
    }

    //判断二级分类不能重复添加
    private EduSubject existTwoSubject(EduSubjectService edusubjectService,String name,String pid) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);

        EduSubject twoSubject = edusubjectService.getOne(wrapper);
        return twoSubject;

    }

    //监听器
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
