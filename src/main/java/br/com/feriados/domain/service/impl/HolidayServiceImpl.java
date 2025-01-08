package br.com.feriados.domain.service.impl;

import br.com.feriados.domain.entity.City;
import br.com.feriados.domain.entity.Holiday;
import br.com.feriados.domain.entity.HolidayType;
import br.com.feriados.domain.service.HolidayService;
import br.com.feriados.infrastructure.database.CityRepository;
import br.com.feriados.infrastructure.database.HolidayRepository;
import br.com.feriados.infrastructure.exception.InvalidCityException;
import br.com.feriados.infrastructure.exception.NotFoundCityException;
import br.com.feriados.infrastructure.exception.NotFoundHolidayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
public class HolidayServiceImpl implements HolidayService {

	@Autowired
	private HolidayRepository holidayRepository;
	@Autowired
	private CityRepository cityRepository;


	@Override
	public Holiday findByCityAndDate(Long cityId, String date) {
		return holidayRepository.findByCityIdAndAndDate(cityId, date);
	}

	@Override
	public List<Holiday> findAllByType(Enum holidayType) {
		return holidayRepository.findAll().stream()
			.filter(holiday -> holiday.getType().equals(holidayType))
			.collect(Collectors.toList());
	}

	@Override
	public List<Holiday> findAllByCityName(String cityName) {
		if (Objects.isNull(cityName)) {
			throw new InvalidCityException();
		}

		City city = cityRepository.findByName(cityName);
		if (Objects.isNull(city)) {
			throw new NotFoundCityException();
		}

		List<Holiday> holidays = holidayRepository.findAllByCityId(city.getId());
		holidays.addAll(findAllByType(HolidayType.NATIONAL));

		return holidays;
	}

	@Override
	public List<Holiday> holidayFilter(String Type) {
		if (Type.equals("NATIONAL")) {
			return holidayRepository.findAllByType(Type);
		}
		else if (Type.equals("REGIONAL")) {
			return holidayRepository.findAllByType(Type);
		}
		else if (Type.equals("LOCAL")) {
			return holidayRepository.findAllByType(Type);
		}
		else {
			throw new InvalidCityException();
		}
	}

	@Override
	public List<Holiday> findAllByDate(String date) {
		if (date != null) {
			List<Holiday> holidays = holidayRepository.findAllByDate(date);
			return holidays;
		}
		throw new NotFoundHolidayException();
	}

	@Override
	public List<Holiday> findAllNationalHolidays() {
		return findAllByType(HolidayType.NATIONAL);
	}

//	@Override
//	public List<Holiday> findAllStateHolidays(String state) {
//		if (state != null) {
//			State currentState = stateRepository.findByName(state);
//
//			if (currentState != null) {
//
//			}
//		}
//
//	}
}
