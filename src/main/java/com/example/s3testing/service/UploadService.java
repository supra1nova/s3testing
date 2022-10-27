package com.example.s3testing.service;

import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service
public interface UploadService {

    void uploadFile(InputStream inputStream, ObjectMetadata objectMetadata, String fileName, int roomId);

    String getFileUrl(String fileName, int roomId);

    void deleteFile(String fileName, int roomId);

    List<String> getFileUrls(int roomId);
}
