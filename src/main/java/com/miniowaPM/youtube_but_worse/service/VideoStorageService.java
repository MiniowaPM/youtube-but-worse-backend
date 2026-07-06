package com.miniowaPM.youtube_but_worse.service;

import org.springframework.web.multipart.MultipartFile;

public interface VideoStorageService {
    String uploadVideo(MultipartFile videoFile);
}
