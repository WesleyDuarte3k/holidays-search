package br.com.feriados.domain.service;

import br.com.feriados.domain.entity.Holiday;



import java.util.List;

public interface HolidayService {
	public Holiday findByCityAndDate(Long cityId, String date);

	public List<Holiday> findAllByType(Enum holidayType);

	public List<Holiday> findAllByCityName(String city);

	public List<Holiday> findAllByDate(String date);

	public List<Holiday> findAllNationalHolidays();

	public List<Holiday> findAllByCep(String cep);

	public List<Holiday> findAllbyState(String stateName);
}
