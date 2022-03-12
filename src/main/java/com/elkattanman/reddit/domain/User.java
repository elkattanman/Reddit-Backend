package com.elkattanman.reddit.domain;


import java.io.Serializable;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@Builder
@Entity
@Table(name = "user")
@DynamicUpdate
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "name")
//    @NotBlank(message = "name is required")
    private String name;

    @Lob
    @Column(name = "img")
    private byte[] img;

    @NotBlank(message = "Username is required")
    @Column(name = "username", unique = true)
    private String username;

    @NotBlank(message = "email is required")
    @Column(name = "email", unique = true)
    private String email;

    @NotBlank(message = "email is required")
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "enabled")
    boolean enabled;

    @CreationTimestamp
    @Column(name = "create_time")
    private Date createTime;

    @UpdateTimestamp
    @Column(name = "update_time")
    private Date updateTime;

    @ManyToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JoinTable(name = "users_has_roles",
        joinColumns = @JoinColumn(name="user_id"),
        inverseJoinColumns = @JoinColumn(name="role_id"))
    private Set<Role> roles;



}
