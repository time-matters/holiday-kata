package com.timematters.holidaykata.service;

import com.timematters.holidaykata.model.PublicHoliday;
import com.timematters.holidaykata.repository.PublicHolidaysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicHolidaysServiceImpl implements PublicHolidaysService {

    @Autowired
    private PublicHolidaysRepository publicHolidaysRepository;

    @Override
    public List<PublicHoliday> getPublicHolidays(String year, String countryCode) {

        return publicHolidaysRepository.findByYearAndCountryCode(year, countryCode);
    }
}
