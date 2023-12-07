package com.jessie.controller;

import com.jessie.pojo.Result;
import com.jessie.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
public class UploadController {

    @Autowired
    private AliOSSUtils aliOSSUtils;

    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws Exception {
        log.info("文件上传,{}", image);
        String url = aliOSSUtils.upload(image);
        log.info("文件上传完成，文件访问的url:{}", url);

        return Result.success(url);

    }
}
