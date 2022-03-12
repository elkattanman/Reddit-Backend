package com.elkattanman.reddit.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.List;
import org.hibernate.annotations.DynamicUpdate;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "subreddit")
@DynamicUpdate
public class Subreddit implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotBlank(message = "Community name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @OneToMany(fetch = LAZY, mappedBy = "subreddit")
    private List<Post> posts;

    private Instant createdDate;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}