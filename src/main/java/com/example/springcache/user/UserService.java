package com.example.springcache.user;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RedisTemplate<String, User> redisTemplate;

    public UserService(UserRepository userRepository, RedisTemplate<String, User> redisTemplate) {
        this.userRepository = userRepository;
        this.redisTemplate = redisTemplate;
    }

    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    @PostConstruct
    /*
      애플리케이션이 실행 될 때
      유저 1000명을 저장

     */
    public void saveUser() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            users.add(new User("이름"));
        }
        List<User> saveAll = userRepository.saveAll(users);

        ListOperations<String, User> operations = redisTemplate.opsForList();
        redisTemplate.delete("users");
        operations.rightPushAll("users",saveAll);
    }

    public List<User> cacheUser() {
        return redisTemplate.opsForList().range("users", 0, -1);

    }
}
