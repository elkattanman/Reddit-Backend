package com.elkattanman.reddit.bootstrap;

import com.elkattanman.reddit.domain.ERole;
import com.elkattanman.reddit.domain.Privilege;
import com.elkattanman.reddit.domain.Role;
import com.elkattanman.reddit.repository.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class Bootstrap implements CommandLineRunner {

    private final RoleRepository roleRepository;

    List<Role> roles() {
        List<Role> roles = new ArrayList<>();
        roles.add(Role.builder().name(ERole.ROLE_ADMIN).description("Administrator").privileges(new ArrayList<>()).build());
        roles.add(Role.builder().name(ERole.ROLE_USER).description("User").privileges(new ArrayList<>()).build());
        return roles;
    }

    @Override
    public void run(String... args) throws Exception {
        if(roleRepository.findAll().size()==0)roleRepository.saveAll(roles());
    }
}