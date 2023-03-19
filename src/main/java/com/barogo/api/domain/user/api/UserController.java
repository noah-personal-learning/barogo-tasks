package com.barogo.api.domain.user.api;

import com.barogo.api.domain.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {

    UserService userService;

    @GetMapping("/v1/hello")
    public String hello() {
        return "Hello";
    }
}
