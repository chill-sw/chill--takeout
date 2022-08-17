package com.chill.controller;

import com.chill.common.Result;
import com.chill.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
@CrossOrigin
public class FileController {
    @Autowired
    FileService fileService;

    @PostMapping("upload")
    public Result uploadFileOSS(MultipartFile file){
        return fileService.uploadFileOSS(file);
    }
}
