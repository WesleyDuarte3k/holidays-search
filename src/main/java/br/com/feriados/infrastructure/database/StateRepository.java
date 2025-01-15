package br.com.feriados.infrastructure.database;

import br.com.feriados.domain.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<State, Long> {

	State findByName(String stateName);
}
