package com.timematters.holidaykata.appconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Value("${holidaykata.cors.origin:null}")
    private String corsOrigin;

    @Value("${holidaykata.cors.enabled:false}")
    private String corsEnabled;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if (Boolean.valueOf(corsEnabled)) {
            http.cors().and().authorizeRequests().anyRequest().permitAll();
        }
    }

    @Bean
    @ConditionalOnProperty(value = "holidaykata.cors.enabled", havingValue = "true", matchIfMissing = false)
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin(corsOrigin);
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        config.addExposedHeader("Authorization");
        config.addExposedHeader("Content-Disposition");
        return new CorsFilter(source);
    }
}
