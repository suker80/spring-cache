package com.example.springcache.redis;

import com.example.springcache.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    private final String host;
    private final int port;

    public RedisConfig(final @Value("${spring.redis.host}") String host , final @Value("{spring.redis.port}") int port) {
        this.host = host;
        this.port = port;
    }


    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(host, port);  // Lettuce 사용
    }

    @Bean
    public RedisTemplate<String, User> redisTemplate() {
        RedisTemplate<String, User> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());   // Key: String
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(User.class));  // Value: 직렬화에 사용할 Object 사용하기
        return redisTemplate;
    }

}