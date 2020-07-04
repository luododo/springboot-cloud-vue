package com.ithr.eduservice.Client;

import com.ithr.commonutils.Result;
import com.ithr.eduservice.Client.impl.VodClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author com.hr.Text
 * @date 2020/4/22 - 0:05
 */
@FeignClient(value = "service-vod",fallback =VodClientImpl.class)//调用服务名称
@Component//交给spring管理
public interface VodClient {
    //定义调用方法全路径
    ////感根据视频id删除阿里云视频@PathVariable("id")不写就会出现问题
    @DeleteMapping("/eduvod/video/removeAlyVideo/{id}")
    public Result removeAlyVideo(@PathVariable("id") String id);

    //定义删除多个视频的id 也就是删除课程然后删除章节小节还有视频
    @DeleteMapping("/eduvod/video/delete-batch/{id}")
    public Result deleteBatch(@RequestParam("videoIdList") List<String> videoIdList);

}
