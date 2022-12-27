package com.example.springcache.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/cache")
    public User cache() {
        return userService.cache();
    }


    @GetMapping("/no-cache")
    public User noCache() {
        return userService.noCache();
    }
}
