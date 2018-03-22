package com.hainet.spring.security.sample.security.web.filter;

import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@Profile("!prod")
public class DefaultRequestHeaderFilter extends RequestHeaderFilter {

    public DefaultRequestHeaderFilter(final AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected Object getPreAuthenticatedPrincipal(final HttpServletRequest request) {
        return "noop-id";
    }

    @Override
    protected Object getPreAuthenticatedCredentials(final HttpServletRequest request) {
        return "noop-secret";
    }
}
