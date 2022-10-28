package com.example.s3testing.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.example.s3testing.model.dto.S3Component;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class AwsS3UploadService implements UploadService {
    private final AmazonS3 amazonS3;
    private final S3Component component;

    @Override
    public void uploadFile(InputStream inputStream, ObjectMetadata objectMetadata, String fileName, int roomId){
        amazonS3.putObject(new PutObjectRequest(component.getBucket() + "/" + component.getProductRoomPath() + "/" + roomId, fileName, inputStream, objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead));
    }

    @Override
    public String getFileUrl(String fileName, int roomId){
        return amazonS3.getUrl(component.getBucket() + "/" + component.getProductRoomPath() + "/" + roomId, fileName).toString();
    }

    @Override
    public void deleteFile(String fileName, int roomId) {
        amazonS3.deleteObject(component.getBucket() + "/" + component.getProductRoomPath() + "/" + roomId, fileName);
    }

    @Override
    public List<String> getFileUrls(int roomId) {
        List<String> list = new ArrayList<>();
        ListObjectsRequest listObject = new ListObjectsRequest();
        listObject.setBucketName(component.getBucket());
        listObject.setPrefix(component.getProductRoomPath() + "/" + roomId);
        ObjectListing objects = amazonS3.listObjects(listObject);
        do {
            // 이곳에 명시할 bucketName은 디렉토리를 제외해야 정상적으로 작동할 것 같다. -> prefix에 directory 정보를 모두 넣는다
            objects = amazonS3.listObjects(objects.getBucketName(), objects.getPrefix());
            //1000개 단위로 읽음
            for (S3ObjectSummary objectSummary : objects.getObjectSummaries()) {
                String objectKey = objectSummary.getKey();
                System.out.println("objectKey = " + objectKey);
                list.add(objectKey);
            }
//            objects = s3.listNextBatchOfObjects(objects);  <--이녀석은 1000개 단위로만 가져옴..
            listObject.setMarker(objects.getNextMarker());
        } while (objects.isTruncated());
        return list;
    }
}
