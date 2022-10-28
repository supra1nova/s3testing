package com.example.s3testing.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//@Getter
//@Setter
//@ConfigurationProperties(prefix = "cloud.aws.s3")
//@Component
//public class S3Component {
//
//    private String bucket;
//
//}

@Getter
@Setter
@Component
public class S3Component {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value(value = "product")
    private String productPath;

    @Value(value = "product/room")
    private String productRoomPath;

}
