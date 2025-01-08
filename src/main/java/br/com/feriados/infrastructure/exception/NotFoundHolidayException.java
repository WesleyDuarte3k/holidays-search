package br.com.feriados.infrastructure.exception;

public class NotFoundHolidayException  extends RuntimeException{
	public NotFoundHolidayException() {
		super("Not Found Holiday");
	}
}
