package com.example.demo.retry.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.retry.annotation.EnableRetry;

@Configuration
@Profile({ "cloud", "local", "default" })
@PropertySource("classpath:default.properties")
@EnableRetry(proxyTargetClass = true)
public class Config {

}
