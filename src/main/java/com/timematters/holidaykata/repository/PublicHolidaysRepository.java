package com.timematters.holidaykata.repository;

import com.timematters.holidaykata.model.PublicHoliday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicHolidaysRepository extends JpaRepository<PublicHoliday, Long> {

    List<PublicHoliday> findByLaunchYearAndCountryCode(Integer lunchYear, String countryCode);

}
