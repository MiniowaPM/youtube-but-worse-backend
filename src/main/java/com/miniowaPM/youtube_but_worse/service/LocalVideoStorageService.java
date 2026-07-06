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

    public LocalVideoStorageService(VideoRepository videoRepository, StorageProperties storageProperties) {
        this.videoRepository = videoRepository;
        this.storageProperties = storageProperties;
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

            Path filePath = storagePath.resolve(uniqueFilename);
            
            Files.copy(videoFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            Video video = new Video();
            video.setTitle(title);
            video.setDescription(description);
            video.setUrl(filePath.toString());
            
            videoRepository.save(video);

            return "File uploaded successfully! ID in database: " + video.getId();
        } catch (IOException e) {
            throw new RuntimeException("Failed to store video file.", e);
        }
    }
}