package br.com.feriados.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "state")
public class State {
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Id
	private Long id;

	@Column(nullable = false, unique = true)
	private String name;

	@OneToMany(mappedBy = "state", cascade = CascadeType.ALL)
	private List<City> cities;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<City> getCities() {
		return cities;
	}

	public State() {

	}
}
