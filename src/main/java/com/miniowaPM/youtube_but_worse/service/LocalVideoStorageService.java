package com.miniowaPM.youtube_but_worse.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.miniowaPM.youtube_but_worse.config.StorageProperties;
import com.miniowaPM.youtube_but_worse.model.Video;
import com.miniowaPM.youtube_but_worse.repository.VideoRepository;

@Service
public class LocalVideoStorageService implements VideoStorageService {
    
    private final VideoRepository videoRepository;
    private final StorageProperties storageProperties;
    private final ThumbnailService thumbnailService;

    public LocalVideoStorageService(VideoRepository videoRepository, StorageProperties storageProperties, ThumbnailService thumbnailService) {
        this.videoRepository = videoRepository;
        this.storageProperties = storageProperties;
        this.thumbnailService = thumbnailService;
    }

    @Override
    public String uploadVideo(MultipartFile videoFile, String title, String description) {
        try{
            Path storagePath = Paths.get(storageProperties.getPath());
            if (Files.notExists(storagePath)) {
                try {
                    Files.createDirectories(storagePath);
                } catch (IOException e) {
                    throw new RuntimeException("Failed to create storage directory.", e);
                }
            }

            String orginalFilename = videoFile.getOriginalFilename();
            String uniqueFilename = UUID.randomUUID().toString() + "_" + orginalFilename;

            Path videoFilePath = storagePath.resolve(uniqueFilename);
            
            Files.copy(videoFile.getInputStream(), videoFilePath, StandardCopyOption.REPLACE_EXISTING);

            String author = "author";

            String thumbnailUrlPath = thumbnailService.generateThumbnail(videoFilePath.toString());

            Video video = new Video();
            video.setTitle(title);
            video.setUrl(videoFilePath.toString());
            video.setAuthor(author);
            video.setDescription(description);
            video.setViews(0);
            video.setThumbnailUrl(thumbnailUrlPath);
            
            videoRepository.save(video);

            return "File uploaded successfully! ID in database: " + video.getId();
        } catch (IOException e) {
            throw new RuntimeException("Failed to store video file.", e);
        }
    }
}