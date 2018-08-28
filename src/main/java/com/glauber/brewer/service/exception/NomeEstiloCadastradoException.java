package com.glauber.brewer.service.exception;

public class NomeEstiloCadastradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NomeEstiloCadastradoException(String message) {
		super(message);
	}
	
	public NomeEstiloCadastradoException(String message, Throwable cause) {
        super(message, cause);
    }
}
