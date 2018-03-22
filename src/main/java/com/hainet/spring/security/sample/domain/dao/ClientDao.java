package com.hainet.spring.security.sample.domain.dao;

import com.hainet.spring.security.sample.domain.entity.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ClientDao {

    private final JdbcTemplate jdbc;

    public Client findByClientId(final String clientId) {
        try {
            return jdbc.queryForObject(
                    "SELECT * FROM client WHERE client_id = ?",
                    new BeanPropertyRowMapper<>(Client.class),
                    clientId
            );
        } catch (final EmptyResultDataAccessException e) {
            return null;
        }
    }
}
