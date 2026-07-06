package com.miniowaPM.youtube_but_worse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miniowaPM.youtube_but_worse.model.Video;

public interface VideoRepository extends JpaRepository<Video, Long> {

}
