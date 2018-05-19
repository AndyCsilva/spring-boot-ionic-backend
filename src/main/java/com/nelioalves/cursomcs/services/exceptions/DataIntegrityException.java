package com.nelioalves.cursomcs.services.exceptions;

public class DataIntegrityException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataIntegrityException(String msg) {
		super(msg);
	}

	public DataIntegrityException(Throwable cause) {
		super(cause);
	}

	public DataIntegrityException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
