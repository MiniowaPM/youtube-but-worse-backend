package com.miniowaPM.youtube_but_worse.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.miniowaPM.youtube_but_worse.service.VideoStorageService;


@RestController
@RequestMapping("/api/videos")
public class VideoController {
    private final VideoStorageService videoService;

    public VideoController(VideoStorageService videoService) {
        this.videoService = videoService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadVideo(@RequestBody MultipartFile videoFile) {
        String videoUploadResponse = videoService.uploadVideo(videoFile);

        return ResponseEntity.ok(videoUploadResponse);
    }
    
}
