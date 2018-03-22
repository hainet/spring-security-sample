package com.hainet.spring.security.sample.security.core.provider;

import com.hainet.spring.security.sample.security.core.userdetails.RequestHeaderDetailsService;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.stereotype.Component;

@Component
public class RequestHeaderAuthenticationProvider extends PreAuthenticatedAuthenticationProvider {

    public RequestHeaderAuthenticationProvider(final RequestHeaderDetailsService service) {
        super.setPreAuthenticatedUserDetailsService(service);
        super.setUserDetailsChecker(new AccountStatusUserDetailsChecker());
    }
}
