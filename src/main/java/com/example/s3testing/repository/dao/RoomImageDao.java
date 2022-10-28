package com.example.s3testing.repository.dao;

import com.example.s3testing.model.dto.RoomImage;

import java.util.List;

public interface RoomImageDao {
    int insertImage(RoomImage roomImage);
    RoomImage getImage(String fileName);
    List<String> getAllImageNamesByRoomId(int roomId);
    int deleteImage(RoomImage roomImage);
}
