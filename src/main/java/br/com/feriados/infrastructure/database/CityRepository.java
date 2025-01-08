package br.com.feriados.infrastructure.database;

import br.com.feriados.domain.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CityRepository extends JpaRepository <City, Long> {
	City findByName(String name);
}
