package com.ithr.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author com.hr.Text
 * @date 2020/4/3 - 18:31
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.ithr"})
public class EduApplocation {
    public static void main(String[] args) {
        SpringApplication.run(EduApplocation.class,args);
    }
}
