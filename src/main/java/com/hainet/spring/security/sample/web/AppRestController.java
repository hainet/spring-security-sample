package com.hainet.spring.security.sample.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class AppRestController {

    @GetMapping("/api/credentials")
    public Principal credentials(final Principal principal) {
        return principal;
    }
}
