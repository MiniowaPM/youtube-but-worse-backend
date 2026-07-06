package com.miniowaPM.youtube_but_worse.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class LocalVideoStorageService implements VideoStorageService {
    
    @Override
    public String uploadVideo(MultipartFile videoFile) {
        // Implement the logic to store the video file locally
        return "E:/videos/" + videoFile.getOriginalFilename();
    }

}
