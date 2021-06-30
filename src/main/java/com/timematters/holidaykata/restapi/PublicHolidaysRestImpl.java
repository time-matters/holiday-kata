package com.timematters.holidaykata.restapi;

import com.timematters.holidaykata.api.PublicHolidaysApi;
import com.timematters.holidaykata.dto.PublicHolidayDto;
import com.timematters.holidaykata.model.PublicHoliday;
import com.timematters.holidaykata.service.PublicHolidaysService;
import org.apache.cxf.feature.Features;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Features(features = { "org.apache.cxf.jaxrs.validation.JAXRSBeanValidationFeature",
        "org.apache.cxf.ext.logging.LoggingFeature" })
@Component
public class PublicHolidaysRestImpl implements PublicHolidaysApi {

    private static final Logger logger = LoggerFactory.getLogger(PublicHolidaysRestImpl.class.getName());

    @Autowired
    private PublicHolidaysService publicHolidaysService;
    @Autowired
    private PublicHolidaysMapper publicHolidaysMapper;

    @Override
    public List<PublicHolidayDto> getPublicHolidays(Integer year, String countrycode) {

        String yearStr = (year == null) ? null : year.toString();
        logger.info("Received a request to retrieve public holiday List");
        List<PublicHoliday> runList = publicHolidaysService.getPublicHolidays(yearStr, countrycode);
        if (runList.isEmpty()) {
            logger.info("No holidays found in the country with the country code {} : in year : {}", countrycode, year);
            throw new NotFoundException("No holidays found");
        }
        return runList.stream().map(publicHolidaysMapper::toDto).collect(Collectors.toList());
    }
}
