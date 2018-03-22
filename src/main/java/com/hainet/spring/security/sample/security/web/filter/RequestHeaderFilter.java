package com.hainet.spring.security.sample.security.web.filter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class RequestHeaderFilter extends AbstractPreAuthenticatedProcessingFilter {

    public RequestHeaderFilter(final AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    protected Object getPreAuthenticatedPrincipal(final HttpServletRequest request) {
        return request.getHeader("Client-Id");
    }

    @Override
    protected Object getPreAuthenticatedCredentials(final HttpServletRequest request) {
        return request.getHeader("Client-Secret");
    }
}
