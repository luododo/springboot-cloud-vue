package com.ithr.msmservvice.service;

import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author com.hr.Text
 * @date 2020/4/23 - 1:38
 */

public interface MsmServic {
    //发送短信的方法 param随机值 phone手机号
    boolean send(Map<String, Object> param, String phone);
}
