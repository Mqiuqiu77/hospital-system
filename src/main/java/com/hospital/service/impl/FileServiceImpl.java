package com.hospital.service.impl;

import com.hospital.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl
        implements FileService {


    @Override
    public String upload(
            MultipartFile file){

        //文件校验

        //生成UUID

        //保存文件

        //返回地址
        return "";
    }

}