package br.com.feriados.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "holiday")
public class Holiday {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	@Column(name = "holiday_date")
	private String date;

	@Enumerated(EnumType.STRING)
	@Column(name = "holiday_type")
	private HolidayType type;

	@JoinColumn(name = "city_id")
	@ManyToOne
	private City city;

	@JoinColumn(name = "state_id")
	@ManyToOne
	private State state;

	public Holiday() {
	}

	public Holiday(String name, String date, HolidayType type) {
		this.name = name;
		this.date = date;
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDate() {
		return date;
	}

	public HolidayType getType() {
		return type;
	}

}
