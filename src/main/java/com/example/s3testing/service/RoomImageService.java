package com.example.s3testing.service;

import com.example.s3testing.model.dto.RoomImage;
import com.example.s3testing.repository.dao.RoomImageDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RoomImageService {

    private final RoomImageDao roomImageDao;

    public int insertImage(RoomImage roomImage){
        return roomImageDao.insertImage(roomImage);
    }

    public String getImage(String fileName){
        RoomImage roomImage = roomImageDao.getImage(fileName);
        return roomImage.getImageUrl();
    }

    public List<String> getAllImageUrlByRoomId(int roomId){
        System.out.println("roomId = " + roomId);
        return roomImageDao.getAllImageUrlByRoomId(roomId);
    }

//    public String getImage(String fileName){
//        String roomImageId = imageRoomDao.getImage(fileName);
//        String roomImageUrl = null;
//        try{
//            roomImageUrl = fileUploadService.getRoomImage(roomImageId);
//        } catch(Exception e) {
//            e.printStackTrace();
//            roomImageUrl = null;
//        }
//        return roomImageUrl;
//    }
}
