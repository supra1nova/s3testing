package com.example.s3testing.controller;

import com.example.s3testing.service.RoomImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/room-image")
public class RoomImageController {

    private final RoomImageService roomImageService;

    @PostMapping("/upload")
    public String uploadRoomImage(@RequestPart(value = "file", required = true) MultipartFile file, @RequestPart(value = "roomId", required = true) int roomId){
        return roomImageService.uploadRoomImage(file, roomId) != 0 ? "이미지 업로드에 성공했습니다." : "이미지 업로드 수행에 문제가 있습니다.";
    }

    @GetMapping("/load")
    public String loadRoomImage(String fileName, int roomId){
        return roomImageService.loadRoomImage(fileName, roomId);
    }

        // db 내 데이터를 가져오는 구조(aws 비접속)
    @GetMapping("/loadAllRoomImageNames")
    public List<String> getAllImageNamesByRoomId(int roomId){
        return roomImageService.getAllImageNamesByRoomId(roomId);
    }

    @PostMapping("/delete")
    public int deleteImage(String fileName, int roomId){
        return roomImageService.deleteImage(fileName, roomId);
    }

}
