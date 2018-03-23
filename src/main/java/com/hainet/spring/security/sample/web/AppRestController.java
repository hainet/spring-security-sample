package com.hainet.spring.security.sample.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class AppRestController {

    // curl localhost:8080/api/credentials -H "Client-Id: id" -H "Cliend-Secret: secret" -v
    // default -> ヘッダの有無や値に関わらず200を返却。
    // prod -> ヘッダが無いか値が求められる値と異なる場合401を返却。
    @GetMapping("/api/credentials")
    public Principal credentials(final Principal principal) {
        return principal;
    }
}
