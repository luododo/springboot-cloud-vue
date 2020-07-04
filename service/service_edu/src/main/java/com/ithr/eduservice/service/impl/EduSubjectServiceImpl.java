package com.ithr.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ithr.eduservice.entity.EduSubject;
import com.ithr.eduservice.entity.excel.SubjecData;
import com.ithr.eduservice.entity.subject.OneSubject;
import com.ithr.eduservice.entity.subject.TowSubject;
import com.ithr.eduservice.listener.SubjecExcelListener;
import com.ithr.eduservice.mapper.EduSubjectMapper;
import com.ithr.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.velocity.runtime.directive.Foreach;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author 子涵
 * @since 2020-04-11
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {
    //添加课程分类
    @Override
    public void saveSubject(MultipartFile file,EduSubjectService edusubjectService) {
      try {
          //文件输入流
          InputStream in=file.getInputStream();

          EasyExcel.read(in, SubjecData.class,new SubjecExcelListener(edusubjectService)).sheet().doRead();
      }catch (Exception e){

      }

    }

    //课程分类树形
    @Override
    public List<OneSubject> getAlloneTwoSbject() {
        //查询所有一级分类
        QueryWrapper<EduSubject> qwone=new QueryWrapper();
        qwone.eq("parent_id","0");
        List<EduSubject> oneSubjects = baseMapper.selectList(qwone);

        //查询所有二级分类
        QueryWrapper<EduSubject> qwtow=new QueryWrapper();
        //不等于
        qwtow.ne("parent_id","0");
        List<EduSubject> towSubjects = baseMapper.selectList(qwtow);

        //创建list集合 用于存最终一级数据封装
        ArrayList<OneSubject> oneSubjectList = new ArrayList<>();
        //分装所有一级
        //查询出来所有的一级分类list集合遍历得到每一个一级分类对象获取吗每一个一级分类对象值
        for(int i=0;i<oneSubjects.size();i++){
            //得到oneSubjects每一个EduSubject的值
            EduSubject oneeduSubject=oneSubjects.get(i);
            //把eduSubject的值取出去放到oneSubjects里面
            OneSubject one=new OneSubject();
           // one.setId(eduSubject.getId());
            //one.setTitle(eduSubject.getTitle());
            //把eduSubject数据复制给我oneoneSubjects
            //找到对应的封装没有就不封装
            BeanUtils.copyProperties(oneeduSubject,one);
            oneSubjectList.add(one);
            //在一级分类循环遍历所有二级分类
            //创建一个list集合封装每一个一级分类中的二级分类
            //封装所有二级
            ArrayList<TowSubject> towSubjectList = new ArrayList<>();
            for(int m=0;m<towSubjects.size();m++){
                //获取每一个二级分类
                EduSubject toweduSubject = towSubjects.get(m);
                //判断二级分类parentid和一级分类id是否一样
                if(toweduSubject.getParentId().equals(oneeduSubject.getId())){
                    //一样就把toweduSubject里面的二级复制一份给tow
                    TowSubject tow=new TowSubject();
                    BeanUtils.copyProperties(toweduSubject,tow);
                    towSubjectList.add(tow);
                }
            }
            one.setChildren(towSubjectList);
        }
        //把一级下面的所有二级分类放到一级集合里面去

        return oneSubjectList;
    }
}
