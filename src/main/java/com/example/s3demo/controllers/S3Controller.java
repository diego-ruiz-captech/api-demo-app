package com.example.s3demo.controllers;

import com.example.s3demo.services.S3Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;

@RestController
@RequestMapping("/")
class S3Controller {

    @Autowired
    S3Repository s3Repository;

    @GetMapping
    public ResponseEntity<?> hello() {

        return new ResponseEntity<>("hello", HttpStatus.OK);
    }

    @RequestMapping(value = "/upload/", method = RequestMethod.POST)
    public ResponseEntity<?> upload(@RequestParam(value = "key", required = true) String key,
            @RequestParam(value = "file", required = true) MultipartFile file) {
        try {
            s3Repository.upload(key, file);
            return new ResponseEntity<>("upload successful", HttpStatus.CREATED);
        } catch (IOException e){
            return new ResponseEntity<>("Error with upload", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/get_file/", method = RequestMethod.GET)
    public ResponseEntity<?> get_file(@RequestParam(value = "key", required = true) String key) {
        try {
            URL presignedUrl = s3Repository.generatePresignedUrl(key);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(presignedUrl.toURI());
            return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
        } catch (Exception e ){
            return new ResponseEntity<>("Error downloading file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}