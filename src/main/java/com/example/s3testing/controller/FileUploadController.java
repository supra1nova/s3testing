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

    @PostMapping("/api/v1/uploadRoomImage")
    public int uploadRoomImage(@RequestPart(value = "file", required = true) MultipartFile file, @RequestPart(value = "roomId", required = true) int roomId){
        return fileUploadService.uploadRoomImage(file, roomId);
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
