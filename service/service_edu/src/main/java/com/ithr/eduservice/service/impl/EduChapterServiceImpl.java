package com.ithr.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ithr.eduservice.entity.EduChapter;
import com.ithr.eduservice.entity.EduVideo;
import com.ithr.eduservice.entity.chapter.OneChapter;
import com.ithr.eduservice.entity.chapter.TowChapter;
import com.ithr.eduservice.mapper.EduChapterMapper;
import com.ithr.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ithr.eduservice.service.EduVideoService;
import com.ithr.servicebase.exceptionhandler.IthrException;
import org.apache.ibatis.annotations.One;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author 子涵
 * @since 2020-04-12
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {
    @Autowired
    private EduVideoService eduVideoService;
    ////大纲列表 根据课程id进行查询
    @Override
    public List<OneChapter>     getChapterVideoByCourseId(String courseId) {
        //1 根据课程id查询课程里面所有的章节
        QueryWrapper<EduChapter> w=new QueryWrapper<>();
        w.eq("course_id",courseId);
        List<EduChapter> eduChapterList = baseMapper.selectList(w);

        //2 根据课程id查询课程里面所有的小节查询
        QueryWrapper<EduVideo> q=new QueryWrapper<>();
        q.eq("course_id",courseId);
        List<EduVideo> list = eduVideoService.list(q);

        //3.创建list集合，用于最终章节封装数据
        List<OneChapter> finaList=new ArrayList<>();
        //4.创建list集合，用于最终小节封装数据
        List<TowChapter>  finaList2=new ArrayList<>();

        //3 遍历查询章节list集合进行封装
        for (int i = 0; i < eduChapterList.size(); i++) {
            //每一个章节
            EduChapter eduChapter=eduChapterList.get(i);
            //把eduChapter对象值复制一份给OneChapter；
            OneChapter chapter=new OneChapter();
            BeanUtils.copyProperties(eduChapter,chapter);
            //把chapter放到集合】
            finaList.add(chapter);
            //遍历查询小节list集合进行封装
            for (int m = 0; m < list.size(); m++) {
                EduVideo eduVideo = list.get(m);
                //判断小节里面chanterid和章节里面id是否一样
                if(eduVideo.getChapterId().equals(chapter.getId())){
                    //进行封装
                    TowChapter towChapter1=new TowChapter();
                    BeanUtils.copyProperties(eduVideo,towChapter1);
                    //放到小节封装集合
                    finaList2.add(towChapter1);

                }
            }
            //把封装的之后的小节list集合放到章节对象里面
            chapter.setTowChapter(finaList2);

        }
              return finaList;

    }

    //删除章节方法
    @Override
    public boolean deleteChapter(String id) {
        QueryWrapper<EduVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("chapter_id", id);
        //eduVideoService.count意思是有没有值
        int count = eduVideoService.count(queryWrapper);
        if (count > 0) {//查询出小节就不进行删除
                throw new IthrException(20001, "不能进行删除");
        } else {//没有查询到数据 进行删除
            int i = baseMapper.deleteById(id);
            //如果删除成功return i>0;如果大于就是ture小于就是false
            return i>0;
        }

}
    // 根据课程id删除章节
    @Override
    public void removeChapterByCourseId(String id) {
        QueryWrapper<EduChapter> wrapper=new QueryWrapper<>();
        wrapper.eq("course_id",id);
        baseMapper.delete(wrapper);
    }

}
