package com.example.demo.retry.service;

import org.springframework.retry.annotation.CircuitBreaker;
import org.springframework.retry.annotation.Recover;
import org.springframework.stereotype.Service;

import com.example.demo.retry.exceptions.ValueException;
import com.example.demo.retry.repository.TestRepository1;

@Service
public class TestService {

	TestRepository1 testRepository1;

	public TestService(TestRepository1 testRepository1) {
		this.testRepository1 = testRepository1;
	}

	/* Open the circuit after 3 retries */
	@CircuitBreaker(maxAttempts = 3, //
			openTimeout = 15000l, //
			resetTimeout = 30000l)
	public String getResult(String id) throws ValueException {
		return testRepository1.getResult(id);
	}

	@Recover
	public String recover(NumberFormatException e, String id) {
		return "Service unavailable";
	}

	@Recover
	public String recover(ValueException e, String id) {
		return "Service unavailable";
	}

}
