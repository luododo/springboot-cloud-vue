package com.ithr.eduucenter.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.ithr.commonutils.JwtUtils;
import com.ithr.commonutils.Result;
import com.ithr.eduucenter.entity.UcenterMember;
import com.ithr.eduucenter.entity.vo.RegisterVo;
import com.ithr.eduucenter.service.UcenterMemberService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author 子涵
 * @since 2020-04-23
 */
@RestController
@CrossOrigin
@RequestMapping("/eduucenter/member")
public class UcenterMemberController {
    @Resource
    private UcenterMemberService memberService;
    //登录
    @PostMapping("login")
    public Result login(@RequestBody UcenterMember member){
     String token=memberService.login(member);
        return Result.ok().data("token",token);
    }
    //注册
    @PostMapping("register")
    public Result registerUser(@RequestBody RegisterVo registerVo) {
        memberService.register(registerVo);
        return Result.ok();
    }

    //根据token获取用户信息
    @GetMapping("getMemberInfo")
    public Result getMemberInfo(HttpServletRequest request) {
        //调用jwt工具类的方法。根据request对象获取头信息，返回用户id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        //查询数据库根据用户id获取用户信息
        UcenterMember member = memberService.getById(memberId);

        Result.ok().data("userInfo",member);
        System.out.println(Result.ok().data("userInfo",member));
        return Result.ok().data("userInfo",member);

    }

}

