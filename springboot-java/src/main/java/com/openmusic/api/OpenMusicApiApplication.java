package com.openmusic.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.openmusic.api.repository.jpa")
public class OpenMusicApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenMusicApiApplication.class, args);
	}

}
