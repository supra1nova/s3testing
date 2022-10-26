package com.example.s3testing.controller;

import com.example.s3testing.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
public class FileUploadController {

    private final FileUploadService fileUploadService;

    @PostMapping("/api/v1/upload")
    public String uploadImage(@RequestPart MultipartFile file){
        return fileUploadService.uploadImage(file);
    }

    @GetMapping("/api/v1/load")
    public String getImage(String fileName){
        return fileUploadService.getImage(fileName);
    }

    @DeleteMapping("/api/v1/load")
    public String deleteImage(String fileName){
        return fileUploadService.deleteImage(fileName);
    }
}
