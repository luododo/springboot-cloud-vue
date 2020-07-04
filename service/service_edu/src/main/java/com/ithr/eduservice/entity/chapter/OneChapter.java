package com.ithr.eduservice.entity.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author com.hr.Text
 * @date 2020/4/13 - 19:51
 */
@Data
public class OneChapter {
    private String id;
    private String title;
    //表示小节
    private List<TowChapter> towChapter=new ArrayList<>();
}
