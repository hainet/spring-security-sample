package com.hainet.spring.security.sample.security.web.filter;

import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@Profile("prod")
public class ProdRequestHeaderFilter extends RequestHeaderFilter {

    public ProdRequestHeaderFilter(final AuthenticationManager authenticationManager) {
        super(authenticationManager);
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
