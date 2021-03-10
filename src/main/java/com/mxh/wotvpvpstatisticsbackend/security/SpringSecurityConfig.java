package com.mxh.wotvpvpstatisticsbackend.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/v3/api-docs/swagger-config#/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                .and().cors().disable();

        http.csrf().ignoringAntMatchers("/users/**");
    }
}
