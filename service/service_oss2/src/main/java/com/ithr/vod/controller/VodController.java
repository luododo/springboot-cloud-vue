package com.ithr.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.baomidou.mybatisplus.extension.api.R;
import com.ithr.commonutils.Result;
import com.ithr.servicebase.exceptionhandler.IthrException;
import com.ithr.vod.Utils.ConstantVodUtils;
import com.ithr.vod.Utils.InitObject;
import com.ithr.vod.service.VodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.ws.rs.POST;
import java.util.List;

/**
 * @author com.hr.Text
 * @date 2020/4/21 - 0:25
 */
@Api(description = "阿里云视频点播")
@RestController
@RequestMapping ("/eduvod/video")
@CrossOrigin//解决跨域
public class VodController {
    @Resource
    private VodService vodService;
  //上传视频到阿里云
    @PostMapping("uploadAlyVideo")
    public Result uploadAlyVideo(MultipartFile file){
        //返回上传之后的id
       String videoId= vodService.uploadVideoAly(file);
        return Result.ok().data("videoId",videoId);
    }
  //感根据视频id删除阿里云视频
    @DeleteMapping("removeAlyVideo/{id}")
    public Result removeAlyVideo(@PathVariable String id){
       try {
           //初始化对象
         DefaultAcsClient client= InitObject.initVodClient(ConstantVodUtils.ACCESS_KEY_ID,ConstantVodUtils.ACCESS_KEY_SECRET);
           //创建删除视频的request
           DeleteVideoRequest request=new DeleteVideoRequest();
           //向request设置视频视频id
           request.setVideoIds(id);
           //调用初始对象的方法进行删除
           client.getAcsResponse(request);
           System.out.println("+++++");
           return Result.ok();
       }catch (Exception e){
           e.printStackTrace();
           throw new IthrException(20001,"删除阿里云视频失败");
       }
    }
    //删除多个视频
    //删除多个阿里云视频的方法
    //参数多个视频id  List videoIdList
    //表示@RequestParam("videoIdList")设置视频名称
    @DeleteMapping("delete-batch")
    public Result deleteBatch(@RequestParam("videoIdList") List<String> videoIdList) {
        vodService.removeMoreAlyVideo(videoIdList);
        return Result.ok();
    }

}
