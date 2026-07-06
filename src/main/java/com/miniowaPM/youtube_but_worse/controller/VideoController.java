package com.miniowaPM.youtube_but_worse.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miniowaPM.youtube_but_worse.dto.VideoUploadRequest;
import com.miniowaPM.youtube_but_worse.service.VideoStorageService;


@RestController
@RequestMapping("/api/video")
public class VideoController {
    private final VideoStorageService videoService;

    public VideoController(VideoStorageService videoService) {
        this.videoService = videoService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadVideo(@ModelAttribute VideoUploadRequest request) {
        String videoUploadResponse = videoService.uploadVideo(request.getVideoFile(), request.getTitle(), request.getDescription());

        return ResponseEntity.ok(videoUploadResponse);
    }
}
