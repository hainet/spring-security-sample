package com.hainet.spring.security.sample.security.core.provider;

import com.hainet.spring.security.sample.domain.dao.PersonDao;
import com.hainet.spring.security.sample.domain.entity.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginAuthenticationProvider implements AuthenticationProvider {

    private final PasswordEncoder passwordEncoder;
    private final PersonDao dao;

    @Override
    public Authentication authenticate(
            final Authentication authentication) throws AuthenticationException {
        final Person person = dao.findByUsername(authentication.getPrincipal().toString());

        if (person == null) {
            throw new UsernameNotFoundException("Username not found!");
        }

        if (!passwordEncoder
                .matches(authentication.getCredentials().toString(), person.getPassword())) {
            throw new BadCredentialsException("Invalid password!");
        }

        return new UsernamePasswordAuthenticationToken(
                person.getUsername(),
                person.getPassword(),
                AuthorityUtils.NO_AUTHORITIES
        );
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
