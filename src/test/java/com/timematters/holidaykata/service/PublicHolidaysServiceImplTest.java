package com.timematters.holidaykata.service;

import com.timematters.holidaykata.model.PublicHoliday;
import com.timematters.holidaykata.repository.PublicHolidaysRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PublicHolidaysServiceImplTest {

    @InjectMocks
    private PublicHolidaysServiceImpl testee;

    @Mock
    private PublicHolidaysRepository publicHolidaysRepository;

    @Test
    public void testGetPublicHolidaysInputValuesNull() {
        List<PublicHoliday> resultPublicHolidayList =
                testee.getPublicHolidays(null, null);

        assertNotNull(resultPublicHolidayList);
        assertTrue(CollectionUtils.isEmpty(resultPublicHolidayList));
    }

    @Test
    public void testGetPublicHolidaysAllInputValues() {
        List<PublicHoliday> publicHolidayList = new ArrayList<>();
        publicHolidayList.add(new PublicHoliday());
        publicHolidayList.add(new PublicHoliday());

        when(publicHolidaysRepository.findByYearAndCountryCode(anyString(), anyString()))
                .thenReturn(publicHolidayList);

        List<PublicHoliday> resultPublicHolidayList =
                testee.getPublicHolidays("2020", "DE");

        assertFalse(CollectionUtils.isEmpty(resultPublicHolidayList));
        assertSame(2, resultPublicHolidayList.size());
    }

}