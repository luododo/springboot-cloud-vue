package com.ithr.eduservice.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.ithr.commonutils.Result;
import com.ithr.eduservice.Client.VodClient;
import com.ithr.eduservice.entity.EduVideo;
import com.ithr.eduservice.service.EduVideoService;
import com.ithr.servicebase.exceptionhandler.IthrException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author 子涵
 * @since 2020-04-12
 */
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin//解决跨域
public class EduVideoController {
    @Resource
    private EduVideoService eduVideoService;
    @Resource
    private VodClient vodClient;
    //添加小节
    @PostMapping("addVideo")
    public Result addVideo(@RequestBody EduVideo eduVideo) {
        eduVideoService.save(eduVideo);
        return Result.ok();
    }

    //删除小节 删除对应的阿里云的视频
    //  后面这个方法需要完善：删除小节时候，同时把里面视频删除
     @DeleteMapping("deleteVideo/{id}")
        public Result deleteVideo(@PathVariable String id) {
         //根据小节id得到视频id  调用方法实现删除
        EduVideo byId = eduVideoService.getById(id);
        //在得到视频id
        String videoSourceId = byId.getVideoSourceId();
        //给视频id进行删除
        //判断是否有视频有id删除不为空
        if(!StringUtils.isEmpty(videoSourceId)) {
            //给视频id进行删除  根据视频id 调用实现视频删除
            Result result = vodClient.removeAlyVideo(videoSourceId);
            //判断是否出错 如果出错
            if(result.getCode()==20001){
                throw new IthrException(20001,"删除视频失败 ,熔断器执行");
            }
        }
        eduVideoService.removeById(id);
        return Result.ok();


    }

    //根据id查询小节
    @GetMapping("getIdAddVideo/{id}")
    public Result getIdAddVideo(@PathVariable String id){
        EduVideo byId = eduVideoService.getById(id);
        return Result.ok().data("byId",byId);
    }
    //修改
    @PostMapping("updateVideo")
    public Result updateVideo(@RequestBody EduVideo eduVideo){
        boolean b = eduVideoService.updateById(eduVideo);
        if(b=true){
            return Result.ok();
        }else{
            return  Result.no();
        }

    }
}

