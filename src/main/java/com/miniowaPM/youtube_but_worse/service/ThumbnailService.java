package com.miniowaPM.youtube_but_worse.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.springframework.stereotype.Service;

import com.miniowaPM.youtube_but_worse.config.StorageProperties;

@Service
public class ThumbnailService {
    private final StorageProperties storageProperties;

    public ThumbnailService(StorageProperties storageProperties) {
        this.storageProperties = storageProperties;
    }

    public String generateThumbnail(String videoFilePath){
        String thumbnailFilename = UUID.randomUUID().toString() + ".jpg";
        String fullThumbnailPath = storageProperties.getPath() + thumbnailFilename;

        try (FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(videoFilePath)) {
            grabber.start();

            int lengthInFrames = grabber.getLengthInFrames();
            int middleFrame = lengthInFrames / 2;

            grabber.setFrameNumber(middleFrame);
            Frame frame = grabber.grabImage();

            try (Java2DFrameConverter converter = new Java2DFrameConverter()) {
                BufferedImage bufferedImage = converter.getBufferedImage(frame);
                
                File outputImage = new File(fullThumbnailPath);
                ImageIO.write(bufferedImage, "jpg", outputImage);
            }

            grabber.stop();

        return fullThumbnailPath;

        } catch (Exception e) {
            throw new RuntimeException("Błąd podczas generowania miniatury z wideo", e);
        }
    }
}
