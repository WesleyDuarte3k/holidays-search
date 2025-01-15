package br.com.feriados.domain.entity.dto;

import br.com.feriados.domain.entity.Holiday;

public class HolidayDTO {
	public String name;
	public String date;
	public String type;
	public String state;

	public HolidayDTO(String name, String date, String type, String state) {
		this.name = name;
		this.date = date;
		this.type = type;
		this.state = state;
	}


	public void setName(String name) {
		this.name = name;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setState(String state) {
		this.state = state;
	}

	public HolidayDTO(String name) {
		this.name = name;
	}

	public HolidayDTO() {

	}

}
