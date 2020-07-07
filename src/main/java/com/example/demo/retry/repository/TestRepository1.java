package com.example.demo.retry.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Repository;

import com.example.demo.retry.exceptions.ValueException;

@Repository
public class TestRepository1 {

	private final static Logger LOGGER = LoggerFactory.getLogger(TestRepository1.class);

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
		int number = 0;
		try {
			number = Integer.parseInt(id);
		} catch (NumberFormatException e) {
			LOGGER.error("Number format exception occurs, value is {}", id);
			throw e;
		}

		if (number == 0 || number > 100) {
			LOGGER.error("Value exception occur, value is {}", number);
			throw new ValueException("invalid input!");
		}

		return "test done!";
	}

}
