package com.portfolio.simpleboard.repository.reply;


import com.portfolio.simpleboard.entity.Reply;
import com.portfolio.simpleboard.repository.reply.search.ReplySearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long>, ReplySearch {

}
