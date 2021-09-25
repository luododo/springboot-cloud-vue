package com.ithr.eduservice.service;

import com.ithr.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ithr.eduservice.entity.subject.OneSubject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author 子涵
 * @since 2020-04-11
 */
public interface EduSubjectService extends IService<EduSubject> {
   //添加课程分类
    void saveSubject(MultipartFile file,EduSubjectService eduSubjectService);
    //课程分类树形
    List<OneSubject> getAlloneTwoSbject();

    public static void main(String[] args) {

          Set<String> ACTIVE_CELL = new HashSet<>();

         int[] antStay=new int[]{3,3,3,3,0,0,0,0};
        // 快速切换天线模式, D100不支持该模式
        String[] antPos=new String[]{"1-3","1-3","1-3","1-3","","","",""};
        // 根据锁状态变化，指定柜体层->只开启对应天线
        byte[] fixedAntStay = new byte[8];
        for (int i = 0; i < antPos.length; i++) { //"antPos":["1-3","1-3","1-3","1-3","","","",""],
            String cellPos = antPos[i];
            // 只开启打开过的柜子对应的天线
                if (StringUtils.isNotBlank(cellPos) && ACTIVE_CELL.contains(cellPos)) {
                fixedAntStay[i] = (byte) antStay[i];
                System.out.println(fixedAntStay[i] = (byte) antStay[i]);
            } else {
                fixedAntStay[i] = 0;
                System.out.println(fixedAntStay[i] = 0);
            }
        }
    }
}
