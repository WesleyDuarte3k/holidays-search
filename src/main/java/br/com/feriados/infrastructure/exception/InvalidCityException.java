package br.com.feriados.infrastructure.exception;

public class InvalidCityException extends RuntimeException {
	public InvalidCityException() {
		super("Invalid city");
	}

}
