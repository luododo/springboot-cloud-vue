package com.ithr.eduservice.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author com.hr.Text
 * @date 2020/4/11 - 13:23
 */
@Data
public class SubjecData {
    //创建一个跟Excel对应的实体类
    @ExcelProperty(index = 0)
    private String oneSubjectName;
    @ExcelProperty(index = 1)
    private String twoSubjectName;
}
