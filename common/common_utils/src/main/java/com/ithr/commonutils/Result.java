package com.ithr.commonutils;

import com.baomidou.mybatisplus.extension.api.R;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author com.hr.Text
 * @date 2020/4/3 - 20:42
 */
//统一返回结果类型
    @Data
    public class Result {
    @ApiModelProperty(value = "是否成功")
    private Boolean success;
    @ApiModelProperty(value = "返回码")
    private  Integer code;
    @ApiModelProperty(value = "返回消息")
    private String message;
    @ApiModelProperty(value = "返回数据")
    private Map<String,Object> data=new HashMap<String, Object>();
    //构造方法私有别人不能nwe
    private Result(){}

    //成功
   public static  Result ok(){
     Result rs=new Result();
     rs.setSuccess(true);
     rs.setCode(ResultCode.SUCCESS );
     rs.setMessage("成功");
     return rs;
   }
    //失败
    public static  Result no(){
        Result rs=new Result();
        rs.setSuccess(false);
        rs.setCode(ResultCode.ERROP );
        rs.setMessage("失败");
        return rs;
    }
    public Result success(Boolean success){
         this.setSuccess(success);
        return this;
    }
    public Result code(Integer code){
        this.setCode(code);
        return this;
    }
    public Result message(String message){
        this.setMessage(message);
        return this;
    }
    public Result data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public Result data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
}
