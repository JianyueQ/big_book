package com.big_event.controller;


import com.big_event.pojo.Result;
import com.big_event.utils.AliOssUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;


@RestController
public class FileUploadController {

    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws Exception {
//        用于上传文件(单文件)
        String originalFilename = file.getOriginalFilename();
        //保证文件名的名字是唯一的，防止文件被覆盖
        String fileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
//        file.transferTo(new File("C:\\Users\\彼岸有霞\\Desktop\\big_event\\file\\"+fileName));
        String url = AliOssUtils.uploadFile(fileName, file.getInputStream());
        return Result.success(url);
    }

}
