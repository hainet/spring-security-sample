package com.hainet.spring.security.sample.security.web.filter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

abstract public class RequestHeaderFilter extends AbstractPreAuthenticatedProcessingFilter {

    public RequestHeaderFilter(final AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }
}
