package com.example.s3testing.repository.dao;

import com.example.s3testing.model.dto.ImageRoom;

public interface ImageRoomDao {
    int uploadImage(ImageRoom imageRoom);
}
