package br.com.feriados.domain.service.impl;

import br.com.feriados.domain.entity.City;
import br.com.feriados.domain.entity.Holiday;
import br.com.feriados.domain.entity.HolidayType;
import br.com.feriados.domain.entity.State;
import br.com.feriados.domain.entity.dto.HolidayDTO;
import br.com.feriados.domain.service.HolidayService;
import br.com.feriados.infrastructure.database.CityRepository;
import br.com.feriados.infrastructure.database.HolidayRepository;
import br.com.feriados.infrastructure.database.StateRepository;
import br.com.feriados.infrastructure.exception.InvalidCityException;
import br.com.feriados.infrastructure.exception.NotFoundCityException;
import br.com.feriados.infrastructure.exception.NotFoundHolidayException;
import br.com.feriados.infrastructure.exception.NotFoundStateException;
import br.com.feriados.infrastructure.viaCep.Address;
import br.com.feriados.infrastructure.viaCep.ViaCepClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class HolidayServiceImpl implements HolidayService {

	@Autowired
	private HolidayRepository holidayRepository;
	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private ViaCepClient viaCepClient;
	@Autowired
	private StateRepository stateRepository;


	@Override
	public Holiday findByCityAndDate(Long cityId, String date) {
		return holidayRepository.findByCityIdAndAndDate(cityId, date);
	}

	@Override
	public List<Holiday> findAllByType(Enum holidayType) {
		List<Holiday> holidays = holidayRepository.findAll().stream()
			.filter(holiday -> holiday.getType().equals(holidayType))
			.collect(Collectors.toList());

		return holidays;
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
		Optional<State> state = stateRepository.findById(city.getState().getId());
		List<Holiday> holidays = holidayRepository.findAllByCityId(city.getId());
		holidays.addAll(findAllByType(HolidayType.NATIONAL));
		holidays.addAll(findAllbyState(state.get().getName()));

		return holidays;
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

	@Override
	public List<Holiday> findAllbyState(String stateName) {
		if (Objects.isNull(stateName)) {
			throw new NotFoundStateException();
		}

		State state = stateRepository.findByName(stateName);
		if (Objects.isNull(state)) {
			throw new NotFoundStateException();
		}

		return holidayRepository.findAllByStateId(state.getId()).stream()
			.filter(holiday -> holiday.getType().equals(HolidayType.STATE))
			.collect(Collectors.toList());
	}

	@Override
	public List<Holiday> findAllByCep(String cep) {
		Address address = viaCepClient.getAddress(cep);
		List<Holiday> holidays = holidayRepository.findAllByCityName(address.getLocalidade());

		return holidays;
	}


}
