package com.example.demo.retry.service;

import org.springframework.retry.annotation.CircuitBreaker;
import org.springframework.retry.annotation.Recover;
import org.springframework.stereotype.Service;

import com.example.demo.retry.exceptions.ValueException;
import com.example.demo.retry.repository.TestRepository2;

@Service
public class TestService2 {

	private TestRepository2 testRepository;

	public TestService2(TestRepository2 testRepository) {
		this.testRepository = testRepository;
	}

	/* Open the circuit after 3 retries */
	@CircuitBreaker(maxAttempts = 3, //
			openTimeout = 150000l, //
			resetTimeout = 300000l)
	public String getResult(String id) throws ValueException {
		return testRepository.getData(id);
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
