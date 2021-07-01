package com.timematters.holidaykata.model;

import com.timematters.holidaykata.dto.PublicHolidayType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
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
    @ElementCollection(fetch=FetchType.EAGER)
    @Column(name ="counties")
    private List<String> counties = null;

    /**
     * The launch year of the public holiday
     **/
    private Integer launchYear;
    private PublicHolidayType type;
}
