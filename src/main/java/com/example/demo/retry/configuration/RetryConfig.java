package com.example.demo.retry.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

@Configuration
public class RetryConfig {

	@Value("${retry.max.attempts}")
	private int maxAttemps;
	
	@Value("${retry.backoff.delay}")
	private long backOffDelay;

	
	@Bean
	public RetryTemplate retryTemplate() {
		RetryTemplate retryTemplate = new RetryTemplate();

		FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
		fixedBackOffPolicy.setBackOffPeriod(backOffDelay);
		retryTemplate.setBackOffPolicy(fixedBackOffPolicy);

		SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
		retryPolicy.setMaxAttempts(maxAttemps);
		retryTemplate.setRetryPolicy(retryPolicy);

		return retryTemplate;
	}
}