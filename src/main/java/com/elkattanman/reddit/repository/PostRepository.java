package com.elkattanman.reddit.repository;

import com.elkattanman.reddit.domain.Post;
import com.elkattanman.reddit.domain.Subreddit;
import com.elkattanman.reddit.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllBySubreddit(Subreddit subreddit);

    List<Post> findByUser(User user);
}