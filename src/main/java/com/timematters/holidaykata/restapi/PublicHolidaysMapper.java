package com.timematters.holidaykata.restapi;

import com.timematters.holidaykata.dto.PublicHolidayDto;
import com.timematters.holidaykata.model.PublicHoliday;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class PublicHolidaysMapper {

    public abstract PublicHolidayDto toDto(PublicHoliday transactionModel);

}
