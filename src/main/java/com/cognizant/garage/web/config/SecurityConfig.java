package com.cognizant.garage.web.config;

import com.cognizant.garage.web.filter.MetricFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    final private MetricFilter metricFilter;

    public SecurityConfig(MetricFilter metricFilter) {
        this.metricFilter = metricFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers("/warehouses/cars").permitAll()
                .anyRequest().authenticated()
                .and().httpBasic()
                .and().addFilterBefore(metricFilter, UsernamePasswordAuthenticationFilter.class);


    }
}
