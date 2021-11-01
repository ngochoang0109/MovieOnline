package com.tlcn.movieonline.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.tlcn.movieonline.service.AwsS3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;


import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;


@Service
public class AwsS3ServiceImpl implements AwsS3Service {

    @Autowired
    private S3Client awsS3;

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${aws.s3.bucket}")
    private String bucket;

    @Value("${aws.endpoint}")
    private String endPoint;

    @Override
    public String uploadFile(MultipartFile param) {
        try {
            File file= convertMultipartFileToFile(param);
            String uniqueNameFile= file.getName() +"_"+ LocalDateTime.now();
            PutObjectRequest putObjectRequest= PutObjectRequest.builder()
                    .bucket(bucket).key(uniqueNameFile).build();
            awsS3.putObject(putObjectRequest, RequestBody.fromBytes(param.getBytes()));
            file.delete();
            return uniqueNameFile;
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            return "";
        }
    }

    private File convertMultipartFileToFile(MultipartFile multipartFile) throws Exception {
        File file = new File(multipartFile.getOriginalFilename());
        try{
            FileOutputStream fileOutputStream= new FileOutputStream(file);
            fileOutputStream.write(multipartFile.getBytes());
            fileOutputStream.close();
        }
        catch (Exception e){
            throw new Exception("File rỗng!!!!");
        }
        return file;
    }

    @Override
    public ResponseEntity<byte[]> getVideoBytes(String keyName) {
        try {
            //get object
            GetObjectRequest getObjectRequest= GetObjectRequest.builder()
                    .bucket(bucket)
                    .key(keyName)
                    .build();
            ResponseBytes<GetObjectResponse> objectBytes= awsS3.getObjectAsBytes(getObjectRequest);
            return ResponseEntity.status(HttpStatus.OK)
                    .header("Content-Type", "video/mp4")
                    .header("Content-Length", String.valueOf(objectBytes.asByteArray().length))
                    .body(objectBytes.asByteArray());
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }


}
