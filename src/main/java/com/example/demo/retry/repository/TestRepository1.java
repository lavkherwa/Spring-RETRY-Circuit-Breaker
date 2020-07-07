package com.example.demo.retry.repository;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Repository;

import com.example.demo.retry.exceptions.ValueException;

@Repository
public class TestRepository1 {

	/*- This will retry 3 times 
	@Retryable(value = { NumberFormatException.class, ValueException.class }, //
			maxAttempts = 3, //
			backoff = @Backoff(delay = 1000))
	*/
	@Retryable(value = { NumberFormatException.class, ValueException.class }, //
			maxAttemptsExpression = "#{${retry.max.attempts}}", //
			backoff = @Backoff(delayExpression = "#{${retry.backoff.delay}}"))
	public String getResult(String id) throws ValueException {
		System.out.println("test service is called with id " + id);

		/* This should through exception when input is not number */
		int number = Integer.parseInt(id);

		if (number > 100) {
			throw new ValueException("invalid input!");
		}

		return "test done!";
	}

}
