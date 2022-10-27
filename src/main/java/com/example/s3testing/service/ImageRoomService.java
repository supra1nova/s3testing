package com.example.s3testing.service;

import com.example.s3testing.model.dto.ImageRoom;
import com.example.s3testing.repository.dao.ImageRoomDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ImageRoomService {

    private final ImageRoomDao imageRoomDao;

    public int insertImage(ImageRoom imageRoom){
        return imageRoomDao.insertImage(imageRoom);
    }

    public String getImage(String fileName){
        ImageRoom imageRoom = imageRoomDao.getImage(fileName);
        return imageRoom.getImageUrl();
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
