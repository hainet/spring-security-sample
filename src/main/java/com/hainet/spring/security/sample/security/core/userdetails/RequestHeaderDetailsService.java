package com.hainet.spring.security.sample.security.core.userdetails;

import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

abstract public class RequestHeaderDetailsService implements
        AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {
}
