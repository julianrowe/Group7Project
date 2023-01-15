package com.example.group7project;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// Configures AWS Cognito login and logout
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter 
{
    @Override
    protected void configure(HttpSecurity http) throws Exception 
    {
        http.csrf()
            .and()
            .oauth2Login()
            .and()
            .logout()
            .logoutSuccessUrl("/");
    }
}