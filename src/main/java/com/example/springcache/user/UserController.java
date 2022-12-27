package com.example.springcache.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> findAllUser() {
        return userService.findAllUser();
    }

    @GetMapping("/cache")
    public List<User> cacheUser() {
        return userService.cacheUser();
    }

    @GetMapping("/cacheOne")
    public User cacheOne() {
        return userService.cacheOne();
    }

    @GetMapping("/userOne")
    public User findUser() {
        return userService.findOne();
    }

}
