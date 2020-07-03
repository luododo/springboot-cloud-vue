package com.ithr.servicebase.exceptionhandler;

import com.ithr.commonutils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author com.hr.Text
 * @date 2020/4/4 - 1:55
 */
@ControllerAdvice//增强型控制器，对于控制器的全局配置放在同一个位置
@Slf4j//在日志输出异常信息
public class excption {
    //制定什么异常会出现这个方法 这里是全部异常
    @ExceptionHandler(Exception.class)
    //返回数据
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return Result.no().message("全局异常系统出错了请检查数据在试");
    }
    //可以写特定异常复制上面一份


    //自定义异常
    @ExceptionHandler(IthrException.class)
    @ResponseBody
    public Result error(IthrException e){
        log.error(e.getMessage());//写到日志文件夹去

        e.printStackTrace();
     return Result.no().code(e.getCode()).message(e.getMessage());
        //方式二
      //  return Result.no().message(e.error1().toString());
        
    }
}
