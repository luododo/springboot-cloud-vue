package com.ithr.msmservvice.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.ithr.commonutils.Result;
import com.ithr.msmservvice.service.MsmServic;
import com.ithr.msmservvice.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author com.hr.Text
 * @date 2020/4/23 - 1:36
 */
@RestController
@RequestMapping("MsmController/msm")
@CrossOrigin
public class MsmController {
    @Resource
    private MsmServic msmServic;
    @Autowired
    private RedisTemplate<String,String> redisTemplate; //redis的查操作

    //发送短信的方法
    @PostMapping("send/{phone}")
    public Result senMsm(@PathVariable String phone){
        //1 从redis获取验证码，如果获取到直接返回
        String code = redisTemplate.opsForValue().get(phone);
        if(!StringUtils.isEmpty(code)) {
            return Result.ok();
        }
        //2 如果redis获取 不到，进行阿里云发送
        //生成随机值，传递阿里云进行发送
        //生成4位
        code = RandomUtil.getSixBitRandom();
        Map<String,Object> param = new HashMap<>();
        //把随机数放到里面
        param.put("code",code);

        //调用service发送短信的方法param随机的 phone手机号

        boolean isSend = msmServic.send(param,phone);
        if(isSend) {
            //发送成功，把发送成功验证码放到redis里面
            //key phone values code
            //设置5分钟有效时间
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            return Result.ok().message("短信发送成功");
        } else {
            return Result.no().message("短信发送失败");
        }
    }


}
