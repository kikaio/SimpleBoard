package com.portfolio.simpleboard.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.portfolio.simpleboard.enums.EMemberRedisDB;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;
import org.springframework.session.data.redis.RedisSessionMapper;
import org.springframework.session.data.redis.RedisSessionRepository;
import org.springframework.session.data.redis.config.annotation.SpringSessionRedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.SpringSessionRedisOperations;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession
@Log4j2
@RequiredArgsConstructor
public class RedisConfig {

    private final ObjectMapper om;

    @Value("${simpleboard.redis.session.host}")
    private String host;

    @Value("${simpleboard.redis.session.port}")
    private int port;

    @Primary
    @Bean
    public RedisConnectionFactory redisConnectionFactoryForDefault() {
        RedisStandaloneConfiguration conf = new RedisStandaloneConfiguration();
        conf.setHostName(host);
        conf.setPort(port);
        conf.setDatabase(EMemberRedisDB.CACHE.getIdx());
        log.info("redis connection pool created. desc : %s".formatted(EMemberRedisDB.CACHE.getDesc()));
        return new LettuceConnectionFactory(conf);
    }


    @SpringSessionRedisConnectionFactory
    @Bean
    public RedisConnectionFactory redisConnectionFactoryForSession() {
        RedisStandaloneConfiguration conf = new RedisStandaloneConfiguration();
        conf.setHostName(host);
        conf.setPort(port);
        conf.setDatabase(EMemberRedisDB.SESSION.getIdx());
        log.info("redis connection pool created. desc : %s".formatted(EMemberRedisDB.SESSION.getDesc()));
        return new LettuceConnectionFactory(conf);
    }

    @Qualifier("redisConnectionFactoryForCommon")
    @Bean
    public RedisConnectionFactory redisConnectionFactoryForCommon() {
        RedisStandaloneConfiguration conf = new RedisStandaloneConfiguration();
        conf.setHostName(host);
        conf.setPort(port);
        conf.setDatabase(EMemberRedisDB.COMMON.getIdx());
        log.info("redis connection pool created. desc : %s".formatted(EMemberRedisDB.COMMON.getDesc()));
        return new LettuceConnectionFactory(conf);
    }

    @Primary
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        var template = new RedisTemplate<String, Object>();

        template.setKeySerializer(createKeySerializer());
        template.setValueSerializer(createKeySerializer());

        template.setValueSerializer(createValueSerializer());
        template.setHashValueSerializer(createValueSerializer());

        template.setDefaultSerializer(createDefaultSerializer());

        template.setConnectionFactory(redisConnectionFactoryForDefault());

        return template;
    }

    @SpringSessionRedisOperations
    @Qualifier("sessionRedisOperations")
    @Bean(name="sessionRedisOperations")
    public RedisTemplate<String, Object> sessionRedisOperations() {
        var template = new RedisTemplate<String, Object>();

        template.setKeySerializer(createKeySerializer());
        template.setValueSerializer(createKeySerializer());

        template.setValueSerializer(createValueSerializer());
        template.setHashValueSerializer(createValueSerializer());

        template.setDefaultSerializer(createDefaultSerializer());

        template.setConnectionFactory(redisConnectionFactoryForSession());

        return template;
    }

    @Qualifier("redisCacheTemplateForCommon")
    @Bean
    public RedisTemplate<String, Object> redisCacheTemplateForCommon() {
        var template = new RedisTemplate<String, Object>();

        template.setKeySerializer(createKeySerializer());
        template.setHashKeySerializer(createKeySerializer());

        template.setValueSerializer(createValueSerializer());
        template.setHashValueSerializer(createValueSerializer());

        template.setDefaultSerializer(createDefaultSerializer());

        template.setConnectionFactory(redisConnectionFactoryForCommon());

        return template;
    }

    private RedisSerializer<String> createKeySerializer() {
        return new StringRedisSerializer();
    }

    private RedisSerializer<Object> createValueSerializer() {
        return new Jackson2JsonRedisSerializer<>(Object.class);
//        return new GenericJackson2JsonRedisSerializer();
//        return new StringRedisSerializer();
    }


    private RedisSerializer<Object> createDefaultSerializer() {
        return new Jackson2JsonRedisSerializer<>(Object.class);
//        return new GenericJackson2JsonRedisSerializer();
    }

    @Bean
    public RedisSerializer<Object> springSessionDefaultRedisSerializer() {
        return new Jackson2JsonRedisSerializer<>(Object.class);
//        return new GenericJackson2JsonRedisSerializer();
    }

    RedisSessionMapper

    @Bean
    @Primary
    public RedisSessionRepository redisSessionRepository() {
        RedisSessionRepository repo = new RedisSessionRepository(sessionRedisOperations());
        repo.setRedisSessionMapper();
        return repo;
    }
}
