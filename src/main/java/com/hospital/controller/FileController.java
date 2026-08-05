package com.hospital.controller;


import com.hospital.common.Result;
import com.hospital.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
@RestController
@RequestMapping("/file")
public class FileController {


    @Autowired
    private FileService fileService;


    @PostMapping("/upload")
    public Result<String> upload(
            MultipartFile file){

        String url =
                fileService.upload(file);

        return Result.success(url);

    }

}