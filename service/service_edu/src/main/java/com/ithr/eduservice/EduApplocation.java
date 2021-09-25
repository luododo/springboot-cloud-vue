package com.ithr.eduservice;

import com.ithr.eduservice.entity.EduChapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author com.hr.Text
 * @date 2020/4/3 - 18:31
 */
@SpringBootApplication
@EnableDiscoveryClient//nacos注册
@EnableFeignClients//服务调用
@ComponentScan(basePackages = {"com.ithr"})
@EnableScheduling
public class EduApplocation {
    public static void main(String[] args) {
        SpringApplication.run(EduApplocation.class,args);

//        System.currentTimeMillis();  //得到一个时间
        EduChapter eduChapter=new EduChapter();

    }
}
