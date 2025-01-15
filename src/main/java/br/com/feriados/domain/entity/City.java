package br.com.feriados.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


import java.util.List;

@Entity
@Table(name = "city")
public class City {
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Id
	private Long id;
	@Column(nullable = false)
	private String name;

	@ManyToOne
	@JoinColumn(name = "state_id", nullable = false)
	private State state;

	@OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
	private List<Holiday> holidays;

	public Long getId() {
		return id;
	}

	public City() {

	}

	public State getState() {
		return state;
	}

	public String getName() {
		return name;
	}
}
