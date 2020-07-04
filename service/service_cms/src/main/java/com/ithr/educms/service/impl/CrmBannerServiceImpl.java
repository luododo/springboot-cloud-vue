package com.ithr.educms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ithr.educms.entity.CrmBanner;
import com.ithr.educms.mapper.CrmBannerMapper;
import com.ithr.educms.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author 子涵
 * @since 2020-04-22
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {
    //查询所有banner
    //开启缓存
    @Cacheable(value = "banner",key ="'selectIndexList'")//放到缓存
    @Override
    public List<CrmBanner> selectAllBanner() {
       //代码排序查询前2条
        QueryWrapper<CrmBanner> wrapper=new QueryWrapper<>();
        //根据id排序
        wrapper.orderByDesc("id");
        //显示前面二条 拼接SQL语句
//        wrapper.last("limit 2");
        List<CrmBanner> bannerList = baseMapper.selectList(null);
        return bannerList;
    }
}
