package com.example.demo.retry.exceptions;

public class ValueException extends Exception {

	private static final long serialVersionUID = -3509503988782429803L;

	public ValueException(String message) {
		super(message);
	}

	public ValueException(String message, Throwable e) {
		super(message, e);
	}

	public ValueException(Throwable e) {
		super(e);
	}

}
