package br.com.feriados.infrastructure.resource;

import br.com.feriados.domain.entity.City;
import br.com.feriados.domain.entity.Holiday;
import br.com.feriados.domain.service.HolidayService;
import br.com.feriados.infrastructure.database.CityRepository;
import br.com.feriados.infrastructure.database.HolidayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/holiday")
public class HolidayResource {
	@Autowired
	private HolidayService holidayService;

	@Autowired
	private HolidayRepository holidayRepository;

	@RequestMapping("/find-all")
	public List<Holiday> findAll() {
		return holidayRepository.findAll();
	}

	@GetMapping("/find-all-by-city")
	public ResponseEntity <List<Holiday>> findAllByCity(@RequestParam("city") String cityName) {
		try {
			List<Holiday> holidays = holidayService.findAllByCityName(cityName);
			return ResponseEntity.ok(holidays);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@GetMapping("/find-all-by-date")
	public ResponseEntity <List<Holiday>> findAllByDate(@RequestParam("date") String date) {
		List<Holiday> holidays = holidayService.findAllByDate(date);
		return ResponseEntity.ok(holidays);
	}

	@GetMapping("/find-national-holidays")
	public ResponseEntity<List<Holiday>> findNationalHolidays() {
		List<Holiday> holidays = holidayService.findAllNationalHolidays();
		return ResponseEntity.ok(holidays);
	}

	@GetMapping("/cep")
	public ResponseEntity<Object> findCep(@RequestParam("cep") String cep) {


		return ResponseEntity.ok().build();
	}

//	@GetMapping("/find-all-state-holidays")
//	public ResponseEntity<List<Holiday>> findAllStateHolidays (@RequestParam("state") String state) {
//		List<Holiday> holidays = holidayService.findAllStateHolidays(state);
//		return ResponseEntity.ok(holidays);
//
//	}
}
