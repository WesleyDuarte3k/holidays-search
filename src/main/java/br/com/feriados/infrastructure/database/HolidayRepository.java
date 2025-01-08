package br.com.feriados.infrastructure.database;

import br.com.feriados.domain.entity.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {
	Holiday findByCityIdAndAndDate(Long cityId, String date);

	List<Holiday> findAllByCityId(Long cityId);

	List<Holiday> findAllByCityName(String cityName);

	List<Holiday> findAllByDate(String date);

	List<Holiday> findAllByType(String type);
}
