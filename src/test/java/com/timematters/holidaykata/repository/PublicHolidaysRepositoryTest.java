package com.timematters.holidaykata.repository;

import com.timematters.holidaykata.dto.PublicHolidayType;
import com.timematters.holidaykata.model.PublicHoliday;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class PublicHolidaysRepositoryTest {

    @Autowired
    private PublicHolidaysRepository publicHolidaysRepository;

    private PublicHoliday holiday1;
    private PublicHoliday holiday2;

    @BeforeEach
    public void setUpDB() {
        holiday1 = new PublicHoliday();
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

        holiday2 = new PublicHoliday();
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
        holiday3.setDate(LocalDate.of(2020,12,25));
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

    @Test
    void testFindByYearAndCountryCodeReturnNonemptyResults() {
        List<PublicHoliday> holidayList = publicHolidaysRepository.findByYearAndCountryCode("2021", "DE");
        assertNotNull(holidayList);
        assertEquals(2, holidayList.size());
        assertEquals(holiday1, holidayList.get(0));
        assertEquals(holiday2, holidayList.get(1));
    }

    @Test
    void testFindByYearAndCountryCodeNoMatchingCountryCodeReturnEmptyResults() {
        List<PublicHoliday> holidayList = publicHolidaysRepository.findByYearAndCountryCode("2021", "USA");
        assertNotNull(holidayList);
        assertEquals(0, holidayList.size());
    }

    @Test
    void testFindByYearAndCountryCodeNoMatchingYearReturnEmptyResults() {
        List<PublicHoliday> holidayList = publicHolidaysRepository.findByYearAndCountryCode("2020", "DE");
        assertNotNull(holidayList);
        assertEquals(0, holidayList.size());
    }

    @Test
    void testFindByYearAndCountryCodeNullYearNullCountryCodeReturnEmptyResults() {
        List<PublicHoliday> holidayList = publicHolidaysRepository.findByYearAndCountryCode(null, null);
        assertNotNull(holidayList);
        assertEquals(0, holidayList.size());
    }

}