package com.ithr.msmservvice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.ithr.msmservvice.service.MsmServic;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author com.hr.Text
 * @date 2020/4/23 - 2:15
 */
@Service
public class MsmServiceimpl implements MsmServic {
    //发送短信的方法
    @Override
    public boolean send(Map<String, Object> param, String phone) {
        if(StringUtils.isEmpty(phone)) return false;

        DefaultProfile profile =
                DefaultProfile.getProfile("default", "LTAI4FskHAVMRpou48tzgzyV", "EWTiEYRpvdVNRbnHCgZt1RbFC7MyF6");//id名药
        IAcsClient client = new DefaultAcsClient(profile);

        //设置相关固定的参数
        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);//默认
        request.setDomain("dysmsapi.aliyuncs.com");//固定
        request.setVersion("2017-05-25");//固定
        request.setAction("SendSms");//固定

        //设置发送相关的参数
        request.putQueryParameter("PhoneNumbers",phone); //手机号
        request.putQueryParameter("SignName","我的学习项目网站"); //申请阿里云 签名名称
        request.putQueryParameter("TemplateCode","SMS_190267849"); //申请阿里云 模板code
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param)); //验证码数据，转换json数据传递

        try {
            //最终发送
            CommonResponse response = client.getCommonResponse(request);
            boolean success = response.getHttpResponse().isSuccess();//成功还是失败
            return success;
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }

    }
    }

