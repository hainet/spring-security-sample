package com.hainet.spring.security.sample.domain.entity;

import lombok.Data;

@Data
public class Client {

    private int id;

    private String clientId;

    private String clientSecret;
}
