package com.ithr.eduservice.Client.impl;

import com.ithr.commonutils.Result;
import com.ithr.eduservice.Client.VodClient;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author com.hr.Text
 * @date 2020/4/22 - 12:53
 */
@Component
public class VodClientImpl implements VodClient {
    //出错执行
    @Override
    public Result removeAlyVideo(String id) {
        return  Result.no().message("垄断了执行出错误");

    }
    //出错执行
    @Override
    public Result deleteBatch(List<String> videoIdList) {

        return  Result.no().message("垄断了执行出错误");
    }
}
