package com.example.s3testing.service;

import com.example.s3testing.model.dto.ImageRoom;
import com.example.s3testing.repository.dao.ImageRoomDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ImageRoomService {

    private final ImageRoomDao imageRoomDao;

    public int uploadRoomImage(ImageRoom imageRoom){
        return imageRoomDao.uploadImage(imageRoom);
    }
}
