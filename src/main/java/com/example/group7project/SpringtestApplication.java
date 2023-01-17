package com.example.group7project;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;

// Runs the application
@SpringBootApplication
public class SpringtestApplication {

	public static void main(String[] args) {
		// TODO: Fix Cognito not working on deployed EC2 server (need https)
		// TODO: Show description of photo when clicked
		// TODO: Show Cognito username of who uploaded the photo when clicked
		// TODO: Add extra image pages and make page buttons work
		SpringApplication.run(SpringtestApplication.class, args);
	}
}