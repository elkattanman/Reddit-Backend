package com.elkattanman.reddit.repository;

import com.elkattanman.reddit.domain.Comment;
import com.elkattanman.reddit.domain.Post;
import com.elkattanman.reddit.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);

    List<Comment> findAllByUser(User user);
}