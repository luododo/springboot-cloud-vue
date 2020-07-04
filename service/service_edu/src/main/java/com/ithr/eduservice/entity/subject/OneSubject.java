package com.ithr.eduservice.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author com.hr.Text
 * @date 2020/4/12 - 8:55
 */
//封装一级分类格式
    @Data
    public class OneSubject {
    private String id;
    private String title;
    //一个一级分类有多个二级分类
    private List<TowSubject> children=new ArrayList<>();
}
