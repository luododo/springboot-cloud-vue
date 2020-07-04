package com.ithr.eduucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ithr.commonutils.JwtUtils;
import com.ithr.commonutils.MD5;
import com.ithr.eduucenter.entity.UcenterMember;
import com.ithr.eduucenter.entity.vo.RegisterVo;
import com.ithr.eduucenter.mapper.UcenterMemberMapper;
import com.ithr.eduucenter.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ithr.servicebase.exceptionhandler.IthrException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author 子涵
 * @since 2020-04-23
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {
//    @Autowired
//    private RedisTemplate<String,String> redisTemplate;

    //登录
    @Override

    public String login(UcenterMember member) {
        //获取手机号和密码
        String password = member.getPassword();
        String mobile = member.getMobile();

        //手机号和密码非空判断
        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
            throw new IthrException(20001,"登录失败");
        }

        //判断手机号是否正确
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        UcenterMember mobileMember = baseMapper.selectOne(wrapper);
        //判断查询对象是否为空
        if(mobileMember == null) {//没有这个手机号
            throw new IthrException(20001,"登录失败");
        }

        //判断密码
        //因为存储到数据库密码肯定加密的
        //把输入的密码进行加密，再和数据库密码进行比较
        //加密方式 MD5
        if(!MD5.encrypt(password).equals(mobileMember.getPassword())) {
             throw new IthrException(20001,"登录失败");

        }

        //判断用户是否禁用
        if(mobileMember.getIsDisabled()) {
            throw new IthrException(20001,"登录失败");
        }

        //登录成功
        //生成token字符串，使用jwt工具类
        //member只有手机号和密码没有名称 所以我们拿到查询出来的值就可以了
        String jwtToken = JwtUtils.getJwtToken(mobileMember.getId(), mobileMember.getNickname());
        return jwtToken;


    }
    //注册
    @Override
    public void register(RegisterVo registerVo) {
        //获取注册的数据
        String code = registerVo.getCode(); //验证码
        String mobile = registerVo.getMobile(); //手机号
        String nickname = registerVo.getNickname(); //昵称
        String password = registerVo.getPassword(); //密码

        //非空判断
        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)
                || StringUtils.isEmpty(code) || StringUtils.isEmpty(nickname)) {
            throw new IthrException(20001,"注册失败");
        }
        //判断验证码
        //获取redis验证码
//        String redisCode = redisTemplate.opsForValue().get(mobile);
//        if(!code.equals(redisCode)) {
//            throw new IthrException(20001,"注册失败");
//        }

        //判断手机号是否重复，表里面存在相同手机号不进行添加
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        //判断有没有值
        Integer count = baseMapper.selectCount(wrapper);
        if(count > 0) {
            throw new IthrException(20001,"注册失败");
        }

        //数据添加数据库中
        UcenterMember member = new UcenterMember();
        member.setMobile(mobile);
        member.setNickname(nickname);
        member.setPassword(MD5.encrypt(password));//密码需要加密的
        member.setIsDisabled(false);//用户不禁用
        member.setAvatar("https://online-teach-file.oss-cn-beijing.aliyuncs.com/teacher/2019/10/30/65423f14-49a9-4092-baf5-6d0ef9686a85.png");
        baseMapper.insert(member);
    }


}
