package com.elkattanman.reddit.repository;

import com.elkattanman.reddit.domain.Post;
import com.elkattanman.reddit.domain.User;
import com.elkattanman.reddit.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);
}