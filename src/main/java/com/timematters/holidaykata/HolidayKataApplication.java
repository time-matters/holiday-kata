package com.timematters.holidaykata;

import com.timematters.holidaykata.dto.PublicHolidayType;
import com.timematters.holidaykata.model.PublicHoliday;
import com.timematters.holidaykata.repository.PublicHolidaysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;

@SpringBootApplication
public class HolidayKataApplication {

	public static void main(String[] args) {
		SpringApplication.run(HolidayKataApplication.class, args);
	}
}

/**
 * This component is used to inserting some sample data into H2 DB for a testing purpose.
 */
@Component
class DemoCommandLineRunner implements CommandLineRunner {

	@Autowired
	private PublicHolidaysRepository publicHolidaysRepository;

	@Override
	public void run(String... args) {

		PublicHoliday holiday1 = new PublicHoliday();
		holiday1.setDate(LocalDate.of(2021,3,8));
		holiday1.setLocalName("Internationaler Frauentag");
		holiday1.setName("International Women's Day");
		holiday1.setCountryCode("DE");
		holiday1.setFixed(true);
		holiday1.setGlobal(false);
		holiday1.setCounties(Arrays.asList("DE-BE"));
		holiday1.setLaunchYear(2019);
		holiday1.setType(PublicHolidayType.PUBLIC);

		publicHolidaysRepository.save(holiday1);

		PublicHoliday holiday2 = new PublicHoliday();
		holiday2.setDate(LocalDate.of(2021,6,3));
		holiday2.setLocalName("Fronleichnam");
		holiday2.setName("orpus Christi");
		holiday2.setCountryCode("DE");
		holiday2.setFixed(false);
		holiday2.setGlobal(false);
		holiday2.setCounties(Arrays.asList("DE-BW", "DE-BY", "DE-HE", "DE-NW", "DE-RP", "DE-SL"));
		holiday2.setLaunchYear(2008);
		holiday2.setType(PublicHolidayType.PUBLIC);

		publicHolidaysRepository.save(holiday2);

		PublicHoliday holiday3 = new PublicHoliday();
		holiday3.setDate(LocalDate.of(2021,12,25));
		holiday3.setLocalName("Christmas");
		holiday3.setName("Christmas");
		holiday3.setCountryCode("USA");
		holiday3.setFixed(true);
		holiday3.setGlobal(false);
		holiday3.setCounties(Arrays.asList("USA-AA", "USA-BB"));
		holiday3.setLaunchYear(2008);
		holiday3.setType(PublicHolidayType.PUBLIC);

		publicHolidaysRepository.save(holiday3);
	}
}
