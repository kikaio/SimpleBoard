package com.portfolio.simpleboard.redisEntity;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.redis.core.RedisHash;


@Getter
@Setter
@Builder
@ToString
@RedisHash(value = "session", timeToLive = 3600)
public class RedisMemberSession {



}
