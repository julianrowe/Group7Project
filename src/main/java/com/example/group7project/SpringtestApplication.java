package com.example.group7project;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;

// Runs the application
@SpringBootApplication
public class SpringtestApplication {

	public static void main(String[] args) {
		// TODO: Get Custom Domain Name and SSL Certificate to fix This site is not secure
		// TODO: Sort S3 Bucket by newest upload date
		// TODO: Show description of photo when clicked
		// TODO: Show Cognito username of who uploaded the photo when clicked
		// TODO: Add metadata to upload and show under photo
		// TODO: Add extra image pages and make page buttons work
		SpringApplication.run(SpringtestApplication.class, args);
	}
}