package com.example.demo.services;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.ResponseHeaderOverrides;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

@Service
public class S3Repository {

    private final AmazonS3Client s3Client;

    String bucket = "druiz-api-test-bucket";

    public S3Repository(AmazonS3Client s3Client) {
        this.s3Client = s3Client;
    }

    // other repository methods
    public void upload(String key, MultipartFile file) throws IOException {
        InputStream inputStream =  new BufferedInputStream(file.getInputStream());
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentDisposition("attachment; filename=" + file.getOriginalFilename());
        objectMetadata.setContentType(file.getContentType());
        s3Client.putObject(bucket, key, inputStream, objectMetadata);
    }

    public URL generatePresignedUrl(String key){
        int seconds = 30;
        Date date = new Date(new Date().getTime() + seconds * 1000);
        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucket, key);
        generatePresignedUrlRequest.setExpiration(date);
//        ResponseHeaderOverrides responseHeaderOverrides = new ResponseHeaderOverrides();
//        responseHeaderOverrides.setContentDisposition("attachment; filename=" + key);
//        generatePresignedUrlRequest.setResponseHeaders(responseHeaderOverrides);
        return s3Client.generatePresignedUrl(generatePresignedUrlRequest);
    }
}