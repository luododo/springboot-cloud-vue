package com.ithr.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ithr.commonutils.Result;
import com.ithr.eduservice.entity.EduTeacher;
import com.ithr.eduservice.entity.vo.TeacherQuery;
import com.ithr.eduservice.service.EduTeacherService;
import com.ithr.servicebase.exceptionhandler.IthrException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.util.List;


/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author 子涵
 * @since 2020-04-03
 */
@Api(description = "讲师模块管理")
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {
      @Autowired
    private EduTeacherService eduTeacherService;

      //查询所有讲师测试成功
    @ApiOperation(value = "查询所有讲师")
      @GetMapping("selectTeacher")
    public Result selectTeacher(){
          List<EduTeacher> list = eduTeacherService.list(null);
        int size = list.size();
        return Result.ok().data("itmes",list).data("size",size);

//        //自定义异常
//        try {
//        int i=5/0;
//        }catch (Exception e){
//            new IthrException(20001,"自定义异常执行");
//        }

      }

    //逻辑删除测试成功
    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("delete/{id}")
    public Result delete(@ApiParam(name = "id", value = "讲师ID", required = true)
                           @PathVariable String id){
        boolean b = eduTeacherService.removeById(id);
        //如果真
        if(b){
            return Result.ok();
        }else {
            return Result.no();
        }
    }

    //分页查询
    //current 当前页
    //limit 每页显示几条数据
    @ApiOperation(value = "分页查询讲师")//测试成功
    @GetMapping("pageListTeacher/{current}/{limit}")
    public Result pageListTeacher(@PathVariable long current,@PathVariable long limit){
        Page<EduTeacher> page=new Page<>(current,limit);//page里面有所有数据
        eduTeacherService.page(page,null);
        long total = page.getTotal();//总记录数
       if(limit>total){
           return Result.no().message("没有这么多页参考total" +  "讲师有"+total+"条请输入"+total);
       }
        List<EduTeacher> records = page.getRecords();//返回list集合
//        Map<String,Object> map=new HashMap<>();
//        map.put("total",total);
//        map.put("records"records);
//        return Result.ok().data(map);
        return Result.ok().data("total",total).data("records",records);

    }
    @ApiOperation(value = "分页查+多条件")
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    //,@RequestBody这个注解意思是使用josn传递数据封装到对应的对象里面而且要post提交不然没有用
    //@RequestBody(requied=false)意思是可以不给参数  //测试成功
    public Result pageTeacherCondition(@PathVariable long current, @PathVariable long limit,@RequestBody(required = false) TeacherQuery teacherQuery){
        Page<EduTeacher> pages=new Page<>(current,limit);
        //条件实体
        QueryWrapper<EduTeacher> wrapper=new QueryWrapper<>();
       //动态sql
       //  teacherQuery这个是用户输入的值
        String name = teacherQuery.getName();
        Integer level =teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        //判断条件是否为空不为空就拼接语句
        if(!StringUtils.isEmpty(name)){
            //模糊查询 1字段 2最终输入的值
             wrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(level)){
            //等于
            wrapper.eq("level",level);
        }
        //开始时间
        if(!StringUtils.isEmpty(begin)){
         //大于等于 gmt_create创建时间
            //意思是输入的时间要大于创建时间
            wrapper.ge("gmt_create",begin);
        }
        //结束时间
        if(!StringUtils.isEmpty(end)){
         //小于等于
            //意思是结束的时间要小于创建时间
            wrapper.le("gmt_create",end);
        }
        //调用方法实现分页查询
        eduTeacherService.page(pages,wrapper);
        long total = pages.getTotal();
        if(limit>total){
            return Result.no().message("没有这么多页参考total" +  "讲师有"+total+"条请输入"+total);
        }
        List<EduTeacher> records = pages.getRecords();//返回list数据
        return Result.ok().data("total",total).data("records",records);
    }

    //添加讲师
    //万物都是开发接口  测试成功
    @ApiOperation(value = "添加讲师模块")// 测试成功
    @PostMapping("insertTecher")
    public Result insertTecher(@RequestBody EduTeacher eduTeacher){
        boolean save = eduTeacherService.save(eduTeacher);
        if(save){
            return  Result.ok();
        }else {
            return   Result.no();
        }
    }

    @ApiOperation(value = "根据id查询讲师")//测试成功
    @GetMapping("getTeacher/{id}")
    public Result getTeacher(@PathVariable String id){
        EduTeacher byId = eduTeacherService.getById(id);
        return Result.ok().data("teacher",byId);

    }
    @ApiOperation(value = "根据id查询讲师在修改")//测试成功
    @PostMapping("updateTeacher")
    public Result updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean update = eduTeacherService.updateById(eduTeacher);

        if(update){
            return Result.ok();
        }else {
            return Result.no();
        }

    }
    @ApiOperation(value = "修改方式二")//测试成功
    @PutMapping("update2/{id}")
    public Result update2(@PathVariable String id, @RequestBody EduTeacher eduTeacher){
        eduTeacher.setId(id);
        eduTeacherService.updateById(eduTeacher);
        return Result.ok();
    }

}

