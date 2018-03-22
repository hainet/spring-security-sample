package com.hainet.spring.security.sample.security.config;

import com.hainet.spring.security.sample.security.core.provider.LoginAuthenticationProvider;
import com.hainet.spring.security.sample.security.core.provider.RequestHeaderAuthenticationProvider;
import com.hainet.spring.security.sample.security.web.filter.RequestHeaderFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Configuration
    @Order(1)
    @RequiredArgsConstructor
    public static class ApiWebSecurityConfig extends WebSecurityConfigurerAdapter {

        private final RequestHeaderFilter filter;
        private final RequestHeaderAuthenticationProvider provider;

        @Override
        public void configure(final HttpSecurity http) throws Exception {
            http
                    .mvcMatcher("/api/**")
                        .authorizeRequests()
                            .anyRequest().authenticated()
                            .and()
                        .addFilter(filter)
                        .exceptionHandling()
                            .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
        }

        @Autowired
        public void configureGlobal(final AuthenticationManagerBuilder auth) {
            auth
                    .authenticationProvider(provider);
        }
    }

    @Configuration
    @Order(2)
    @RequiredArgsConstructor
    public static class LoginWebSecurityConfig extends WebSecurityConfigurerAdapter {

        private final LoginAuthenticationProvider provider;

        @Override
        public void configure(final HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                        .anyRequest().authenticated()
                        .and()
                    .formLogin()
                        .loginPage("/login").permitAll();
        }

        @Autowired
        public void configureGlobal(final AuthenticationManagerBuilder auth) {
            auth
                    .authenticationProvider(provider);
        }
    }
}
