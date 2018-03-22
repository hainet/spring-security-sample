package com.hainet.spring.security.sample.domain.dao;

import com.hainet.spring.security.sample.domain.entity.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PersonDao {

    private final JdbcTemplate jdbc;

    public Person findByUsername(final String username) {
        try {
            return jdbc.queryForObject(
                    "SELECT * FROM person WHERE username = ?",
                    new BeanPropertyRowMapper<>(Person.class),
                    username
            );
        } catch (final EmptyResultDataAccessException e) {
            return null;
        }
    }
}
