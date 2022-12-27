package com.example.springcache;


import com.example.springcache.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.List;

@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate<String, User> redisTemplate;

    @Test
    void temp() {
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        ListOperations<String, User> listOperations = redisTemplate.opsForList();
        List < User > users = List.of(new User("이름1"), new User("이름3"), new User("이름2"));

        for (User user : users) {
            listOperations.rightPush("users", user);
        }
        List<User> users1 = listOperations.range("users", 0, -1);
        users1.forEach(System.out::println);
        listOperations.


    }
}
