package com.elkattanman.reddit.repository;

import com.elkattanman.reddit.domain.Subreddit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface SubredditRepository extends JpaRepository<Subreddit, Long> {
    Optional<Subreddit> findByName(String subredditName);
}