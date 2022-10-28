package com.example.s3testing.controller;

import com.example.s3testing.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class FileUploadController {

    private final FileUploadService fileUploadService;

    @PostMapping("/api/v1/upload")
    public String uploadImage(@RequestPart MultipartFile file, @RequestPart(value = "roomId", required = true) int roomId){
        return fileUploadService.uploadImage(file, roomId);
    }

    @GetMapping("/api/v1/load")
    public String getImageUrl(String fileName, int roomId){
        return fileUploadService.loadImageUrl(fileName, roomId);
    }

    //  aws bucket에 실제로 접속해서 모든 데이터를 가져오는 메서드
    @GetMapping("/api/v1/loadRoomAllImages")
    public List<String> getAllImageUrlsByRoomId(int roomId){
        return fileUploadService.loadAllFileUrlsByRoomId(roomId);
    }


    @DeleteMapping("/api/v1/load")
    public String deleteImage(String fileName, int roomId){
        return fileUploadService.deleteImage(fileName, roomId);
    }
}
