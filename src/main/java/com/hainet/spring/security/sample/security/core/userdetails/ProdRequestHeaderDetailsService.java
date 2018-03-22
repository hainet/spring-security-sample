package com.hainet.spring.security.sample.security.core.userdetails;

import com.hainet.spring.security.sample.domain.dao.ClientDao;
import com.hainet.spring.security.sample.domain.entity.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
@RequiredArgsConstructor
public class ProdRequestHeaderDetailsService extends RequestHeaderDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final ClientDao dao;

    @Override
    public UserDetails loadUserDetails(
            final PreAuthenticatedAuthenticationToken token) throws AuthenticationException {
        final Client client = dao.findByClientId(token.getPrincipal().toString());

        if (client == null) {
            throw new UsernameNotFoundException("Client id not found!");
        }

        if (!passwordEncoder
                .matches(token.getCredentials().toString(), client.getClientSecret())) {
            throw new BadCredentialsException("Incalid client secret!");
        }

        return new User(
                client.getClientId(),
                client.getClientSecret(),
                AuthorityUtils.NO_AUTHORITIES
        );
    }
}
