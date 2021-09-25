package com.ithr.eduservice.service.impl;

import com.ithr.eduservice.entity.EduTeacher;
import com.ithr.eduservice.mapper.EduTeacherMapper;
import com.ithr.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author 子涵
 * @since 2020-04-03
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    public static void main(String[] args) {
        String A="1";
        String B="1";
        System.out.println(String.format("串层%s-%s", A, B));
    }

}
