package com.openmusic.api;

import com.openmusic.api.config.ApplicationProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Arrays;

@EnableConfigurationProperties(ApplicationProperties.class)
@EnableJpaRepositories(basePackages = "com.openmusic.api.repository.jpa")
@SpringBootApplication()
public class OpenMusicApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenMusicApiApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext context) {
		return args -> {
			System.out.println("Let's inspect the beans provided by Spring Boot:");
			System.out.println("Application Context Jumlah Bean : " + context.getBeanDefinitionNames().length);
			Arrays.asList(context.getBeanDefinitionNames()).forEach(System.out::println);

		};
	}

}
