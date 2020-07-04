package com.ithr.msmservvice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @author com.hr.Text
 * @date 2020/4/23 - 1:35
 */

@SpringBootApplication
@ComponentScan("com.ithr")
public class msm {
    public static void main(String[] args) {
        SpringApplication.run(msm.class,args);
    }
}
