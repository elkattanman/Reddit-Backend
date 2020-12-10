package com.elkattanman.reddit.repository;

import com.elkattanman.reddit.domain.enums.ERole;
import com.elkattanman.reddit.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {


    Optional<Role> findByName(ERole role);
}
