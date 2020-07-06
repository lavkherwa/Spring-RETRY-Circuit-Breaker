package com.example.demo.retry.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class RetryAspect {

	private RetryTemplate retryTemplate;

	public RetryAspect(RetryTemplate retryTemplate) {
		this.retryTemplate = retryTemplate;
	}

	/*
	 * Around all the classed within package Service
	 * 
	 * @Around("within(com.example.demo.retry.service..*)") public Object
	 * retry(final ProceedingJoinPoint point) throws Throwable { return
	 * retryTemplate.execute(retryCallback -> point.proceed()); }
	 */

}
