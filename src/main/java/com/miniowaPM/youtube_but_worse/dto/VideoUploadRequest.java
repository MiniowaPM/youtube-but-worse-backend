package com.miniowaPM.youtube_but_worse.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class VideoUploadRequest {
    private MultipartFile videoFile;
    private String title;
    private String description;

    public void setVideoFile(MultipartFile videoFile) {
        this.videoFile = videoFile;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}