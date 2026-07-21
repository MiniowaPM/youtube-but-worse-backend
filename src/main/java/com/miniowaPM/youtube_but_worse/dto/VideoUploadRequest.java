package com.miniowaPM.youtube_but_worse.dto;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class VideoUploadRequest {
    private MultipartFile videoFile;
    private MultipartFile thumbnailFile;
    private String title;
    private String author;
    private String description;
    private Integer views;
    private LocalDateTime releaseDate;


    public void setVideoFile(MultipartFile videoFile) {
        this.videoFile = videoFile;
    }

    public void setThumbnailFile(MultipartFile thumbnailFile) {
        this.thumbnailFile = thumbnailFile;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }
}