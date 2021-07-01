package com.timematters.holidaykata.restapi;

import com.timematters.holidaykata.api.DateDiffApi;
import org.apache.cxf.feature.Features;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.*;

@Features(features = { "org.apache.cxf.jaxrs.validation.JAXRSBeanValidationFeature",
        "org.apache.cxf.ext.logging.LoggingFeature" })
@Component
public class DateDiffRestImpl implements DateDiffApi {

    private static final Logger logger = LoggerFactory.getLogger(DateDiffRestImpl.class.getName());

    @Override
    public Integer calDateDiff(@NotNull @Pattern(regexp="^(\\d{4})-(\\d{2})-(\\d{2})(T(\\d{2}):(\\d{2}):(\\d{2}))?(([\\+-])((\\d{2}):(\\d{2})))?$") String date1, @NotNull @Pattern(regexp="^(\\d{4})-(\\d{2})-(\\d{2})(T(\\d{2}):(\\d{2}):(\\d{2}))?(([\\+-])((\\d{2}):(\\d{2})))?$") String date2) {

        logger.info("Received a request to calculate date time difference");

        OffsetDateTime offsetDateTime1 = convertDateStringToOffsetDateTime(date1);
        OffsetDateTime offsetDateTime2 = convertDateStringToOffsetDateTime(date2);

        Duration duration = Duration.between(offsetDateTime1, offsetDateTime2);
        int diffHours = new Long (duration.toHours()).intValue();
        return new Integer(diffHours);
    }

    private OffsetDateTime convertDateStringToOffsetDateTime (String date) {
        switch (date.length()) {
            case 10:
                date += "T00:00:00";
            case 19:
                return OffsetDateTime.of(LocalDateTime.parse(date), ZoneOffset.UTC);
            default:
                return OffsetDateTime.parse(date);
        }
    }
}
