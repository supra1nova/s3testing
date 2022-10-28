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

    @Value("${cloud.aws.s3.folder.room}")
    private String room;

    @Value("${cloud.aws.s3.folder.product}")
    private String product;
}
