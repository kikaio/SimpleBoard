package com.portfolio.simpleboard.repository.redis;

import com.portfolio.simpleboard.redisEntity.RedisMemberProfile;
import org.springframework.data.repository.CrudRepository;

public interface RedisMemberSessionRepository extends CrudRepository<RedisMemberProfile, String> {

}
