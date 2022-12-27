package com.example.springcache.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final RedisTemplate<String, User> redisTemplate;

    public UserService(UserRepository userRepository, RedisTemplate<String, User> redisTemplate) {
        this.userRepository = userRepository;
        this.redisTemplate = redisTemplate;
    }
    @PostConstruct
    public void saveUser() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            users.add(new User("이름"));
        }
        List<User> saveAll = userRepository.saveAll(users);
    }

    @Cacheable(cacheNames = "user")
    public User cache() {
        log.info("getting from database");

        return userRepository.findById(1L).orElseThrow();
    }

    public User noCache() {
        log.info("getting from database no cache");

        return userRepository.findById(1L).orElseThrow();
    }
}
