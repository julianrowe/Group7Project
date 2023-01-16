package com.example.group7project;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;

// Runs the application
@SpringBootApplication
public class SpringtestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringtestApplication.class, args);
		S3Util object1 = new S3Util();
		object1.getS3Objects();
	}
}