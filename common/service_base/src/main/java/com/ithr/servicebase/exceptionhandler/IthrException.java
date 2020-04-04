package com.ithr.servicebase.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author com.hr.Text
 * @date 2020/4/4 - 13:16
 */
@Data
@AllArgsConstructor//可以生成有参
@NoArgsConstructor//可以生成无参
public class IthrException extends RuntimeException {
    //状态码
    private Integer code;
    //提示信息
    private String msg;
public static IthrException error1(){
    IthrException ie=new IthrException();
    ie.setCode(20001);
    ie.setMsg("自定义异常执行");
    return ie;
}





}
