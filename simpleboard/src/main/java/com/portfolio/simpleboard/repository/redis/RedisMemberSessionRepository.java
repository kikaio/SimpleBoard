package com.portfolio.simpleboard.repository.redis;

import com.portfolio.simpleboard.session.MemberSession;
import org.springframework.data.repository.CrudRepository;

public interface RedisMemberSessionRepository extends CrudRepository<MemberSession, String> {

}
