package com.miniowaPM.youtube_but_worse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class YoutubeButWorseApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(YoutubeButWorseApplication.class, args);
		System.out.println("Application started with context: " + applicationContext);
	}

}
