package com.example.demo.retry.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;

@Configuration
//@Profile({ "cloud", "local", "default" })
@EnableRetry(proxyTargetClass = true)
//@PropertySource("classpath:resilience.properties")
public class Config {

}
