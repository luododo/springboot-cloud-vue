package com.ithr.eduservice.controller;


import com.ithr.commonutils.Result;
import com.ithr.eduservice.entity.EduChapter;
import com.ithr.eduservice.entity.chapter.OneChapter;
import com.ithr.eduservice.service.EduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author 子涵
 * @since 2020-04-12
 */
@RestController
@RequestMapping("/eduservice/chapter")
@CrossOrigin//解决跨域

public class EduChapterController {
    @Resource
    private EduChapterService chapterService;
    //大纲列表 根据课程id进行查询
    @GetMapping("getChapterVideo/{courseId}")
    public Result getChapterVideo(@PathVariable String courseId){
      List<OneChapter> list= chapterService.getChapterVideoByCourseId(courseId);
        return Result.ok().data("list",list);
    }
     //添加章节
      @PostMapping("addChapter")
        public Result addChapter(@RequestBody EduChapter eduChapter){
          chapterService.save(eduChapter);
          return Result.ok();
      }
      //根据id查章节
    @GetMapping("getChapter/{id}")
    public Result getChapter(@PathVariable String id){
        EduChapter byId = chapterService.getById(id);
        return Result.ok().data("byId",byId);
    }
    //修改章节
    @PostMapping("updateChapter")
    public Result updateChapter(@RequestBody EduChapter eduChapter){
        chapterService.updateById(eduChapter);
        return Result.ok();
    }
    //删除
    @DeleteMapping("deleteChapter/{id}")
    public Result deleteChapter(@PathVariable String id){
        boolean b = chapterService.deleteChapter(id);
        if(b){
            return Result.ok();

        }else {
            return Result.no();
        }

    }
}

