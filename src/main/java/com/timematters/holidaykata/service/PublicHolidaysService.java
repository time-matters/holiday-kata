package com.timematters.holidaykata.service;

import com.timematters.holidaykata.model.PublicHoliday;

import java.util.List;

public interface PublicHolidaysService {

    List<PublicHoliday> getPublicHolidays(String year, String countryCode);

}
