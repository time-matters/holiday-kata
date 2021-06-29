package com.timematters.holidaykata.model;

import com.timematters.holidaykata.dto.PublicHolidayType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
public class PublicHoliday {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The date
     **/
    private LocalDate date;

    /**
     * Local name
     **/
    private String localName;

    /**
     * English name
     **/
    private String name;

    /**
     * ISO 3166-1 alpha-2
     **/
    private String countryCode;

    /**
     * Is this public holiday every year on the same date
     **/
    private Boolean fixed;

    /**
     * Is this public holiday in every county (federal state)
     **/
    private Boolean global;

    /**
     * ISO-3166-2 - Federal states - Used if the holiday applies only to specific federal states.
     **/
    private List<String> counties = null;

    /**
     * The launch year of the public holiday
     **/
    private Integer launchYear;
    private PublicHolidayType type;
}
