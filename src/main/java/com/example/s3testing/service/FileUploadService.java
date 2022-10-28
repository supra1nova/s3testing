package com.example.s3testing.service;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.example.s3testing.model.dto.RoomImage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FileUploadService {

    private final UploadService s3service;
    private final RoomImageService roomImageService;

    // Multipart를 통해 전송된 파일을 업로드하는 메서드
    public String uploadImage(MultipartFile file, int roomId){
        String fileName = createFileName(file.getOriginalFilename());
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());
        try (InputStream inputStream = file.getInputStream()) {
            s3service.uploadFile(inputStream, objectMetadata, fileName, roomId);
        } catch(Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException(String.format("파일 변환 중 에러가 발생했습니다."));
        }
        return s3service.getFileUrl(fileName, roomId);
    }

    public int uploadRoomImage(MultipartFile file, int roomId){
//        String fileName = roomId + "/" + createFileName(file.getOriginalFilename());
        String fileName = createFileName(file.getOriginalFilename());
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());
        try (InputStream inputStream = file.getInputStream()) {
            s3service.uploadFile(inputStream, objectMetadata, fileName, roomId);
        } catch(Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException(String.format("파일 변환 중 에러가 발생했습니다."));
        }
        String url = s3service.getFileUrl(fileName, roomId);
        int res = 0;
        if(url != null){
            String roomImageName = url.substring(url.lastIndexOf("/")+1);
            RoomImage roomImage = RoomImage.builder()
                    .roomId(roomId)
                    .imageUrl(roomImageName)
                    .build();
            res = roomImageService.insertImage(roomImage);
        }

        return res;
    }

    // 기존 확장자명을 유지한 채, 유니크한 파일명을 생성하는 로직
    // s3 저장시 동일 파일명 존재시 에러 발생
    private String createFileName(String originalFileName){
        return UUID.randomUUID().toString().concat(getFileExtension(originalFileName));
    }

    public String getImage(String fileName, int roomId) {
        try {
            return s3service.getFileUrl(fileName, roomId);
        } catch (Exception e){
            e.printStackTrace();
            throw new IllegalArgumentException("파일명이 존재하지 않습니다.");
        }
    }

    public String getRoomImage(String fileName, int roomId) {
        try {
            String roomImageName = roomImageService.getImage(fileName);
            return s3service.getFileUrl(roomImageName, roomId);
        } catch (Exception e){
            e.printStackTrace();
            throw new IllegalArgumentException("파일명이 존재하지 않습니다.");
        }
    }

    public List<String> getFileUrls(int roomId){
        return s3service.getFileUrls(roomId);
    }

    public List<String> getAllImageUrlByRoomId(int roomId){
        return roomImageService.getAllImageUrlByRoomId(roomId);
    }

    public String deleteImage(String fileName, int roomId) {
        try {
            s3service.deleteFile(fileName, roomId);
            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("파일이 존재하지 않습니다. 다시 한 번 확인해 주세요.");
        }
    }

    // 기존 파일의 확장자명을 가져오는 로직
    private String getFileExtension(String originalFileName) {
        try {
            return originalFileName.substring(originalFileName.lastIndexOf("."));
        } catch(Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException(String.format("잘못된 형식의 파일 이름(%s) 입니다.", originalFileName));
        }
    }
}
