package com.example.s3testing.service;

import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public interface UploadService {

    void uploadFile(InputStream inputStream, ObjectMetadata objectMetadata, String fileName);

    String getFileUrl(String fileName);

    void deleteFile(String fileName);
}
