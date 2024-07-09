package com.portfolio.simpleboard.config;


import com.portfolio.simpleboard.enums.EMemberRedisDB;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
//@EnableRedisRepositories
@Log4j2
public class CacheConfig {

//    @Value("${simpleboard.redis.session.host}")
//    private String host;
//
//    @Value("${simpleboard.redis.session.port}")
//    private int port;
//
//    @Qualifier("redisConnectionFactoryForCache")
//    @Bean
//    public RedisConnectionFactory redisConnectionFactoryForCache() {
//        RedisStandaloneConfiguration conf = new RedisStandaloneConfiguration();
//        conf.setHostName(host);
//        conf.setPort(port);
//        conf.setDatabase(EMemberRedisDB.CACHE.getIdx());
//        log.info("redis connection pool created. desc : %s".formatted(EMemberRedisDB.CACHE.getDesc()));
//        return new LettuceConnectionFactory(conf);
//    }
//
//    @Qualifier("redisCacheTemplateForCache")
//    @Bean
//    public RedisTemplate<String, Object> redisCacheTemplateForCache() {
//        var template = new RedisTemplate<String, Object>();
//
//        template.setKeySerializer(new StringRedisSerializer());
//        template.setValueSerializer(new StringRedisSerializer());
//
//        template.setHashValueSerializer(new StringRedisSerializer());
//        template.setHashKeySerializer(new StringRedisSerializer());
//
//        template.setConnectionFactory(redisConnectionFactoryForCache());
//
//        return template;
//    }
}
