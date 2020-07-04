package com.ithr.eduucenter.service;

import com.ithr.eduucenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ithr.eduucenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author 子涵
 * @since 2020-04-23
 */
public interface UcenterMemberService extends IService<UcenterMember> {
    //登录
    String login(UcenterMember member);
    //注册
    void register(RegisterVo registerVo);
}
