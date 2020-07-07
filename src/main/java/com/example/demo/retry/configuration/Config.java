package com.example.demo.retry.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;

//@Profile({ "cloud", "local", "default" })
//@PropertySource("classpath:default.properties")
@Configuration
@EnableRetry(proxyTargetClass = true)
public class Config {

}
