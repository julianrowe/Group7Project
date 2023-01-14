package com.example.group7project;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringtestApplication 
{

	public static void main(String[] args) 
	{
		SpringApplication.run(SpringtestApplication.class, args);
	}
}