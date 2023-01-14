package com.example.group7project;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebController implements WebMvcConfigurer 
{
	
    @RequestMapping("/AmazonCognito")
    public ModelAndView AmazonCognito () 
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cognito.html");
        return modelAndView;
    }
}