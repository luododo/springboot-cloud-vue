package com.ithr.oss.controller;


import com.ithr.commonutils.Result;
import com.ithr.oss.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author com.hr.Text
 * @date 2020/4/9 - 20:08
 */
@Api(description = "阿里云上传图片")
@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin//解决跨域
public class OssController {
    @Autowired
    private OssService ossService;

    //上传头像的方法
    @ApiOperation(value = "上传头像的方法")
    @PostMapping
    public Result uploadOssFile(MultipartFile file) {
        //获取上传文件  MultipartFile
        //返回上传到oss的路径
        String url = ossService.uploadFileAvatar(file);
        return Result.ok().data("url",url);
    }
}
