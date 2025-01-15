package br.com.feriados.infrastructure.resource;

import br.com.feriados.domain.entity.Holiday;
import br.com.feriados.domain.entity.HolidayType;
import br.com.feriados.domain.entity.dto.HolidayDTO;
import br.com.feriados.domain.service.HolidayService;
import br.com.feriados.infrastructure.database.HolidayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/holiday")
public class HolidayResource {
	@Autowired
	private HolidayService holidayService;

	@Autowired
	private HolidayRepository holidayRepository;

	@RequestMapping("/holidays")
	public ResponseEntity<List<HolidayDTO>> findHoliday (
		@RequestParam(required = false) String city,
	  @RequestParam(required = false) String date,
		@RequestParam(required = false) String cep,
		@RequestParam(required = false) String state
	) {
		if(city != null) {
			try {
				return ResponseEntity.ok(findAllByCity(city));

			} catch (RuntimeException e) {
				ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		}
		else if (date != null) {
			try {
				return ResponseEntity.ok(findAllByDate(date));

			} catch (RuntimeException e) {
				ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		}
		else if (cep != null) {
			try {
				return ResponseEntity.ok(findCep(cep));
			} catch (RuntimeException e) {
				ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		}
		else if (state != null) {
			try {
				return ResponseEntity.ok(findAllStateHolidays(state));

			} catch (RuntimeException e) {
				ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}



//
//	@RequestMapping("/find-all")
//	public List<Holiday> findAll() {
//		return holidayRepository.findAll();
//	}
//
	public List<HolidayDTO>findAllByCity (String cityName) {
		List<Holiday> holidays = holidayService.findAllByCityName(cityName);
		List<HolidayDTO> holidaysDTOS = convertHolidays(holidays);
		return holidaysDTOS;
	}
//
	public List<HolidayDTO> findAllByDate(String date) {
		List<Holiday> holidays = holidayService.findAllByDate(date);
		List<HolidayDTO> holidaysDTOS = convertHolidays(holidays);
		return holidaysDTOS;
	}
//
//	@GetMapping("/find-national-holidays")
//	public ResponseEntity<List<Holiday>> findNationalHolidays() {
//		List<Holiday> holidays = holidayService.findAllNationalHolidays();
//		return ResponseEntity.ok(holidays);
//	}
//
	public List<HolidayDTO> findCep(String cep) {
			List<Holiday> holidays = holidayService.findAllByCep(cep);

			List<HolidayDTO> holidaysDTOS = convertHolidays(holidays);
			return (holidaysDTOS);
	}

	public List<HolidayDTO> findAllStateHolidays (String state) {
			List<Holiday> holidays = holidayService.findAllbyState(state);
			List<HolidayDTO> holidaysDTOS = convertHolidays(holidays);
			return holidaysDTOS;
	}

	public List<HolidayDTO> convertHolidays(List<Holiday> holidays) {
		List<HolidayDTO> holidaysDTOS = new ArrayList<>();
		for (Holiday holiday : holidays) {
			HolidayDTO holidayDTO = new HolidayDTO();
			holidayDTO.setDate(holiday.getDate());
			holidayDTO.setName(holiday.getName());
			holidayDTO.setType(holiday.getType().name());
			if (holiday.getType().equals(HolidayType.MUNICIPAL)) {
				holidayDTO.setCity(holiday.getCity().getName());
			}

			if (holiday.getType() != HolidayType.NATIONAL) {
				holidayDTO.setState(holiday.getState().getName());
			}
			holidaysDTOS.add(holidayDTO);
		}
		return holidaysDTOS;
	}
}
