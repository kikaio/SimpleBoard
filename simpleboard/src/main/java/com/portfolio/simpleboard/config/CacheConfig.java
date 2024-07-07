package com.portfolio.simpleboard.config;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
@EnableCaching
@RequiredArgsConstructor
public class CacheConfig {

    private final CacheProperties cacheProperties;
//
//    @Value("simpleboard.cache.redis.host")
//    private String redisHost;
//
//    @Value("simpleboard.cache.redis.port")
//    private int redisPort;
//
//    @Bean(name="redisCacheConnectionFactory")
//    public RedisConnectionFactory redisCacheConnectionFactory() {
//        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(
//                redisHost, redisPort
//        );
//        return lettuceConnectionFactory;
//    }

}
