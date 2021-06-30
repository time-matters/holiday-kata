package com.timematters.holidaykata.restapi;

import com.timematters.holidaykata.dto.PublicHolidayDto;
import com.timematters.holidaykata.model.PublicHoliday;
import com.timematters.holidaykata.service.PublicHolidaysService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.CollectionUtils;

import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PublicHolidaysRestImplTest {

    @InjectMocks
    private PublicHolidaysRestImpl testee;

    @Mock
    private PublicHolidaysService holidaysService;

    @Mock
    private PublicHolidaysMapper holidaysMapper;

    @Test
    public void testGetPublicHolidaysInputValuesNull() {
        assertThrows(NotFoundException.class, () -> {
            List<PublicHolidayDto> resultPublicHolidayDto =
                    testee.getPublicHolidays(null, null);
            assertNull(resultPublicHolidayDto);
        });
        verify(holidaysMapper, times(0)).toDto(any());
    }

    @Test
    public void testGetPublicHolidaysAllInputValues() {

        List<PublicHoliday> publicHolidayList = new ArrayList<>();
        publicHolidayList.add(new PublicHoliday());
        publicHolidayList.add(new PublicHoliday());
        when(holidaysService.getPublicHolidays(anyString(), anyString()))
                .thenReturn(publicHolidayList);

        PublicHolidayDto publicHolidayDto = new PublicHolidayDto();
        when(holidaysMapper.toDto(any(PublicHoliday.class))).thenReturn(publicHolidayDto);

        List<PublicHolidayDto> resultPublicHolidayDtoList =
                testee.getPublicHolidays(2020, "DE");

        assertFalse(CollectionUtils.isEmpty(resultPublicHolidayDtoList));
        assertSame(2, resultPublicHolidayDtoList.size());
    }
}