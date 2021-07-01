package com.timematters.holidaykata.restapi;

import org.junit.jupiter.api.Test;
import java.time.format.DateTimeParseException;
import static org.junit.jupiter.api.Assertions.*;

class DateDiffRestImplTest {

    private final DateDiffRestImpl testee = new DateDiffRestImpl();

    @Test
    public void testGetDateTimeDiffValidInputValues() {

        String date1 = "2001-10-26T21:32:52+02:00";
        String date2 = "2001-10-26T23:32:52+02:00";
        int hourDiff = testee.calDateDiff(date1, date2);
        assertEquals(2, hourDiff);
    }

    @Test
    public void testGetDateTimeDiffValidInputValuesDifferentForms1() {

        String date1 = "2001-10-26T01:32:52";
        String date2 = "2001-10-26";
        int hourDiff = testee.calDateDiff(date1, date2);
        assertEquals(-1, hourDiff);
    }

    @Test
    public void testGetDateTimeDiffValidInputValuesDifferentForms2() {

        String date1 = "2001-10-26T21:32:52";
        String date2 = "2001-10-26T21:32:52+02:00";
        int hourDiff = testee.calDateDiff(date1, date2);
        assertEquals(-2, hourDiff);
    }

    @Test
    public void testGetDateTimeDiffNullInputValues() {

        assertThrows(NullPointerException.class, () -> {
            Integer hourDiff = testee.calDateDiff(null, null);
            assertNull(hourDiff);
        });
    }

    @Test
    public void testGetDateTimeDiffInputValuesNotMatchRegexPattern() {

        String date1 = "abc";
        String date2 = "2001-10-26T23:32:52+02:00";
        assertThrows(DateTimeParseException.class, () -> {
            Integer hourDiff = testee.calDateDiff(date1, date2);
            assertNull(hourDiff);
        });
    }
}