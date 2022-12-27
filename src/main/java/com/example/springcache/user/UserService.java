package com.example.springcache.user;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    @PostConstruct
    /**
     * 애플리케이션이 실행 될 때
     * 유저 1000명을 저장
     *
     */
    public void saveUser() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            users.add(new User("이름"));
        }
        userRepository.saveAll(users);
    }
}
