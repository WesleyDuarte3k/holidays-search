package br.com.feriados.infrastructure.exception;

public class NotFoundStateException extends RuntimeException{
	public NotFoundStateException() {
		super("Not Found State");
	}
}
