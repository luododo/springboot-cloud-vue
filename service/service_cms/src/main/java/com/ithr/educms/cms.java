package com.ithr.educms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author com.hr.Text
 * @date 2020/4/22 - 15:40
 */
@SpringBootApplication
@ComponentScan({"com.ithr"}) //指定扫描位置
@MapperScan("com.ithr.educms.mapper")
public class cms {
    public static void main(String[] args) {
        SpringApplication.run(cms.class,args);
    }
}
