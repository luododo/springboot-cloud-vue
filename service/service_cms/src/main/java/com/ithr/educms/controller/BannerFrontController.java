package com.ithr.educms.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.ithr.commonutils.Result;
import com.ithr.educms.entity.CrmBanner;
import com.ithr.educms.service.CrmBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author 子涵
 * @since 2020-04-22
 */
@RestController
@RequestMapping("/educms/bannerfront")
@CrossOrigin  //前台
public class BannerFrontController {
    @Autowired
    private CrmBannerService bannerService;

    //查询所有banner

    @GetMapping("getAllBanner")
    public Result getAllBanner() {
        List<CrmBanner> list = bannerService.selectAllBanner();
        return Result.ok().data("list",list);
    }

}

