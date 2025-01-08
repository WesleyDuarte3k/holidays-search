package br.com.feriados.infrastructure.exception;

public class NotFoundCityException extends RuntimeException{
			public NotFoundCityException() {
				super("City not found.");
		}
}
