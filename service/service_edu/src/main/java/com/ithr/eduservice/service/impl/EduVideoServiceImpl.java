package com.ithr.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ithr.eduservice.Client.VodClient;
import com.ithr.eduservice.entity.EduVideo;
import com.ithr.eduservice.mapper.EduVideoMapper;
import com.ithr.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author 子涵
 * @since 2020-04-12
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {
  @Resource
  private VodClient vodClient;
    @Override
    public void removeVideoByCourseId(String id) {
        //根据课程id
         QueryWrapper<EduVideo> queryWrapper=new QueryWrapper<>();
         queryWrapper.eq("course_id",id);
         //查询指定的类
         queryWrapper.select("video_source_id");
        List<EduVideo> videos = baseMapper.selectList(queryWrapper);
        //List<Eduvideo>变成List<string>
        List<String> text=new ArrayList<>();
        for (int i = 0; i < videos.size(); i++) {
            EduVideo video = videos.get(i);
            String videoSourceId = video.getVideoSourceId();
            //如果视频id不等于空就放
            if(!StringUtils.isEmpty(videoSourceId)){
            //放到videoList中
            text.add(videoSourceId);
            }
        }
        //根据你的多个视频id删除
        if(videos.size()>0) {
            vodClient.deleteBatch(text);
        }
        //根据课程id删除小节
        QueryWrapper<EduVideo> wrapper=new QueryWrapper<>();
        wrapper.eq("course_id",id);
        baseMapper.delete(wrapper);
    }
}
