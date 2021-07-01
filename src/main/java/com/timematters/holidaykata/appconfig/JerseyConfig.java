package com.timematters.holidaykata.appconfig;

import com.timematters.holidaykata.restapi.DateDiffRestImpl;
import com.timematters.holidaykata.restapi.PublicHolidaysRestImpl;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/Api/v2")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(PublicHolidaysRestImpl.class);
        register(DateDiffRestImpl.class);
    }
}
