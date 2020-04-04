package com.ithr.servicebase.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author com.hr.Text
 * @date 2020/4/4 - 1:20
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    //自动填充添加触发
    @Override
    public void insertFill(MetaObject metaObject) {
        //这个传递的是属性名称不是字段
        this.setFieldValByName("gmtCreate",new Date(),metaObject);
        this.setFieldValByName("gmtModified",new Date(),metaObject);
    }
    //修改触发
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("gmtModified",new Date(),metaObject);
    }
}
