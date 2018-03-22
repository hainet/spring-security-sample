package com.hainet.spring.security.sample.security.core.userdetails;

import org.springframework.context.annotation.Profile;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
@Profile("!prod")
public class DefaultRequestHeaderDetailsService extends RequestHeaderDetailsService {

    @Override
    public UserDetails loadUserDetails(
            final PreAuthenticatedAuthenticationToken token) throws AuthenticationException {
        return new User(
                token.getPrincipal().toString(),
                token.getCredentials().toString(),
                AuthorityUtils.NO_AUTHORITIES
        );
    }
}
