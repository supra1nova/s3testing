package com.example.s3testing.service;

import com.example.s3testing.model.dto.RoomImage;
import com.example.s3testing.repository.dao.RoomImageDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class RoomImageService {

    private final RoomImageDao roomImageDao;
    private final FileUploadService fileUploadService;

    public int uploadRoomImage(MultipartFile file, int roomId){
        int res = 0;
        String roomImageName  = null;
        try {
            String url = fileUploadService.uploadImage(file, roomId);
            if(url != null){
                roomImageName = url.substring(url.lastIndexOf("/")+1);
                RoomImage roomImage = RoomImage.builder()
                        .roomId(roomId)
                        .imageUrl(roomImageName)
                        .build();
                res = roomImageDao.insertImage(roomImage);
            }
        } catch(Exception e) {
            e.printStackTrace();
            if(roomImageName != null) fileUploadService.deleteImage(roomImageName, roomId);
        }
        return res;
    }

    // aws s3에 실제 url 주소를 호출한다.
    public String loadRoomImage(String fileName, int roomId){
        String roomImageUrl;
        try{
            roomImageUrl = fileUploadService.loadImageUrl(fileName, roomId);
        } catch(Exception e) {
            e.printStackTrace();
            roomImageUrl = null;
        }
        return roomImageUrl;
    }

    public List<String> getAllImageNamesByRoomId(int roomId){
        return roomImageDao.getAllImageNamesByRoomId(roomId);
    }

    public int deleteImage(String fileName, int roomId){
        int res = 0;
        try {
            String s3res = fileUploadService.deleteImage(fileName, roomId);
            if(s3res != null){
                RoomImage roomImage = RoomImage.builder()
                        .roomId(roomId)
                        .imageUrl(fileName)
                        .build();
                Integer result = roomImageDao.deleteImage(roomImage);
                if(result != null) res = result;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}

//            List<String> imageUrls = fileUploadService.loadImageUrls(roomId);
//            Iterator imageUrlsIterator = imageUrls.iterator();
//            while(imageUrlsIterator.hasNext()){
//                String imageUrl = imageUrlsIterator.next().toString();
//                String[] splitImageUrl = imageUrl.split("/");
//                int intRoomIdFromSplit = Integer.valueOf(splitImageUrl[splitImageUrl.length-2]);
//                if(intRoomIdFromSplit == roomId) {
//                    resUrl = splitImageUrl[splitImageUrl.length-1];
//                    System.out.println("resUrl = " + resUrl);
//                    break;
//                }
//            }