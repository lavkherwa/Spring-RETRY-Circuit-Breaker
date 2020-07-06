package com.example.demo.retry.repository;

import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.retry.exceptions.ValueException;

@Repository
public class TestRepository2 {

	private RetryTemplate retryTemplate;

	public TestRepository2(RetryTemplate retryTemplate) {
		this.retryTemplate = retryTemplate;
	}

	public String getData(String id) throws ValueException {
//		return retryTemplate.execute(new ProcessCallback(id), new ProcessRecoveryCallback());
		return retryTemplate.execute(new ProcessCallback(id));
	}

	private class ProcessCallback implements RetryCallback<String, ValueException> {

		private String id;

		public ProcessCallback(String id) {
			this.id = id;
		}

		@Override
		public String doWithRetry(RetryContext context) throws ValueException {

			/* Here goes your logic */
			System.out.println("test service is called with id " + id);

			/* Number format exception */
			int number = Integer.parseInt(id);

			/* Value exception if value exceeds 100 */
			if (number > 100) {
				throw new ValueException("invalid input!");
			}

			return id;
		}
	}

	/*-
	private class ProcessRecoveryCallback implements RecoveryCallback<String> {
	
		@Override
		public String recover(RetryContext context) {
	
			String result;
	
			if (context.getLastThrowable() instanceof NumberFormatException) {
				result = "recovered from number format exception";
			} else if (context.getLastThrowable() instanceof ValueException) {
				result = "recovered from value exception";
			} else {
				result = "recovered";
			}
	
			return result;
		}
	}
	*/

}
