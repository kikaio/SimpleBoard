package com.portfolio.simpleboard.config;


import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
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
import org.springframework.security.jackson2.CoreJackson2Module;
import org.springframework.security.jackson2.SecurityJackson2Modules;
import org.springframework.security.oauth2.client.jackson2.OAuth2ClientJackson2Module;
import org.springframework.security.web.jackson2.WebJackson2Module;
import org.springframework.security.web.jackson2.WebServletJackson2Module;
import org.springframework.security.web.server.jackson2.WebServerJackson2Module;
import org.springframework.session.data.redis.config.annotation.SpringSessionRedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.SpringSessionRedisOperations;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession
@Log4j2
@RequiredArgsConstructor
public class RedisConfig {

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

        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());

        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

        template.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());

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
        return new GenericJackson2JsonRedisSerializer();
    }


    private RedisSerializer<Object> createDefaultSerializer() {
        return new GenericJackson2JsonRedisSerializer();
    }


    private ObjectMapper objectMapper() {
        var om = new ObjectMapper();
        om.registerModule(new CoreJackson2Module());
        om.registerModule(new WebJackson2Module());
        om.registerModule(new WebServletJackson2Module());
        om.registerModule(new WebServerJackson2Module());
        om.registerModule(new OAuth2ClientJackson2Module());
//        om.registerModule(new CasJackson2Module());
  //      om.registerModules(SecurityJackson2Modules.getModules(SecurityJackson2Modules.class.getClassLoader()));
        return om;
    }

    @Bean
    public RedisSerializer<Object> springSessionDefaultRedisSerializer() {
//        return new Jackson2JsonRedisSerializer<>(Object.class);
        return new GenericJackson2JsonRedisSerializer(objectMapper());
    }

}
