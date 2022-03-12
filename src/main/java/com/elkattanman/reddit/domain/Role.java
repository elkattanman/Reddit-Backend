package com.elkattanman.reddit.domain;


import com.elkattanman.reddit.domain.enums.ERole;
import java.io.Serializable;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Builder
@Entity
@Table(name = "Roles")
@DynamicUpdate
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @Enumerated(value = EnumType.STRING)
    private ERole name;

    @Column(name = "description")
    private String description;

    @CreationTimestamp
    @Column(name = "create_time")
    private Date createTime;

    @UpdateTimestamp
    @Column(name = "update_time")
    private Date updateTime;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "roles_has_privileges",
            joinColumns = @JoinColumn(name="roles_id"),
            inverseJoinColumns = @JoinColumn(name="Privileges_id"))
    private List<Privilege> privileges;

}
