package com.hospital.service.impl;

import com.hospital.exception.BusinessException;
import com.hospital.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
@Service
public class FileServiceImpl
        implements FileService {

    private static final Path UPLOAD_DIRECTORY =
            Paths.get("D:/upload").toAbsolutePath().normalize();

    @Override
    public String upload(
            MultipartFile file){

        //文件校验
        if (file == null || file.isEmpty()) {
            throw new BusinessException("上传文件不能为空");
        }

        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || originalFilename.isBlank()) {
            throw new BusinessException("上传文件名不能为空");
        }

        String safeFilename = Paths.get(originalFilename).getFileName().toString();
        int extensionIndex = safeFilename.lastIndexOf('.');
        String extension = extensionIndex > 0 && extensionIndex < safeFilename.length() - 1
                ? safeFilename.substring(extensionIndex)
                : "";

        //生成UUID
        String storedFilename = UUID.randomUUID().toString().replace("-", "") + extension;
        Path target = UPLOAD_DIRECTORY.resolve(storedFilename).normalize();

        //保存文件
        try {
            Files.createDirectories(UPLOAD_DIRECTORY);
            file.transferTo(target);
        } catch (IOException e) {
            log.error("保存上传文件失败，目标路径：{}", target, e);
            throw new BusinessException("文件上传失败");
        }

        //返回地址
        return target.toString();
    }

}
