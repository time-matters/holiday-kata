package com.timematters.holidaykata.repository;

import com.timematters.holidaykata.model.PublicHoliday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicHolidaysRepository extends JpaRepository<PublicHoliday, Long> {

    @Query("select holiday from PublicHoliday holiday where substring(holiday.date, 1, 4) = :year and holiday.countryCode= :countryCode")
    List<PublicHoliday> findByYearAndCountryCode(@Param("year") String year, @Param("countryCode") String countryCode);

}
