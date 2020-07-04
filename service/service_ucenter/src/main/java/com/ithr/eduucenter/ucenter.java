package com.ithr.eduucenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author com.hr.Text
 * @date 2020/4/23 - 2:57
 */
@SpringBootApplication
@ComponentScan("com.ithr")
@MapperScan("com.ithr.eduucenter.mapper")
public class ucenter {
    public static void main(String[] args) {
        SpringApplication.run(ucenter.class,args);
    }
}
